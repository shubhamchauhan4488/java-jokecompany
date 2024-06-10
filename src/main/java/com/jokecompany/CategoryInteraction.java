package com.jokecompany;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class CategoryInteraction extends Interaction {
    private final JokeService jokeService;
    private List<String> categories;

    public CategoryInteraction(ConsolePrinter printer, BufferedReader reader, JokeService jokeService) {
        super(printer, reader);
        this.jokeService = jokeService;
        this.categories = new ArrayList<>();
    }

    public void fetchCategories() throws InterruptedException, IOException, URISyntaxException {
        this.categories = jokeService.getCategories();
    }

    public List<String> getCategories(){
        return this.categories;
    }
}
