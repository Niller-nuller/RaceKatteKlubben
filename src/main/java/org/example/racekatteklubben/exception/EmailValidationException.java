package org.example.racekatteklubben.exception;

public class EmailValidationException extends ValidationTypeException {
    public EmailValidationException(String message) {
        super(message);
    }
}
