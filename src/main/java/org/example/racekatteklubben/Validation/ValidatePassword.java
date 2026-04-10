package org.example.racekatteklubben.Validation;

import org.example.racekatteklubben.entity.UserLogin;
import org.example.racekatteklubben.exception.PasswordValidationException;
import org.example.racekatteklubben.exception.UserLoginException;
import org.example.racekatteklubben.exception.ValidatorException;
import org.springframework.stereotype.Component;


@Component("PASSWORD")
public class ValidatePassword implements ValidationStrat {
    @Override
    public ValidationType getValidationType() {
        return ValidationType.PASSWORD;
    }

    @Override
    public void validate(Object object) {
        if(object == null || object.equals("")){
            throw new UserLoginException("The login request is empty");
        }
        if(!(object instanceof UserLogin userLogin)){
            throw new ValidatorException("Wrong Exception thrown please contact an administrator");
        }
        if(userLogin.getPassword() == null || userLogin.getPassword().isEmpty()){
            throw new PasswordValidationException("Password is empty");
        }
        if(userLogin.getPassword().length() < 8){
            throw new PasswordValidationException("Password too short");
        }
        if(!userLogin.getPassword().matches(".*[a-zA-Z].*") || !userLogin.getPassword().matches(".*\\d.*")){
            throw new PasswordValidationException("Password is not string enough");
        }
    }
}
