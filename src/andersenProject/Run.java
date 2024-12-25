package andersenProject;

import andersenProject.exception.AuthorizationException;
import andersenProject.exception.InputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run {
    static Scanner keyboard = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static List<CoworkingSpace> spaces = new ArrayList<>();
    static List<Reservation> reservationList = new ArrayList<>();

    public static void main(String[] args) {
        User customer = new User("customer", "customer", false);
        User admin = new User("admin", "admin", true);
        users.add(customer);
        users.add(admin);

        System.out.println("Welcome to Coworking Space Reservation Application");
        boolean operatingMenu = true;
        while (operatingMenu) {
            System.out.println("""
                    Choose one of the following options:
                     1. Admin Login
                     2. Customer Login
                     3. Exit""");
            try {
                switch (keyboard.nextLine()) {
                    case "1" -> {
                        Authorization.authorization(Authorization.ADMIN);
                    }
                    case "2" -> {
                        Authorization.authorization(Authorization.CUSTOMER);
                    }
                    default -> {
                        operatingMenu = false;
                    }
                }
            } catch (AuthorizationException | InputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}