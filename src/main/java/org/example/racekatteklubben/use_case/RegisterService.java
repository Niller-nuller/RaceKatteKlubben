package org.example.racekatteklubben.use_case;

import jakarta.servlet.http.HttpSession;
import org.example.racekatteklubben.entity.RegisterWrapper;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.entity.UserLogin;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.web.server.servlet.Session;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private HttpSession session;
    private final IUserRepository userRepository;
    @Autowired
    public RegisterService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void register(RegisterWrapper registerWrapper) {
        UserLogin userLogin = registerWrapper.getUserLogin();
        User user = registerWrapper.getUser();
        notEmptyVerify(userLogin);
        emailVerify(userLogin);
        String hashed = BCrypt.hashpw(userLogin.getPassword(), BCrypt.gensalt());
        userLogin.setPassword(hashed);
        checkUserInput(user);
        userRepository.createUserCredentials(userLogin);
        userRepository.createUser(user,userLogin);
    }

    public void emailVerify(UserLogin userLogin) throws VerifyError{
        String email = userRepository.getEmail(userLogin);
        if(!(email == null)){
            throw new VerifyError("Email already exists");
        }
    }

    public void notEmptyVerify(UserLogin userLogin) throws VerifyError{
        if(userLogin.getEmail() == null || userLogin.getEmail().isBlank()){
            throw new VerifyError("Invalid email input");
        }
        if(userLogin.getPassword() == null || userLogin.getPassword().isBlank()){
            throw new VerifyError("Invalid password input");
        }
    }
    public void checkUserInput(User user) {
        checkNameInput(user);
        checkLastNameInput(user);
        checkGenderInput(user);
    }
    public void checkNameInput(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
    }
    public void checkLastNameInput(User user) {
        if (user.getLastName() == null || user.getLastName().isBlank()) {
            throw new IllegalArgumentException("Last name is required");
        }
    }
    public void checkGenderInput(User user){
        if (user.getGender() == null){
            throw new IllegalArgumentException("Gender is required");
        }
    }
}