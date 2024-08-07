package com.it.support.exception;

public class PanneNotFoundException extends RuntimeException{
    public PanneNotFoundException(String message) {
        super(message);
    }
}
