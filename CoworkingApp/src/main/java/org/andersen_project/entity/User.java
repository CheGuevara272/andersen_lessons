package org.andersen_project.entity;

import org.andersen_project.util.IdGenerator;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private UUID userId;
    private String name;
    private final String login;
    private String password;
    private UserRole userRole;

    public User(String login, String password, UserRole userRole) {
        this.userId = IdGenerator.getInstance().generateUniqueId();
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

    public UUID getUserId() {
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
