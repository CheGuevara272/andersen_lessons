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
                    User user = checkLogin(login, password);
                    if (Objects.requireNonNull(user).isAdmin()) {
                        adminMenu();
                    } else {
                        System.out.println("Invalid Login or Password");
                    }
                }
                case "2" -> {
                    System.out.println("Enter Login");
                    String login = keyboard.nextLine();
                    System.out.println("Enter Password");
                    String password = keyboard.nextLine();
                    User user = checkLogin(login, password);
                    if (!Objects.requireNonNull(user).isAdmin()) {
                        customerMenu(user);
                    } else {
                        System.out.println("Invalid Login or Password");
                    }
                }
                default -> {
                    return;
                }
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

    private static void adminMenu() {
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
                        default -> System.out.println("Invalid type");
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
                        System.out.println(reservation);
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

    private static void customerMenu(User user) {
        while (true) {
            System.out.println("Choose one of the following options:");
            System.out.println("1. Browse available spaces");
            System.out.println("2. Make a reservation");
            System.out.println("3. View my reservations");
            System.out.println("4. Cancel a reservation");
            System.out.println("5. Back");
            switch (keyboard.nextLine()) {
                case "1" -> {
                    System.out.println("List of available spaces:");
                    for (CoworkingSpace space : spaces) {
                        if (!space.isReserved()) {
                            System.out.println(space);
                        }
                    }
                }
                case "2" -> {
                    System.out.println("Enter a coworking space name that you want to reserve");
                    String spaceName = keyboard.nextLine();
                    for (CoworkingSpace space : spaces) {
                        if (space.getName().equals(spaceName) && !space.isReserved()) {
                            System.out.println("Enter your name");
                            Reservation reservation = new Reservation(space, user, keyboard.nextLine());
                            reservationList.add(reservation);
                            System.out.println("Enter the time you wish to reserve the coworking space");
                            reservation.setReservationStartTime(keyboard.nextLine());
                            System.out.println("Enter what time you expect to vacate the coworking space");
                            reservation.setReservationEndTime(keyboard.nextLine());
                            System.out.println("Enter reservation date");
                            reservation.setReservationDate(keyboard.nextLine());
                            break;
                        }
                    }
                }
                case "3" -> {
                    System.out.println("Your list of reservations:");
                    for (Reservation reservation : reservationList) {
                        if (user.equals(reservation.getReservee())) {
                            System.out.println(reservation);
                        }
                    }
                }
                case "4" -> {
                    System.out.println("Enter a id of reservation that you want to cancel");
                    String id = keyboard.nextLine();
                    reservationList.removeIf(reservation -> id.equals(reservation.getReservationID()));
                }
                default -> {
                    return;
                }
            }
        }
    }
}