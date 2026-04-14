package org.example.racekatteklubben.exception;

public class CatValidationException extends RuntimeException {
    public CatValidationException(String message) {
        super(message);
    }
}
