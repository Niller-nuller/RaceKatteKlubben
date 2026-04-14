package org.example.racekatteklubben.Validation;

import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.exception.UserValidationException;
import org.springframework.stereotype.Component;

@Component("USER_GENDER")
public class ValidateUserGender implements ValidationStrategy{
    @Override
    public ValidationType getValidationType() {
        return ValidationType.USER_GENDER;
    }

    @Override
    public void validate(Object object) {
        if(object == null || !(object.equals(""))) {
            throw new UserValidationException("The User object is null or empty when checking for gender");
        }
        if(!(object instanceof User user)) {
            throw new UserValidationException("The object is not a User object when checking for gender");
        }
        if(user.getGender() == null) {
            throw new UserValidationException("The User's gender is null");
        }
    }
}
