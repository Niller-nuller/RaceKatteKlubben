package org.example.racekatteklubben.entity;

public class User {
    private long id;
    private String name;
    private String lastName;
    private Gender gender;
    private Auth AuthLogin;

    public User(){}
    public User(String name,String lastName, Gender gender, Auth AuthLogin) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.AuthLogin = AuthLogin;
    }
    public User(long id, String name, String lastName, Gender gender, Auth AuthLogin) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.AuthLogin = AuthLogin;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
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
    public Auth getUsersLogin() {
        return AuthLogin;
    }
    public void setUsersLogin(Auth AuthLogin) {
        this.AuthLogin = AuthLogin;
    }
}
