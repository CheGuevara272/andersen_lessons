package andersen_project;

import andersen_project.exception.AuthorizationException;
import andersen_project.exception.InputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run {
    static Scanner keyboard = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static List<CoworkingSpace> spaces = new ArrayList<>();
    static List<Reservation> reservationList = new ArrayList<>();

    static final String USERS_FILE_NAME = "users.ser";
    static final String COWORKING_FILE_NAME = "coworkingSpaces.ser";
    static final String RESERVATION_FILE_NAME = "reservations.ser";

    public static void main(String[] args) {
        atProgramStart();
//        User customer = new User("customer", "customer", false);
//        User admin = new User("admin", "admin", true);
//        users.add(customer);
//        users.add(admin);

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
                    case "1" -> Authorization.authorization(Authorization.ADMIN);
                    case "2" -> Authorization.authorization(Authorization.CUSTOMER);
                    default -> operatingMenu = false;
                }
            } catch (AuthorizationException | InputException e) {
                System.out.println(e.getMessage());
            }
        }
        atProgramStop();
    }

    private static void atProgramStart() {
        users = ProgramState.loadState(USERS_FILE_NAME);
        spaces = ProgramState.loadState(COWORKING_FILE_NAME);
        reservationList = ProgramState.loadState(RESERVATION_FILE_NAME);
    }

    private static void atProgramStop() {
        ProgramState.saveState(users, USERS_FILE_NAME);
        ProgramState.saveState(spaces, COWORKING_FILE_NAME);
        ProgramState.saveState(reservationList, RESERVATION_FILE_NAME);
    }

}