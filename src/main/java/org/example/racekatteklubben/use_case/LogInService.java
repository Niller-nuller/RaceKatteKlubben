package org.example.racekatteklubben.use_case;

import org.example.racekatteklubben.Validation.ValidationType;
import org.example.racekatteklubben.entity.UserLogin;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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

    public UserLogin login(UserLogin loginRequest) {
        validateLogin(loginRequest);
        UserLogin userLogin = userRepository.logUserIn(loginRequest);
        if (userLogin != null && BCrypt.checkpw(loginRequest.getPassword(), userLogin.getPassword())) {
            return userLogin;
        }
        return null;
    }
    private void validateLogin(UserLogin loginRequest) {
        validationService.validate(ValidationType.EMAIL, loginRequest.getEmail());
        validationService.validate(ValidationType.PASSWORD, loginRequest.getPassword());
    }
}
