package org.andersen_project.menu;

import org.andersen_project.entity.CoworkingSpace;
import org.andersen_project.entity.Reservation;
import org.andersen_project.entity.User;
import org.andersen_project.exception.InputException;
import org.andersen_project.repository.CoworkingRepository;
import org.andersen_project.repository.ReservationRepository;

import java.util.Scanner;
import java.util.UUID;

public class CustomerMenu {
    static Scanner keyboard = new Scanner(System.in);
    public static void customerMenu(User user) throws InputException {
        boolean operatingMenu = true;
        while (operatingMenu) {
            System.out.println("""
                    Choose one of the following options:
                     1. Browse available spaces
                     2. Make a reservation
                     3. View my reservations
                     4. Cancel a reservation
                     5. Back""");
            switch (keyboard.nextLine()) {
                case "1" -> listOfAvailableSpaces();
                case "2" -> makeReservation(user);
                case "3" -> listOfReservations(user);
                case "4" -> cancelReservation(user);
                default -> operatingMenu = false;
            }
        }
    }

    private static void listOfAvailableSpaces() {
        System.out.println("List of available spaces:");
        CoworkingRepository.findAll().stream()
                .filter(CoworkingSpace::isNotReserved)
                .forEach(System.out::println);
    }

    public static void makeReservation(User user) throws InputException {
        listOfAvailableSpaces();
        System.out.println("Enter a coworking space name that you want to reserve");
        String spaceName = keyboard.nextLine();
        for (CoworkingSpace space : CoworkingRepository.findAll()) {
            if (space.getName().equals(spaceName) && space.isNotReserved()) {
                System.out.println("Enter your name");
                Reservation reservation = new Reservation(
                        CoworkingRepository.findByName(space.getName()),
                        user,
                        keyboard.nextLine());

                System.out.println("Enter the time you wish to reserve the coworking space");
                reservation.setReservationStartTime(keyboard.nextLine());

                System.out.println("Enter what time you expect to vacate the coworking space");
                reservation.setReservationEndTime(keyboard.nextLine());

                System.out.println("Enter reservation date");
                reservation.setReservationDate(keyboard.nextLine());

                ReservationRepository.addReservation(reservation);
                break;
            } else {
                throw new InputException("Coworking space with that name is reserved or does not exist");
            }
        }
    }

    private static void listOfReservations(User user) {
        System.out.println("Your list of reservations:");
        ReservationRepository.findAll().stream()
                .filter(reservation -> reservation.getReservee().equals(user))
                .forEach(System.out::println);
    }

    public static void cancelReservation(User user) throws InputException {
        listOfReservations(user);
        System.out.println("Enter a id of reservation that you want to cancel");
        UUID id = UUID.fromString(keyboard.nextLine());
        if (ReservationRepository.deleteById(id)) {
            System.out.println("Reservation has been cancelled");
        } else {
            throw new InputException("Reservation with that id does not exist");
        }
    }
}
