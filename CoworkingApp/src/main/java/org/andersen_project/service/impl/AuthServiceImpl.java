package org.andersen_project.service.impl;

import org.andersen_project.entity.User;
import org.andersen_project.exception.InputException;
import org.andersen_project.exception.LoginException;
import org.andersen_project.repository.UserRepository;
import org.andersen_project.service.AuthService;

import java.util.List;
import java.util.Scanner;

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authorization() throws LoginException, InputException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Login");
        String login = keyboard.nextLine();
        System.out.println("Enter Password");
        String password = keyboard.nextLine();
        User user = checkLogin(login, password);
        return user;
    }

    public User checkLogin(String login, String password) throws LoginException {
        User user = userRepository.findByLogin(login);
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new LoginException("Wrong password");
        }
    }
}
