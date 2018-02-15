package com.jtouzy.cleasy.output;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

public class DefaultPrinter implements Printer {
    private static DefaultPrinter instance;

    public static final DefaultPrinter instance() {
        if (instance == null) {
            instance = new DefaultPrinter();
        }
        return instance;
    }

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-hh:mm:ss/SSS");
    private int currentLevel = 0;

    @Override
    public void logHeader() {
    }

    @Override
    public void increase() {
        currentLevel += 1;
    }

    @Override
    public void decrease() {
        currentLevel -= 1;
    }

    @Override
    public void title(String log) {
        log("=> " + log);
    }

    @Override
    public void log(String log) {
        System.out.println(getLogPrefix().append(log));
    }

    @Override
    public void logCommandException(Throwable error) {
        currentLevel = 0;
        title("Error during command execution");
        increase();
        Throwable currentThrowable = error;
        while (currentThrowable != null) {
            log((currentThrowable == error ? "Detail :" : "Caused by :") + getThrowableMessage(currentThrowable));
            increase();
            for (StackTraceElement elm : currentThrowable.getStackTrace()) {
                log("at " + elm.getClassName() + ":" + elm.getMethodName() + ":(" + elm.getLineNumber() + ")");
            }
            decrease();
            currentThrowable = currentThrowable.getCause();
        }
        decrease();
    }

    private String getThrowableMessage(Throwable error) {
        StringBuilder message = new StringBuilder(" [")
                .append(error.getClass().getName())
                .append("]");
        if (error.getMessage() != null) {
            message.append(" - ").append(error.getMessage());
        }
        return message.toString();
    }

    protected StringBuilder getLevelPrefix() {
        StringBuilder prefix = new StringBuilder();
        IntStream.range(0, currentLevel).forEach(e -> prefix.append("   "));
        return prefix;
    }

    protected StringBuilder getLogPrefix() {
        StringBuilder logPrefix = new StringBuilder();
        return logPrefix.append("[" ).append(dateFormat.format(new Date())).append("] ").append(getLevelPrefix());
    }
}
