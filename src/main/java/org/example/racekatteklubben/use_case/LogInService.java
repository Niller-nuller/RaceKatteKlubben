package org.example.racekatteklubben.use_case;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.Validation.ValidationType;
import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.example.racekatteklubben.exception.LoginValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class LogInService {
    private final IUserRepository userRepository;
    private final ValidationService validationService;
    @Autowired
    public LogInService(IUserRepository userRepository,ValidationService validationService, HttpSession session) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }

    public User login(Auth loginRequest) {
        validateLoginRequest(loginRequest);
        Auth authLogin = userRepository.logUserIn(loginRequest);
        if (isValidCredentials(loginRequest,authLogin)) {
            return userRepository.getUserFromAuth(authLogin);
        }
        throw new LoginValidationException("Invalid Credentials");
    }

    private void validateLoginRequest(Auth loginRequest) {
        validationService.validate(ValidationType.EMAIL, loginRequest);
        validationService.validate(ValidationType.PASSWORD, loginRequest);
    }

    private boolean isValidCredentials(Auth loginRequest, Auth authLogin) {
        return authLogin != null && BCrypt.checkpw(loginRequest.getPassword(), authLogin.getPassword());
    }
}
