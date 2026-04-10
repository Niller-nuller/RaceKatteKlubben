package org.example.racekatteklubben.use_case;

import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.entity.UserLogin;
import org.example.racekatteklubben.infrastrcture.UserRepository;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

@Service
public class CreateUserProfileService {
    private HttpSession session;
    private final UserRepository userRepository;
    public CreateUserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUserProfile(User user) {
        checkUserInput(user);
        UserLogin userLogin = (UserLogin) session.getAttribute("loggedInUser");
        userRepository.createUser(user,userLogin);
    }
    public void checkUserInput(User user) {
        checkNameInput(user);
        checkLastNameInput(user);
        checkGenderInput(user);
    }
    public void checkNameInput(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("Username is required");
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
