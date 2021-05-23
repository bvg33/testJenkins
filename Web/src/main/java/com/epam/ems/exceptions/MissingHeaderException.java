package com.epam.ems.exceptions;

public class MissingHeaderException extends RuntimeException {

    public MissingHeaderException(String message) {
        super(message);
    }

    public MissingHeaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingHeaderException(Throwable cause) {
        super(cause);
    }
}
