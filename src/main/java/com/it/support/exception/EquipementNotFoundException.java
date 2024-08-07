package com.it.support.exception;

public class EquipementNotFoundException extends RuntimeException{
    public EquipementNotFoundException(String message) {
        super(message);
    }
}
