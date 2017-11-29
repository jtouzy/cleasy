package com.jtouzy.cleasy.output;

public interface Printer {
    void logHeader();
    void increase();
    void decrease();
    void title(String log);
    void log(String log);
    void logCommandException(Throwable error);
}
