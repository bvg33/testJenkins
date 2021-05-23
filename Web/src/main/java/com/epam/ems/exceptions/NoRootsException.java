package com.epam.ems.exceptions;

public class NoRootsException extends Exception {
    public NoRootsException() {
    }

    public NoRootsException(String message) {
        super(message);
    }

    public NoRootsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRootsException(Throwable cause) {
        super(cause);
    }
}
