package com.epam.esm.exceptions;

/**
 * Exception class in case of duplicate item in database.
 */
public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Element already exists in database.";
    }
}
