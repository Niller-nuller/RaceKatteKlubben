package org.example.racekatteklubben.usecase;

import org.example.racekatteklubben.Validation.ValidationStrategy;
import org.example.racekatteklubben.Validation.ValidationType;
import org.example.racekatteklubben.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ValidationService {

    private final Map<ValidationType, ValidationStrategy> validatorMap;

    public ValidationService(List<ValidationStrategy> strategies) {
        this.validatorMap = strategies.stream().collect(Collectors.toMap(ValidationStrategy::getValidationType, validation -> validation));
    }

    public void validate(ValidationType type, Object object) {
        ValidationStrategy validator = validatorMap.get(type.name());
        if (validator == null) {
            throw new ValidationException("No validate found for type" + type);
        }
        validator.validate(object);
    }
}
