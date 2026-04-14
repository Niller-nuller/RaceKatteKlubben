package org.example.racekatteklubben.entity;

public class User {
    private long id;
    private String name;
    private String lastName;
    private Gender gender;
    private Auth usersAuth;

    public User(){}
    public User(String name,String lastName, Gender gender, Auth usersAuth) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.usersAuth = usersAuth;
    }
    public User(long id, String name,String lastName, Gender gender, Auth usersAuth) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.usersAuth = usersAuth;
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
        return usersAuth;
    }
    public void setUsersLogin(Auth usersAuth) {
        this.usersAuth = usersAuth;
    }
}
