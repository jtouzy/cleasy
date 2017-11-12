package com.jtouzy.cleasy.configuration;

import com.jtouzy.cleasy.output.DefaultPrinter;
import com.jtouzy.cleasy.output.Printer;

public class DefaultConfiguration implements Configuration {
    @Override
    public Printer getPrinter() {
        return DefaultPrinter.instance();
    }

    @Override
    public boolean processFirstArgumentAsToolIdentifier() {
        return true;
    }

    @Override
    public String getParameterPrefix() {
        return "--";
    }

    @Override
    public String getShortParameterPrefix() {
        return "-";
    }
}
