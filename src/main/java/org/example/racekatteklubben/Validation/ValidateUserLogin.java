package org.example.racekatteklubben.Validation;

import org.example.racekatteklubben.entity.UserLogin;
import org.example.racekatteklubben.exception.ValidatorException;
import org.springframework.stereotype.Component;
@Component("USER_LOGIN")
public class ValidateUserLogin implements ValidationStrategy {

    @Override
    public ValidationType getValidationType() {
        return ValidationType.USER_LOGIN;
    }
    @Override
    public void validate(Object object) {
        if(!(object instanceof UserLogin userLogin)){
            throw new ValidatorException("Wrong Exception thrown please contact an administrator");
        }
    }
}
