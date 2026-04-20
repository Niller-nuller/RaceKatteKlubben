package org.example.racekatteklubben.entity;

import org.example.racekatteklubben.exception.EmailValidationException;
import org.example.racekatteklubben.exception.PasswordValidationException;

public class AuthObject {
    private long id;
    private String email;
    private String password;

    public AuthObject(String email, String password) {
        validateEmail(email);
        validatePassword(password);
            this.email = email;
            this.password = password;
    }
    public AuthObject(long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
    private void validateEmail(String email) throws EmailValidationException {
        if (email == null || email.isEmpty()) {
            throw new EmailValidationException("Email cannot be empty");
        }
        if (!email.contains("@")) {
            throw new EmailValidationException("Invalid email, email does not contain @");
        }
        if (email.length() < 8) {
            throw new EmailValidationException("Email length cannot be greater than 8");
        }
        if (!email.contains(".")) {
            throw new EmailValidationException("Email does not contain .");
        }
        if (!email.matches(".*\\.(com|dk)$")) {
            throw new EmailValidationException("Email must end with .com or .dk");
        }
    }
    private void validatePassword(String password) throws PasswordValidationException {
        if (password == null || password.isEmpty()) {
            throw new PasswordValidationException("Password cannot be empty");
        }
        if (password.length() < 8) {
            throw new PasswordValidationException("Password or Email is incorrect");
        }
        if (!password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*")) {
            throw new PasswordValidationException("Password or email is incorrect");
        }
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
