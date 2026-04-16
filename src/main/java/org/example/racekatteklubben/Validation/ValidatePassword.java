package org.example.racekatteklubben.Validation;

import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.exception.PasswordValidationException;
import org.example.racekatteklubben.exception.AuthException;
import org.example.racekatteklubben.exception.ValidatorException;
import org.springframework.stereotype.Component;


@Component("PASSWORD")
public class ValidatePassword implements ValidationStrategy {
    @Override
    public ValidationType getValidationType() {
        return ValidationType.PASSWORD;
    }

    @Override
    public void validate(Object object) {
        if(object == null || object.equals("")){
            throw new AuthException("The login request is empty");
        }
        if(!(object instanceof Auth auth)){
            throw new ValidatorException("Wrong Exception thrown please contact an administrator");
        }
        if(auth.getPassword() == null || auth.getPassword().isEmpty()){
            throw new PasswordValidationException("Password is empty");
        }
        if(auth.getPassword().length() < 8){
            throw new PasswordValidationException("Password too short");
        }
        if(!auth.getPassword().matches(".*[a-zA-Z].*") || !auth.getPassword().matches(".*\\d.*")){
            throw new PasswordValidationException("Password is not string enough");
        }
    }
}
