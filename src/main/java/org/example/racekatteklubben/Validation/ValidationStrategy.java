package org.example.racekatteklubben.Validation;

public interface ValidationStrategy {
    ValidationType getValidationType();
    void validate(Object object);
}
