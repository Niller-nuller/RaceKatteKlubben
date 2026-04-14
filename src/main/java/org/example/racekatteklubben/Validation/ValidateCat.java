package org.example.racekatteklubben.Validation;

import org.example.racekatteklubben.entity.Cat;
import org.example.racekatteklubben.exception.CatValidationException;
import org.springframework.stereotype.Component;

@Component("CAT")
public class ValidateCat implements ValidationStrategy {
    @Override
    public ValidationType getValidationType() {
        return ValidationType.CAT;
    }

    @Override
    public void validate(Object object) {
        if(!(object instanceof Cat cat)) {
            throw new CatValidationException("Object is not a Cat");
        }
        if(cat.getName() == null || cat.getName().isEmpty()){
            throw new CatValidationException("Cat Name is empty or null");
        }

    }
}
