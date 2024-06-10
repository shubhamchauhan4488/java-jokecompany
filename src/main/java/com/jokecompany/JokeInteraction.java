package com.jokecompany;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class JokeInteraction extends Interaction {
    private final JokeService jokeService;

    public JokeInteraction(ConsolePrinter printer, BufferedReader reader, JokeService jokeService) {
        super(printer, reader);
        this.jokeService = jokeService;
    }

    public void getJokes() throws InterruptedException, IOException, URISyntaxException {
        String categoryChoice = prompt("Want to specify a category? y/n");
        String category = "";

        if (categoryChoice.equalsIgnoreCase("y")) {
            category = prompt("Enter a category:");
        }
        int numberOfJokes = Integer.parseInt(prompt("How many jokes do you want? (1-9)"));
        printResults(jokeService.getRandomJokes(category, numberOfJokes));
    }
}
