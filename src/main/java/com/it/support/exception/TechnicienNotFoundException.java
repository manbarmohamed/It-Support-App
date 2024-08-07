package com.it.support.exception;

public class TechnicienNotFoundException extends RuntimeException{
    public TechnicienNotFoundException(String message) {
        super(message);
    }
}
