package org.example.racekatteklubben.use_case;

import org.example.racekatteklubben.Validation.ValidationStrat;
import org.example.racekatteklubben.Validation.ValidationType;
import org.example.racekatteklubben.exception.ValidatorException;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;
import java.util.Map;

@Service
public class ValidationService {

    private final Map<String, ValidationStrat> validatorMap;

    public ValidationService(Map<String, ValidationStrat> validatorMap) {
        this.validatorMap = validatorMap;
    }

    public void validate(ValidationType type, Object object) {
        ValidationStrat validator = validatorMap.get(type.name());
        if (validator == null) {
            throw new ValidatorException("No validate found for type" + type);
        }
        validator.validate(object);
    }
}
