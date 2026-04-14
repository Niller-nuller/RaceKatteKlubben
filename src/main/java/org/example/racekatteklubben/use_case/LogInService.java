package org.example.racekatteklubben.use_case;

import org.example.racekatteklubben.Validation.ValidationType;
import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class LogInService {

    private final IUserRepository userRepository;
    private final ValidationService  validationService;
    @Autowired
    public LogInService(IUserRepository userRepository, ValidationService validationService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }

    public Auth login(Auth loginRequest) {
        validateLogin(loginRequest);
        Auth auth = userRepository.logUserIn(loginRequest);
        if (auth != null && BCrypt.checkpw(loginRequest.getPassword(), auth.getPassword())) {
            return auth;
        }
        return null;
    }
    private void validateLogin(Auth loginRequest) {
        validationService.validate(ValidationType.EMAIL, loginRequest.getEmail());
        validationService.validate(ValidationType.PASSWORD, loginRequest.getPassword());
    }
}
