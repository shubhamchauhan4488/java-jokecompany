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
        String category = "";
        try {
            String categoryChoice = prompt("Want to specify a category? y/n");
            if (categoryChoice.equalsIgnoreCase("y")) {
                category = prompt("Enter a category:");
            } else if (categoryChoice.equalsIgnoreCase("n")) {
                category = "";
            }else{
                throw new IllegalArgumentException("Invalid input. Please enter either 'n' or 'y' only.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            throw e; // need to break the flow here to re-do
        }

        try {
            int numberOfJokes = Integer.parseInt(prompt("How many jokes do you want? (1-9)"));
            if (numberOfJokes < 1 || numberOfJokes > 9) {
                throw new IllegalArgumentException("Number of jokes must be between 1 and 9.");
            }
            this.jokes = jokeService.getRandomJokes(category, numberOfJokes);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input format. Cannot convert to number. Enter number only");
            throw e;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    public List<String> getJokes(){
        return this.jokes;
    }
}
