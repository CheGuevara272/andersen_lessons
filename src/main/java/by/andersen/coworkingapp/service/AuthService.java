package by.andersen.coworkingapp.service;

import by.andersen.coworkingapp.exception.LoginException;
import by.andersen.coworkingapp.model.dto.RegistrationRequest;

public interface AuthService {
    void registerUser(RegistrationRequest dto) throws LoginException;
}
