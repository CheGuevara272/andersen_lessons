package org.andersen_project.service.impl;

import org.andersen_project.entity.User;
import org.andersen_project.exception.InputException;
import org.andersen_project.exception.LoginException;
import org.andersen_project.repository.UserRepository;
import org.andersen_project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Autowired
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
        Optional<User> optionalUser = checkLogin(login, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user;
        } else {
            throw new LoginException("Invalid Login");
        }
    }

    public Optional<User> checkLogin(String login, String password) throws LoginException {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        } else {
            throw new LoginException("Wrong password");
        }
    }
}
