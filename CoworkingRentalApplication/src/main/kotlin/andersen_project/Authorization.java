package andersen_project;

import andersen_project.exception.AuthorizationException;
import andersen_project.exception.InputException;

import java.util.Objects;

public class Authorization {
    static final String ADMIN = "admin";
    static final String CUSTOMER = "customer";

    static void authorization(String userType) throws AuthorizationException, InputException {
        System.out.println("Enter Login");
        String login = Run.keyboard.nextLine();
        System.out.println("Enter Password");
        String password = Run.keyboard.nextLine();
        User user = checkLogin(login, password);
        if (userType.equals(ADMIN) && Objects.requireNonNull(user).isAdmin()) {
                AdminMenu.adminMenu();
        } else if (userType.equals(CUSTOMER) && !Objects.requireNonNull(user).isAdmin()) {
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
