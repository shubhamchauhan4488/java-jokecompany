package com.jokecompany;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class UserInteraction {
    private final BufferedReader reader;
    private final ConsolePrinter printer;
    private final CategoryInteraction categoryInteraction;
    private final JokeInteraction jokeInteraction;

    public UserInteraction(BufferedReader reader, ConsolePrinter printer, CategoryInteraction categoryInteraction, JokeInteraction jokeInteraction) {
        this.reader = reader;
        this.printer = printer;
        this.categoryInteraction = categoryInteraction;
        this.jokeInteraction = jokeInteraction;
    }

    public void run() throws InterruptedException, IOException, URISyntaxException {
        try {
            while (true) {
                displayInstructions();
                char key = reader.readLine().charAt(0);
                switch (key) {
                    case 'c':
                        categoryInteraction.fetchCategories();
                        categoryInteraction.printResults(categoryInteraction.getCategories());
                        break;
                    case 'r':
                        jokeInteraction.fetchJokes();
                        // now there will be jokes in the private variable
                        // use Interaction class inherited
                        jokeInteraction.printResults(jokeInteraction.getJokes());
                        break;
                    case '?':
                        break;
                    case 'q':
                        return;
                    default:
                        printer.print("Invalid input. Please try again. Press ? to get instructions.");
                        break;
                }
            }
        } catch (IOException | URISyntaxException | InterruptedException e) {
            printer.print("An error occurred: " + e.getMessage());
        }
    }

    private void displayInstructions() {
        printer.print("Instructions:");
        printer.print("- Press 'c' to get categories of jokes.");
        printer.print("- Press 'r' to get random jokes.");
        printer.print("- Press '?' to display these instructions again.");
    }
}
