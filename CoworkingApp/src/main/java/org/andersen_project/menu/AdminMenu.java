package org.andersen_project.menu;

import org.andersen_project.entity.CoworkingType;
import org.andersen_project.entity.CoworkingSpace;
import org.andersen_project.entity.Reservation;
import org.andersen_project.exception.InputException;

import java.util.Scanner;

public class AdminMenu {
    static Scanner keyboard = new Scanner(System.in);
    public static void adminMenu() throws InputException {
        boolean operatingMenu = true;
        while (operatingMenu) {
            System.out.println("""
                    Choose one of the following options:
                     1. Add new coworking space
                     2. Remove coworking space
                     3. View all reservations
                     4. Back""");
            switch (keyboard.nextLine()) {
                case "1" -> addNewCoworking();
                case "2" -> removeCoworking();
                case "3" -> reservationsList();
                default -> operatingMenu = false;
            }
        }
    }

    public static void addNewCoworking() throws InputException {
        System.out.println("Enter coworking space name");
        String spaceName = keyboard.nextLine();
        System.out.println("Choose coworking space type");
        printTypes();
        CoworkingType coworkingType;

        switch (keyboard.nextLine()) {
            case "1" -> coworkingType = CoworkingType.OPEN_SPACE;
            case "2" -> coworkingType = CoworkingType.PRIVATE;
            case "3" -> coworkingType = CoworkingType.MINIMAL;
            case "4" -> coworkingType = CoworkingType.FULL_SERVICE;
            default -> throw new InputException("Invalid type");
        }
        System.out.println("Enter coworking price");
        double price = keyboard.nextDouble();
        CoworkingSpace space = new CoworkingSpace(spaceName, coworkingType, price);
        Run.spaces.add(space);
    }

    public static void removeCoworking() throws InputException {
        System.out.println("Choose coworking that you want to delete");
        printCoworkingSpaces();
        int coworkingNumber = keyboard.nextInt();
        if (Run.spaces.size() >= coworkingNumber) {
            Run.spaces.remove(keyboard.nextInt() - 1);
        } else {
            throw new InputException("Invalid coworking number");
        }

    }

    private static void reservationsList() {
        System.out.println("Reservations list:");
        for (Reservation reservation : Run.reservationList) {
            System.out.println(reservation);
        }
    }

    private static void printTypes() {
        int i = 0;
        for (CoworkingType coworkingType : CoworkingType.values()) {
            System.out.println(++i + ") " + coworkingType.toString());
        }
    }

    private static void printCoworkingSpaces() {
        int i = 0;
        for (CoworkingSpace space : Run.spaces) {
            i++;
            System.out.println(i + ") " + space);
        }
    }
}
