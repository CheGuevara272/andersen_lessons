package by.andersen.coworkingapp.service.impl;

import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.exception.LoginException;
import by.andersen.coworkingapp.model.entity.User;
import by.andersen.coworkingapp.repository.UserRepository;
import by.andersen.coworkingapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authorization(String login, String password) throws LoginException, InputException {
        List<User> usersList = userRepository.findByName(login);
        for (User user : usersList) {
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                throw new LoginException("Invalid Login");
            }
        }
        return null;
    }
}
