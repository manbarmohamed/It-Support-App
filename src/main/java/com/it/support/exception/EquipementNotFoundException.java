package com.it.support.exception;

/**
 * Custom exception thrown when an equipment item is not found.
 */
public class EquipementNotFoundException extends RuntimeException {

    /**
     * Constructs a new EquipementNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public EquipementNotFoundException(String message) {
        super(message);
    }
}
