package com.jtouzy.cleasy.output;

public class DefaultPrinter implements Printer {
    private static DefaultPrinter instance;

    public static final DefaultPrinter instance() {
        if (instance == null) {
            instance = new DefaultPrinter();
        }
        return instance;
    }
}
