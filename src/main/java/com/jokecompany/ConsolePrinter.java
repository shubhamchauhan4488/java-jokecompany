package com.jokecompany;

public class ConsolePrinter {
    private String printValue;

    public void print(String s) {
        this.printValue = s;
        System.out.println(s);
    }

    @Override
    public String toString() {
        return printValue;
    }
}
