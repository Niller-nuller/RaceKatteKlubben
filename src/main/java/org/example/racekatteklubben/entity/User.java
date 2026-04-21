package org.example.racekatteklubben.entity;

import java.util.List;

public class User {
    private long id;
    private String name;
    private String lastName;
    private Gender gender;
    private AuthObject usersAuth;
    private List<Cat> usersCats;


    public User(String name,String lastName, Gender gender, AuthObject usersAuth) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.usersAuth = usersAuth;
    }
    public User(long id, String name,String lastName, Gender gender, AuthObject usersAuth) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.usersAuth = usersAuth;
    }
    public User(long id, String name, String lastName, Gender gender, AuthObject usersAuth, List<Cat> usersCats) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.usersAuth = usersAuth;
        this.usersCats = usersCats;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public  void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public AuthObject getUsersAuthObject() {
        return usersAuth;
    }
    public void setUsersLogin(AuthObject usersAuth) {
        this.usersAuth = usersAuth;
    }
    public List<Cat> getUsersCats() {return usersCats;}
    public void setUsersCats(List<Cat> usersCats) {this.usersCats = usersCats;}

    private void validateName(String name){

    }
    private void validateLastName(String lastName){

    }
    private void validateGender(Gender gender){

    }
    private void validateUsersAuth(AuthObject usersAuth){

    }
}
