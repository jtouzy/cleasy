package com.jtouzy.cleasy;

public class CleasyContextException extends RuntimeException {
    public CleasyContextException(String message) {
        super(message);
    }

    public CleasyContextException(String message, Throwable cause) {
        super(message, cause);
    }
}
