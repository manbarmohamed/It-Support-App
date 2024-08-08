package com.it.support.exception;


/**
 * Custom exception thrown when a user item is not found.
 */
public class UserNotFoundException extends RuntimeException{

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
