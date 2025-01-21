package org.andersen_project.entity;

import java.io.Serializable;

public class User implements Serializable {
    private final String login;
    private Integer userId;
    private String name;
    private String password;
    private UserRole userRole;

    public User(Integer userId, String name, String login, String password, UserRole userRole) {
        this.userId = userId;
        this.name = name;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    public UserRole getRole() {
        return userRole;
    }

    public void getRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
