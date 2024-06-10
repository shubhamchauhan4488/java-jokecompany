package com.jokecompany;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public abstract class Interaction {
    private final ConsolePrinter printer;
    private final BufferedReader reader;

    public Interaction(ConsolePrinter printer, BufferedReader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    protected String prompt(String message, String inputType) throws IOException {
        printer.print(message);
        // inputType to check for expected String or number input
        return reader.readLine();
    }

    protected void printResults(List<String> results) {
        for (String result : results) {
            printer.print(result);
        }
    }
}
