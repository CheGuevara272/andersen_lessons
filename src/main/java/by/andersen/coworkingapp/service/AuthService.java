package by.andersen.coworkingapp.service;

import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.exception.LoginException;
import by.andersen.coworkingapp.model.entity.User;

public interface AuthService {
    User authorization(String login, String password) throws LoginException, InputException;
}
