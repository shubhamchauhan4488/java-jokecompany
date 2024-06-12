package com.jokecompany;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CategoryInteractionTest {

    @Mock
    private JokeService jokeService;

    @Mock
    private ConsolePrinter printer;

    @Mock
    private BufferedReader reader;

    private CategoryInteraction categoryInteraction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Use initMocks instead of openMocks
        categoryInteraction = new CategoryInteraction(printer, reader, jokeService);
    }

    @Test
    void testFetchCategories() throws InterruptedException, IOException, URISyntaxException {
        List<String> mockCategories = Arrays.asList("general", "programming", "knock-knock");
        when(jokeService.getCategories()).thenReturn(mockCategories);

        categoryInteraction.fetchCategories();

        assertEquals(mockCategories, categoryInteraction.getCategories());
    }
}
