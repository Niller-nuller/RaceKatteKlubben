package org.example.racekatteklubben.use_case;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.Validation.ValidationType;
import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.RegisterWrapper;
import org.example.racekatteklubben.entity.User;

import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final IUserRepository userRepository;
    private ValidationService validationService;

    @Autowired
    public RegisterService(IUserRepository userRepository,  ValidationService validationService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }
    public void register(RegisterWrapper registerWrapper) {
        Auth auth = registerWrapper.getUserLogin();
        User user = registerWrapper.getUser();
        String hashed = BCrypt.hashpw(auth.getPassword(), BCrypt.gensalt());
        auth.setPassword(hashed);
        validateUser(user);
        validateAuth(auth);
        userRepository.createUserCredentials(auth);
        userRepository.createUser(user,auth);
    }

    private void validateUser(User user) {
        validationService.validate(ValidationType.USER_NAME, user);
        validationService.validate(ValidationType.USER_GENDER, user);
    }
    private void validateAuth(Auth auth) {
        validationService.validate(ValidationType.PASSWORD, auth);
        validationService.validate(ValidationType.EMAIL, auth);
    }

}