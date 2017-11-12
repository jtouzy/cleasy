package com.jtouzy.cleasy.configuration;

import com.jtouzy.cleasy.output.Printer;

public interface Configuration {
    // Generic configuration
    Printer getPrinter();
    // CL processing configuration
    boolean processFirstArgumentAsToolIdentifier();
    String getParameterPrefix();
    String getShortParameterPrefix();
}
