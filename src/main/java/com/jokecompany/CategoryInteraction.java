package com.jokecompany;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class CategoryInteraction extends Interaction {
    private final JokeService jokeService;

    public CategoryInteraction(ConsolePrinter printer, BufferedReader reader, JokeService jokeService) {
        super(printer, reader);
        this.jokeService = jokeService;
    }

    public void displayCategories() throws InterruptedException, IOException, URISyntaxException {
        printResults(jokeService.getCategories());
    }
}
