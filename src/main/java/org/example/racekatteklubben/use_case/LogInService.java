package org.example.racekatteklubben.use_case;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.Validation.ValidationType;
import org.example.racekatteklubben.entity.AuthObject;
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
    public LogInService(IUserRepository userRepository,ValidationService validationService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }

    public User login(String email, String password) {
        AuthObject loginRequest = returnLoginObject(email,password);
        AuthObject authLogin = userRepository.logUserIn(loginRequest);
        if (isValidCredentials(loginRequest,authLogin)) {
            return userRepository.getUserFromAuthObject(authLogin);
        }
        throw new LoginValidationException("Invalid Credentials");
    }
    private AuthObject returnLoginObject(String email, String password) {
        return new AuthObject(email, password);

    }
    private boolean isValidCredentials(AuthObject loginRequest, AuthObject authLogin) {
        return authLogin != null && BCrypt.checkpw(loginRequest.getPassword(), authLogin.getPassword());
    }
}
