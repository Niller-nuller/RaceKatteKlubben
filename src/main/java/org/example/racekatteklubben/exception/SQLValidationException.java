package org.example.racekatteklubben.exception;

import java.util.zip.DataFormatException;

public class SQLValidationException extends DataFormatException {
    public SQLValidationException(String message) {
        super(message);
    }
}
