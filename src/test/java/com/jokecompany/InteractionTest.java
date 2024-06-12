package com.jokecompany;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class InteractionTest {

    @Mock
    private ConsolePrinter printer;

    @Mock
    private BufferedReader reader;

    private Interaction interaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Creating an anonymous subclass of Interaction for testing purposes
        interaction = new Interaction(printer, reader) {};
    }

    @Test
    void testPrompt() throws IOException {
        String message = "Enter your input: ";
        String input = "User input";
        when(reader.readLine()).thenReturn(input);

        String actualInput = interaction.prompt(message);
        assertEquals(input, actualInput);
        verify(printer).print(message);
    }

    @Test
    void testPrintResults() {
        List<String> results = Arrays.asList("Result 1", "Result 2", "Result 3");

        interaction.printResults(results);

        verify(printer).print("-> Result 1");
        verify(printer).print("-> Result 2");
        verify(printer).print("-> Result 3");
    }
}
