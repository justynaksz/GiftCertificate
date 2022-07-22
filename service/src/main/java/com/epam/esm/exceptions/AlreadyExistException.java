package com.epam.esm.exceptions;

/**
 * Exception class in case of duplicate item in database.
 */
public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException(String message) {
        super(message);
    }
}
