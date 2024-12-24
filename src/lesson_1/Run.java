package lesson_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Run {
    static Scanner keyboard = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static List<CoworkingSpace> spaces = new ArrayList<>();
    static List<Reservation> reservationList = new ArrayList<>();
    static String ADMIN = "admin";
    static String CUSTOMER = "customer";

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
            switch (keyboard.nextLine()) {
                case "1" -> {
                    authorization(ADMIN);
                }
                case "2" -> {
                    authorization(CUSTOMER);
                }
                default -> {
                    operatingMenu = false;
                }
            }
        }
    }

    private static void authorization(String userType) {
        System.out.println("Enter Login");
        String login = keyboard.nextLine();
        System.out.println("Enter Password");
        String password = keyboard.nextLine();
        User user = checkLogin(login, password);
        if (userType.equals(ADMIN)) {
            if (Objects.requireNonNull(user).isAdmin()) {
                AdminMenu.adminMenu();
            } else {
                System.out.println("Invalid Login or Password");
            }
        } else if (userType.equals(CUSTOMER)) {
            if (!Objects.requireNonNull(user).isAdmin()) {
                CustomerMenu.customerMenu(user);
            } else {
                System.out.println("Invalid Login or Password");
            }
        }
    }

    private static User checkLogin(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}