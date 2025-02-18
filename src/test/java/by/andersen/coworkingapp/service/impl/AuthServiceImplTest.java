package by.andersen.coworkingapp.service.impl;

import by.andersen.coworkingapp.exception.LoginException;
import by.andersen.coworkingapp.model.dto.RegistrationRequest;
import by.andersen.coworkingapp.model.entity.User;
import by.andersen.coworkingapp.model.enums.UserRole;
import by.andersen.coworkingapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private AuthServiceImpl authService;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        authService = new AuthServiceImpl(userRepository);
    }

    @Test
    void registerUser_ShouldSaveUserWithEncodedPassword_WhenLoginIsUnique() throws LoginException {
        RegistrationRequest dto = new RegistrationRequest("newUser", "rawPassword", UserRole.ADMIN);
        when(userRepository.existsByLogin(dto.getLogin())).thenReturn(false);

        authService.registerUser(dto);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals(dto.getLogin(), savedUser.getLogin());
        assertEquals(UserRole.ADMIN, savedUser.getUserRole());
        assertTrue(passwordEncoder.matches(dto.getPassword(), savedUser.getPassword()));
    }

    @Test
    void registerUser_ShouldThrowException_WhenLoginExists() {
        RegistrationRequest dto = new RegistrationRequest("existingUser", "anyPass", UserRole.ADMIN);
        when(userRepository.existsByLogin(dto.getLogin())).thenReturn(true);

        LoginException exception = assertThrows(LoginException.class,
                () -> authService.registerUser(dto)
        );

        assertEquals("Login already exists", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void registerUser_ShouldUseBCryptEncoding_ForPassword() throws LoginException {
        RegistrationRequest dto = new RegistrationRequest("user", "plainPassword", UserRole.ADMIN);
        when(userRepository.existsByLogin(dto.getLogin())).thenReturn(false);

        authService.registerUser(dto);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertNotEquals(dto.getPassword(), savedUser.getPassword());
        assertTrue(savedUser.getPassword().startsWith("$2a$"));
    }
}