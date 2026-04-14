package org.example.racekatteklubben.Validation;

import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.exception.UserValidationException;
import org.springframework.stereotype.Component;

@Component("USER_NAME")
public class ValidateUserName implements ValidationStrategy {
    @Override
    public ValidationType getValidationType() {
        return ValidationType.USER_NAME;
    }

    @Override
    public void validate(Object object) {
        if(object == null || !(object.equals(""))) {
            throw new UserValidationException("The User object is when trying to checking for name is null or empty");
        }
        if(!(object instanceof User user)) {
            throw new UserValidationException("The object is not a User object when checking for name");
        }
        if(user.getName() == null || user.getName().isEmpty()) {
            throw new UserValidationException("The User's first name is null or empty");
        }
        if(user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new UserValidationException("The User's last name is null or empty");
        }
    }
}
