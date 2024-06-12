package com.jokecompany;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonFeed {
    private final String baseUrl;
    private final HttpClient client;

    public JsonFeed(String endpoint) {
        this.baseUrl = endpoint;
        this.client = HttpClient.newHttpClient();
    }

    public String buildUrlWithParams(String endpoint, Map<String, String> params) {
        StringBuilder url = new StringBuilder(baseUrl).append(endpoint);
        if (params != null && !params.isEmpty()) {
            url.append("?");
            params.forEach((key, value) -> url.append(key).append("=").append(value).append("&"));
            url.setLength(url.length() - 1); // Remove trailing '&'
        }
        return url.toString();
    }

    public String sendHttpRequest(String url, String method) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(url);
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(uri);

        if (method.equalsIgnoreCase("GET")) {
            requestBuilder.GET();
        }// we can extend for more methods

        HttpRequest request = requestBuilder.build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private String parseJokeFromResponse(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        if (!jsonObject.has("value")) {
            return "Error: No joke found";
        }
        return jsonObject.get("value").getAsString();
    }

    public List<String> getRandomJokes(String category, int number) throws URISyntaxException, IOException, InterruptedException {
        List<String> jokes = new ArrayList<>();
        Map<String, String> params = new HashMap<>();
        if (category != null && !category.isEmpty()) {
            params.put("category", category);
        }
        String url = buildUrlWithParams("/joke", params);

        // adding unique jokes to the list
        for (int i = 0; i < number; i++) {
            String joke;
            do {
                String response = sendHttpRequest(url, "GET");
                joke = parseJokeFromResponse(response);
            } while (jokes.contains(joke));
            jokes.add(joke); // add the joke only if it's not already in the list
        }
        return jokes;
    }

    public List<String> getCategories() throws IOException, InterruptedException, URISyntaxException {
        String url = buildUrlWithParams("/joke_category", new HashMap<>());
        String response = sendHttpRequest(url, "GET");

        // Parse the JSON array from the response
        JsonArray jsonArray = JsonParser.parseString(response).getAsJsonArray();
        List<String> categories = new ArrayList<>();
        for (JsonElement element : jsonArray) {
            categories.add(element.getAsString());
        }

        return categories;
    }
}
