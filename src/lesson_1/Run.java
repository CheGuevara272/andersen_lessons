package lesson_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Run {
    static Scanner keyboard = new Scanner(System.in);
    static List<User> users = new ArrayList<User>();
    static String ADMIN = "admin";
    static String CUSTOMER = "customer";

    public static void run(String[] args) {
        User customer = new User("customer", "customer", false);
        User admin = new User("admin", "admin", true);
        users.add(customer);
        users.add(admin);



        System.out.println("Welcome to Coworking Space Reservation Application");
        System.out.println("Choose one of the following options:");
        System.out.println("1. Admin Login");
        System.out.println("2. Customer Login");
        System.out.println("3. Exit");
        switch (keyboard.nextLine()) {
            case "1" -> {
                System.out.println("Enter Login");
                String login = keyboard.nextLine();
                System.out.println("Enter Password");
                String password = keyboard.nextLine();
                if (Objects.equals(CheckLogin(login, password), ADMIN)) {
                    AdminMenu();
                }
            }
            case "2" -> {
                System.out.println("Enter Login");
                String login = keyboard.nextLine();
                System.out.println("Enter Password");
                String password = keyboard.nextLine();
                if (Objects.equals(CheckLogin(login, password), CUSTOMER)) {
                    CustomerMenu();
                }
            }
            case "3" -> {
                System.exit(0);
            }
        }
    }

    private static String CheckLogin(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                if (user.isAdmin()) {
                    return ADMIN;
                } else {
                    return CUSTOMER;
                }
            } else {
                return "Wrong Login or Password";
            }
        }
        return login;
    }

    private static void AdminMenu() {}

    private static void CustomerMenu() {}
}