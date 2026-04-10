package org.example.racekatteklubben.exception;

public class PasswordValidationException extends ValidationTypeException {
    public PasswordValidationException(String message) {
        super(message);
    }
}
