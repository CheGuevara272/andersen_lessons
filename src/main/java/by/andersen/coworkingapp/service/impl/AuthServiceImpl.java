package by.andersen.coworkingapp.service.impl;

import by.andersen.coworkingapp.exception.LoginException;
import by.andersen.coworkingapp.model.dto.RegistrationRequest;
import by.andersen.coworkingapp.model.entity.User;
import by.andersen.coworkingapp.model.enums.UserRole;
import by.andersen.coworkingapp.repository.UserRepository;
import by.andersen.coworkingapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registerUser(RegistrationRequest dto) throws LoginException {
        if (userRepository.existsByLogin(dto.getLogin())) {
            throw new LoginException("Login already exists");
        }

        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setUserRole(dto.getUserRole());
        userRepository.save(user);
    }
}
