package com.jokecompany;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class JokeService {
    private static final String BASE_URL = "https://us-central1-geotab-interviews.cloudfunctions.net";
    private final JsonFeed jsonFeed;

    public JokeService() {
        this.jsonFeed = new JsonFeed(BASE_URL);
    }

    public List<String> getCategories() throws IOException, InterruptedException, URISyntaxException {
        return jsonFeed.getCategories();
    }

    public List<String> getRandomJokes(String category, int number) throws InterruptedException, IOException, URISyntaxException {
        return jsonFeed.getRandomJokes(category, number);
    }
}
