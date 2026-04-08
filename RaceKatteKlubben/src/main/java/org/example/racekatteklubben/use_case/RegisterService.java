package org.example.racekatteklubben.use_case;

import org.example.racekatteklubben.entity.UserLogin;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private IUserRepository userRepository;


    public void register(UserLogin userLogin){
        emailVerify(userLogin);
        notEmailVerify(userLogin);
        String hashed = BCrypt.hashpw(userLogin.getPassword(), BCrypt.gensalt());
        userLogin.setPassword(hashed);
        userRepository.createUser(userLogin);
    }

    public void emailVerify(UserLogin userLogin) throws VerifyError{
        String email = userRepository.getEmail(userLogin);
        if(!email.isBlank()){
            throw new VerifyError("Email already exists");
        }
    }

    public void notEmailVerify(UserLogin userLogin) throws VerifyError{
        if(userLogin.getEmail() == null || !userLogin.getEmail().isBlank()){
            throw new VerifyError("Invalid email input");
        }
        if(userLogin.getPassword() == null || !userLogin.getPassword().isBlank()){
            throw new VerifyError("Invalid password input");
        }
    }
}
