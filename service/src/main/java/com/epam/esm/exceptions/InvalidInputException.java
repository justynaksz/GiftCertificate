package com.epam.esm.exceptions;

/**
 * Exception class in case of invalid input.
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
