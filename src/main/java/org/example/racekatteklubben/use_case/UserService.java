package org.example.racekatteklubben.use_case;

import org.example.racekatteklubben.Validation.ValidationType;
import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.User;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final ValidationService validationService;
    private final IUserRepository userRepository;

    @Autowired
    public UserService(ValidationService validationService ,IUserRepository userRepository) {
        this.validationService = validationService;
        this.userRepository = userRepository;
    }

    public List<User> handleGetAllUsers(String criteria){
        validationService.validate(ValidationType.CRITERIA,criteria);
        return userRepository.requestFullFilteredUserList(criteria);
    }

    public User getUserFromAuth(Auth auth) {
        return userRepository.getUserFromAuth(auth);
    }

    public void updateUser(User formUser, Auth auth) {
        User existingUser = userRepository.getUserFromAuth(auth);
        User updatedUser = new User(
                existingUser.getId(),
                formUser.getName(),
                formUser.getLastName(),
                formUser.getGender(),
                auth
        );
        userRepository.updateUser(updatedUser);
    }
    public void handleDeleteUser(User user) {
        //Validate lol
        userRepository.requestDeleteUser(user);
    }
}
