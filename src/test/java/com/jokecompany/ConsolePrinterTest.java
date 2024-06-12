
package com.jokecompany;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsolePrinterTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent)); // Redirects System.out to Bytearray
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut); // Restores the original System.out
    }

    @Test
    public void testPrint() {
        ConsolePrinter printer = new ConsolePrinter();
        String testString = "Hello, world!";
        printer.print(testString);

        // Assert that the printed output is correct
        assertEquals(testString + System.lineSeparator(), outContent.toString());
    }
}
