package com.ubs.expections;

import java.sql.SQLException;

public class TransactionFailedException extends SQLException {
    public TransactionFailedException(String message) {
        super(message);
    }

    public TransactionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
