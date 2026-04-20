package org.example.racekatteklubben.Validation;

import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.exception.EmailValidationException;
import org.example.racekatteklubben.exception.AuthException;
import org.example.racekatteklubben.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component("EMAIL")
public class ValidateEmail implements ValidationStrategy {

    @Override
    public ValidationType getValidationType() {
        return ValidationType.EMAIL;
    }

    @Override
    public void validate(Object object) {
        if(object == null || object.equals("")){
            throw new AuthException("The login request is empty");
        }
        if(!(object instanceof Auth auth)){
            throw new ValidationException("Wrong Exception thrown please contact an administrator");
        }
        if(auth.getEmail() == null || auth.getEmail().isEmpty()){
            throw new EmailValidationException("Email is empty");
        }
        if(!auth.getEmail().contains("@")){
            throw new EmailValidationException("Invalid email");
        }
        if(auth.getEmail().length() < 8){
            throw new EmailValidationException("Invalid email length");
        }
    }
}
