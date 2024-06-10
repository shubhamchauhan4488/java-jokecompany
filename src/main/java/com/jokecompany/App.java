package com.jokecompany;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static List<String> categories = new ArrayList<>();
    private static List<String> jokes = new ArrayList<>();
    private static ConsolePrinter printer = new ConsolePrinter();
    private static BufferedReader reader;

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            App.reader = reader;
            printer.print("Press ? to get instructions.");
            runApp();
        } catch (IOException e) {
            printer.print("Failed to initialize input reader: " + e.getMessage());
        }
    }

    private static void runApp() throws InterruptedException, IOException, URISyntaxException {
        try {
            while (true) {
                displayInstructions();
                char key = reader.readLine().charAt(0);
                switch (key) {
                    case 'c':
                        categories = getCategories();
                        printResults(categories);
                        break;
                    case 'r':
                        jokes = getRandomJokes(promptForCategory(), promptForNumberOfJokes());
                        printResults(jokes);
                        break;
                    case '?':
                        printHelp();
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

    static void displayInstructions() {
        printer.print("Instructions:");
        printer.print("- Press 'c' to get categories of jokes.");
        printer.print("- Press 'r' to get random jokes.");
        printer.print("- Press '?' to display these instructions again.");
        printer.print("- Press q to quit");
    }


        private static String promptForCategory() throws InterruptedException, IOException {
            printer.print("Want to specify a category? y/n");
            char categoryChoice = reader.readLine().charAt(0);
            String category = "";
            int numberOfJokes = 0;
            if (categoryChoice == 'y') {
                printer.print("Enter a category:");
                category = reader.readLine();
            }else if (categoryChoice == 'n') {
                category = "";
            }
            else{
                printer.print("Invalid input, lets try again !");
            }
            return category;
        }


    private static int promptForNumberOfJokes() throws IOException {
        int numberOfJokes = 0;
        while (numberOfJokes < 1 || numberOfJokes > 9) {
            printer.print("How many jokes do you want? (1-9)");
            try {
                numberOfJokes = Integer.parseInt(reader.readLine());
                if (numberOfJokes < 1 || numberOfJokes > 9) {
                    printer.print("Please enter a number between 1 and 9.");
                }
            } catch (NumberFormatException e) {
                printer.print("Invalid input. Please enter a number between 1 and 9.");
            }
        }
        return numberOfJokes;
    }

    static void printHelp() {
        printer.print("Instructions:");
        printer.print("- Press 'c' to get categories of jokes.");
        printer.print("- Press 'r' to get random jokes.");
        printer.print("- Press '?' to display these instructions again.");
    }

    private static void printResults(List<String> results) {
        results.forEach(result -> printer.print("-> " + result));
    }

    private static List<String> getRandomJokes(String category, int number) throws InterruptedException, IOException, URISyntaxException {
        String path = "/joke";
        JsonFeed jsonFeed = new JsonFeed(path);
        List<String> result = jsonFeed.getRandomJokes(category, number);
        return result;
    }

    private static List<String> getCategories() throws InterruptedException, IOException, URISyntaxException {
        String path = "/joke_category";
        JsonFeed jsonFeed = new JsonFeed(path);
        return jsonFeed.getCategories();
    }
}
