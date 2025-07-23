package com.pharmacy_store.exception;

public class CustomMessageError extends Exception {
    private String message;

    public CustomMessageError(String message) {
        super(message);
    }
}
