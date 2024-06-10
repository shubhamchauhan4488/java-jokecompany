package com.jokecompany;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class JokeInteraction extends Interaction {
    private final JokeService jokeService;
    private List<String> jokes;

    public JokeInteraction(ConsolePrinter printer, BufferedReader reader, JokeService jokeService) {
        super(printer, reader);
        this.jokeService = jokeService;
        this.jokes = new ArrayList<>();
    }

    public void fetchJokes() throws InterruptedException, IOException, URISyntaxException {
        String categoryChoice = prompt("Want to specify a category? y/n");
        String category = "";

        if (categoryChoice.equalsIgnoreCase("y")) {
            category = prompt("Enter a category:");
        }
        int numberOfJokes = Integer.parseInt(prompt("How many jokes do you want? (1-9)"));
        this.jokes = jokeService.getRandomJokes(category, numberOfJokes);
    }

    public List<String> getJokes(){
        return this.jokes;
    }
}
