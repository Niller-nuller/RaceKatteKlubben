package org.example.racekatteklubben.Validation;

import org.example.racekatteklubben.exception.SQLListException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("LIST")
public class ValidateSQLList implements ValidationStrategy{
    @Override
    public ValidationType getValidationType() {
        return ValidationType.LIST;
    }

    @Override
    public void validate(Object object) {
        if(!(object instanceof List<?> list)){
            throw new SQLListException("The object is not a list");
        }
        if(list.isEmpty()){
            throw new SQLListException("The list is empty");
        }
        if(list.contains(null)){
            throw new SQLListException("The list contains null elements");
        }
    }
}
