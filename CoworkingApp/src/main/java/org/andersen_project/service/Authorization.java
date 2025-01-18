package org.andersen_project.service;

import org.andersen_project.entity.UserType;
import org.andersen_project.menu.Run;
import org.andersen_project.entity.User;
import org.andersen_project.exception.AuthorizationException;
import org.andersen_project.exception.InputException;
import org.andersen_project.menu.AdminMenu;
import org.andersen_project.menu.CustomerMenu;

import java.util.Objects;
import java.util.Scanner;

public class Authorization {

    public static void authorization(UserType userType) throws AuthorizationException, InputException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Login");
        String login = keyboard.nextLine();
        System.out.println("Enter Password");
        String password = keyboard.nextLine();
        User user = checkLogin(login, password);
        if (userType.equals(UserType.ADMIN) && Objects.requireNonNull(user).isAdmin()) {
                AdminMenu.adminMenu();
        } else if (userType.equals(UserType.CUSTOMER) && !Objects.requireNonNull(user).isAdmin()) {
                CustomerMenu.customerMenu(user);
        }
    }

    private static User checkLogin(String login, String password) throws AuthorizationException {
        for (User user : Run.users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new AuthorizationException("Invalid Login or Password");
    }
}
