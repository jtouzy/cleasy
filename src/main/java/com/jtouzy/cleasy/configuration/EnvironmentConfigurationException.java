package com.jtouzy.cleasy.configuration;

public class EnvironmentConfigurationException extends RuntimeException {
    public EnvironmentConfigurationException(String message) {
        super(message);
    }

    public EnvironmentConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
