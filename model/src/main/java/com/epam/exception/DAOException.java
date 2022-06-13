package com.epam.exception;

import java.sql.SQLException;

public class DAOException extends Throwable {

    public DAOException(String exceptionMessage) {
        super(exceptionMessage);
    }

    public DAOException(String exceptionMessage, SQLException sqlException) {
        super(exceptionMessage, sqlException);
    }
}
