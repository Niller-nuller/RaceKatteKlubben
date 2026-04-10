package org.example.racekatteklubben.Validation;

public interface ValidationStrat {
    ValidationType getValidationType();
    void validate(Object object);
}
