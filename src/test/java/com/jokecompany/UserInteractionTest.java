package com.jokecompany;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserInteractionTest {

    @Mock
    private BufferedReader mockReader;
    @Mock
    private ConsolePrinter mockPrinter;
    @Mock
    private CategoryInteraction mockCategoryInteraction;
    @Mock
    private JokeInteraction mockJokeInteraction;

    @InjectMocks
    private UserInteraction userInteraction;

    @BeforeEach
    public void setUp() {
        // Setup common mock behaviors if necessary
    }

    @Test
    public void testRun_CategoriesOption() throws IOException, URISyntaxException, InterruptedException {
        // Simulate user pressing 'c' to fetch categories
        when(mockReader.readLine()).thenReturn("c", "q");
        userInteraction.run();
        verify(mockCategoryInteraction).fetchCategories();
        verify(mockCategoryInteraction).printResults(any());
    }

    @Test
    public void testRun_RandomJokesOption() throws IOException, URISyntaxException, InterruptedException {
        // Simulate user pressing 'r' to fetch random jokes
        when(mockReader.readLine()).thenReturn("r", "q");
        userInteraction.run();
        verify(mockJokeInteraction).fetchJokes();
        verify(mockJokeInteraction).printResults(any());
    }

    @Test
    public void testRun_InvalidInput() throws IOException {
        // Simulate user entering an invalid command
        when(mockReader.readLine()).thenReturn("x", "q"); // Invalid followed by quit
        userInteraction.run();
        verify(mockPrinter, atLeastOnce()).print("Invalid input. Please try again. Press ? to get instructions.");
    }

    @Test
    public void testRun_DisplayHelpInstructions() throws IOException {
        // Simulate user requesting help, performing another command, and then quitting
        when(mockReader.readLine()).thenReturn("?", "r", "q");
        userInteraction.run();
        verify(mockPrinter, times(3)).print("Instructions:");
        verify(mockPrinter, times(3)).print("- Press 'c' to get categories of jokes.");
        verify(mockPrinter, times(3)).print("- Press 'r' to get random jokes.");
        verify(mockPrinter, times(3)).print("- Press 'q' to exit.");
        verify(mockPrinter, times(3)).print("- Press '?' to display these instructions again.");
    }

    @Test
    public void testRun_ExitCommand() throws IOException {
        // Simulate user exiting the program
        when(mockReader.readLine()).thenReturn("q");
        userInteraction.run();
        verify(mockReader, times(1)).readLine(); // Ensure it only reads once and then exits
    }
}