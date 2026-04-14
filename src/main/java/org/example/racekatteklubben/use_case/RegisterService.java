package org.example.racekatteklubben.use_case;

import org.example.racekatteklubben.entity.Auth;
import org.example.racekatteklubben.entity.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final IUserRepository userRepository;
    @Autowired
    public RegisterService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void register(Auth auth){
        notEmptyVerify(auth);
        emailVerify(auth);
        String hashed = BCrypt.hashpw(auth.getPassword(), BCrypt.gensalt());
        auth.setPassword(hashed);
        userRepository.createUserCredentials(auth);
    }

    public void emailVerify(Auth auth) throws VerifyError{
        String email = userRepository.getEmail(auth);
        if(!email.isBlank()){
            throw new VerifyError("Email already exists");
        }
    }

    public void notEmptyVerify(Auth auth) throws VerifyError{
        if(auth.getEmail() == null || !auth.getEmail().isBlank()){
            throw new VerifyError("Invalid email input");
        }
        if(auth.getPassword() == null || !auth.getPassword().isBlank()){
            throw new VerifyError("Invalid password input");
        }
    }
}