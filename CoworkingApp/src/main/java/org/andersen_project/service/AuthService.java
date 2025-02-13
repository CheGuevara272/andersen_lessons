package org.andersen_project.service;

import org.andersen_project.entity.User;
import org.andersen_project.exception.InputException;
import org.andersen_project.exception.LoginException;

import java.util.Optional;

public interface AuthService {
    User authorization() throws LoginException, InputException;

    Optional<User> checkLogin(String login, String password) throws LoginException, InputException;
}
