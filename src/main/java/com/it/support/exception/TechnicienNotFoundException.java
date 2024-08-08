package com.it.support.exception;

/**
 * Custom exception thrown when a Technician item is not found.
 */

public class TechnicienNotFoundException extends RuntimeException{

    /**
     * Constructs a new TechnicianNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */

    public TechnicienNotFoundException(String message) {
        super(message);
    }
}
