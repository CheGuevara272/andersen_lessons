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
        while (true) {
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
                    } else {
                        System.out.println("Invalid Login or Password");
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
                default -> {
                    return;
                }
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
            }
        }
        return null;
    }

    private static void AdminMenu() {
        while (true) {
            System.out.println("Choose one of the following options:");
            System.out.println("1. Add new coworking space");
            System.out.println("2. Remove coworking space");
            System.out.println("3. View all reservations");
            System.out.println("4. Back");
            switch (keyboard.nextLine()) {
                case "1" -> {
                    System.out.println("Enter coworking space name");
                    String spaceName = keyboard.nextLine();
                    System.out.println("Choose coworking space type");
                    printTypes();
                    Type type = null;
                    switch (keyboard.nextLine()) {
                        case "1" -> type = Type.OpenSpace;
                        case "2" -> type = Type.Private;
                        case "3" -> type = Type.Minimal;
                        case "4" -> type = Type.FullService;
                    }
                    System.out.println("Enter coworking price");
                    double price = Double.parseDouble(keyboard.nextLine());
                    CoworkingSpace space = new CoworkingSpace(spaceName, type, price);
                    spaces.add(space);
                }
                case "2" -> {
                    System.out.println("Choose coworking that you want to delete");
                    printTypes();
                    spaces.remove(keyboard.nextInt() - 1);
                }
                case "3" -> {
                    System.out.println("Reservations list:");
                    for (Reservation reservation : reservationList) {
                        reservation.toString();
                    }
                }
                default -> {
                    return;
                }
            }
        }
    }

    private static void printTypes() {
        int i = 0;
        for (Type type : Type.values()) {
            System.out.println(++i + ") " + type.toString());
        }
    }

    private static void CustomerMenu() {
        while (true) {
            System.out.println("Choose one of the following options:");
            System.out.println("1. Browse available spaces");
            System.out.println("2. Make a reservation");
            System.out.println("3. View my reservations");
            System.out.println("4. Cancel a reservation");
            System.out.println("5. Back");
            switch (keyboard.nextLine()) {
                case "1" -> {
                    System.out.println("Enter coworking space name");
                    String spaceName = keyboard.nextLine();
                    System.out.println("Choose coworking space type");
                    printTypes();
                    Type type = null;
                    switch (keyboard.nextLine()) {
                        case "1" -> type = Type.OpenSpace;
                        case "2" -> type = Type.Private;
                        case "3" -> type = Type.Minimal;
                        case "4" -> type = Type.FullService;
                    }
                    System.out.println("Enter coworking price");
                    double price = Double.parseDouble(keyboard.nextLine());
                    CoworkingSpace space = new CoworkingSpace(spaceName, type, price);
                    spaces.add(space);
                }
                case "2" -> {
                    System.out.println("Choose coworking that you want to delete");
                    printTypes();
                    spaces.remove(keyboard.nextInt() - 1);
                }
                case "3" -> {
                    System.out.println("Reservations list:");
                    for (Reservation reservation : reservationList) {
                        reservation.toString();
                    }
                }
                case "4" -> {
                    System.out.println("Cancel a reservation");
                }
                default -> {
                    return;
                }
            }
        }
    }
}