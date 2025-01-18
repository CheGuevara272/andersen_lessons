package org.andersen_project.menu;

import org.andersen_project.entity.User;
import org.andersen_project.entity.UserType;
import org.andersen_project.exception.AuthorizationException;
import org.andersen_project.exception.InputException;
import org.andersen_project.repository.CoworkingRepository;
import org.andersen_project.repository.ReservationRepository;
import org.andersen_project.service.Authorization;
import org.andersen_project.util.ProgramState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run {
    static Scanner keyboard = new Scanner(System.in);
    public static List<User> users = new ArrayList<>();

    static final String USERS_FILE_NAME = "users.ser";
    static final String COWORKING_FILE_NAME = "coworkingSpaces.ser";
    static final String RESERVATION_FILE_NAME = "reservations.ser";

    public static void main(String[] args) {
        atProgramStart();
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
                    case "1" -> Authorization.authorization(UserType.ADMIN);
                    case "2" -> Authorization.authorization(UserType.CUSTOMER);
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
        CoworkingRepository.spaces = ProgramState.loadState(COWORKING_FILE_NAME);
        ReservationRepository.reservationList = ProgramState.loadState(RESERVATION_FILE_NAME);
    }

    private static void atProgramStop() {
        ProgramState.saveState(users, USERS_FILE_NAME);
        ProgramState.saveState(CoworkingRepository.spaces, COWORKING_FILE_NAME);
        ProgramState.saveState(ReservationRepository.reservationList, RESERVATION_FILE_NAME);
    }

}