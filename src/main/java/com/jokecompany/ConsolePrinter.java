package com.jokecompany;


public class ConsolePrinter {
    private String printValue;

    public ConsolePrinter value(String value) {
        this.printValue = value;
        return this;
    }

    public void print(String s) {
        System.out.println(s);
    }

    @Override
    public String toString() {
        return printValue;
    }
}
