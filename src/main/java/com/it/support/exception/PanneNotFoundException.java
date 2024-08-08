package com.it.support.exception;


/**
 * Custom exception thrown when a panne item is not found.
 */
public class PanneNotFoundException extends RuntimeException{

    /**
     * Constructs a new PanneNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public PanneNotFoundException(String message) {
        super(message);
    }
}
