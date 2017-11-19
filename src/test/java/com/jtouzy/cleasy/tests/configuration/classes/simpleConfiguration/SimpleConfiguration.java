package com.jtouzy.cleasy.tests.configuration.classes.simpleConfiguration;

import com.jtouzy.cleasy.configuration.CleasyConfiguration;
import com.jtouzy.cleasy.configuration.Configuration;
import com.jtouzy.cleasy.output.Printer;

@CleasyConfiguration
public class SimpleConfiguration implements Configuration {
    @Override
    public Printer getPrinter() {
        return null;
    }

    @Override
    public boolean processFirstArgumentAsToolIdentifier() {
        return false;
    }

    @Override
    public String getParameterPrefix() {
        return null;
    }

    @Override
    public String getShortParameterPrefix() {
        return null;
    }
}
