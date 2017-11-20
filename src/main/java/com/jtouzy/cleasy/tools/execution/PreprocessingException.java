package com.jtouzy.cleasy.tools.execution;

public class PreprocessingException extends RuntimeException {
    public PreprocessingException(String message) {
        super(message);
    }

    public PreprocessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
