package com.jokecompany;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) {
        // Create a BufferedReader to read user input from the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Initialize a ConsolePrinter to print messages to the console
        ConsolePrinter printer = new ConsolePrinter();

        // Initialize the JokeService to handle API calls for jokes and categories
        JokeService jokeService = new JokeService();

        // Initialize interactions for categories and jokes
        CategoryInteraction categoryInteraction = new CategoryInteraction(printer, reader, jokeService);
        JokeInteraction jokeInteraction = new JokeInteraction(printer, reader, jokeService);

        // Initialize UserInteraction to handle user inputs and outputs
        UserInteraction userInteraction = new UserInteraction(reader, printer, categoryInteraction, jokeInteraction);

        userInteraction.run();
    }
}
