package com.sap.refactoring.exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super(message);
    }
}
