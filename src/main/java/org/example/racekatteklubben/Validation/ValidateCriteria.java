package org.example.racekatteklubben.Validation;

import org.example.racekatteklubben.exception.CriteriaException;
import org.springframework.stereotype.Component;
import java.util.List;
@Component("CRITERIA")
public class ValidateCriteria implements ValidationStrategy{

    private final List<String> blackList =  List.of("DROP", "DELETE", "INSERT", "UPDATE", "TRUNCATE",
            "ALTER", "EXEC", "EXECUTE", "UNION", "SELECT",
            "--", ";", "/*", "*/", "XP_");

    @Override
    public ValidationType getValidationType() {
        return ValidationType.CRITERIA;
    }

    @Override
    public void validate(Object object) {
        if(object instanceof String criteria){
            if(blackList.contains(criteria)){
                throw new CriteriaException("Criteria contains blacklist");
            }
        }
    }
}
