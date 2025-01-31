package org.andersen_project.service.impl;

import org.andersen_project.entity.User;
import org.andersen_project.entity.UserRole;
import org.andersen_project.exception.InputException;
import org.andersen_project.exception.LoginException;
import org.andersen_project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplTest {
    private AuthServiceImpl authServiceImpl;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        this.userRepository = Mockito.mock(UserRepository.class);
        this.authServiceImpl = new AuthServiceImpl(userRepository);
    }

    @Test
    void givenValidLoginAndPassword() {//checkLogin() {
        String login = "validLogin";
        String password = "validPassword";

        User user = new User(1, "test", "validLogin", "validPassword", UserRole.ADMIN);


        try {
            Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }

        try {
            Assertions.assertEquals(authServiceImpl.checkLogin(login, password), user);
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void givenInvalidLoginAndValidPassword() {
        String login = "invalidLogin";
        String password = "validPassword";

        String expectedException = "Wrong login";

        try {
            Mockito.when(userRepository.findByLogin(login)).thenThrow(new LoginException("Wrong login"));
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }

        LoginException e = assertThrows(LoginException.class, () -> authServiceImpl.checkLogin(login, password));
        Assertions.assertEquals(expectedException, e.getMessage());
    }

    @Test
    void givenValidLoginAndInvalidPassword() {
        String login = "validLogin";
        String password = "invalidPassword";

        User user = new User(1, "test", "validLogin", "validPassword", UserRole.ADMIN);

        String expectedException = "Wrong password";

        try {
            Mockito.when(userRepository.findByLogin(login)).thenReturn(user);
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }

        LoginException e = assertThrows(LoginException.class, () -> authServiceImpl.checkLogin(login, password));
        Assertions.assertEquals(expectedException, e.getMessage());
    }
}