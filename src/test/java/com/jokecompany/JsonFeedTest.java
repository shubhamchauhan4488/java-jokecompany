package com.jokecompany;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JsonFeedTest {

    @InjectMocks
    private JsonFeed jsonFeed;

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockResponse;
    @BeforeEach
    public void setUp() {
        jsonFeed = new JsonFeed("http://api.joke.com");
    }

    @Test
    public void testBuildUrlWithParams_NoParams() {
        String url = jsonFeed.buildUrlWithParams("/test-endpoint", null);
        assertEquals("http://api.joke.com/test-endpoint", url);
    }

    @Test
    public void testBuildUrlWithParams_WithParams() {
        Map<String, String> params = new HashMap<>();
        params.put("key", "value");
        String url = jsonFeed.buildUrlWithParams("/test-endpoint", params);
        assertEquals("http://api.joke.com/test-endpoint?key=value", url);
    }

}
