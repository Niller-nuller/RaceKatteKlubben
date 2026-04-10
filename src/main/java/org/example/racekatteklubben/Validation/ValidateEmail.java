package org.example.racekatteklubben.Validation;

import org.example.racekatteklubben.entity.UserLogin;
import org.example.racekatteklubben.exception.EmailValidationException;
import org.example.racekatteklubben.exception.ValidationTypeException;
import org.example.racekatteklubben.exception.ValidatorException;
import org.springframework.stereotype.Component;

@Component("EMAIL")
public class ValidateEmail implements ValidationStrat {

    @Override
    public ValidationType getValidationType() {
        return ValidationType.EMAIL;
    }

    @Override
    public void validate(Object object) {
        if(!(object instanceof UserLogin userLogin)){
            throw new ValidatorException("Wrong Exception thrown please contact an administrator");
        }
        if(userLogin.getEmail() == null || userLogin.getEmail().isEmpty()){
            throw new EmailValidationException("Email is empty");
        }
        if(!userLogin.getEmail().contains("@")){
            throw new EmailValidationException("Invalid email");
        }
        if(userLogin.getEmail().length() < 8){
            throw new EmailValidationException("Invalid email length");
        }
    }
}
