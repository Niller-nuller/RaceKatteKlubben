package org.example.racekatteklubben.usecase;

import org.example.racekatteklubben.entity.AuthObject;
import org.example.racekatteklubben.entity.Gender;
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
    public User register(String email,String password,String name,String lastName,String gender) {
        AuthObject auth = createAuthObject(email,password);
        User user = createUser(name, lastName, gender, auth);
        return userRepository.requestCreateUser(user);
    }
    private AuthObject createAuthObject(String email,String password) {
        return new AuthObject(email, BCrypt.hashpw(password, BCrypt.gensalt()));
    }
    private User createUser(String name,String lastName,String gender,AuthObject usersAuth) {
        return new User(name,lastName, Gender.valueOf(gender), usersAuth);
    }
}