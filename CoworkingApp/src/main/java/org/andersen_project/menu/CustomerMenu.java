package org.andersen_project.menu;

import org.andersen_project.context.RepositoryContext;
import org.andersen_project.entity.CoworkingSpace;
import org.andersen_project.entity.User;
import org.andersen_project.exception.InputException;
import org.andersen_project.repository.CoworkingRepository;
import org.andersen_project.repository.ReservationRepository;
import org.andersen_project.service.impl.CoworkingServiceImpl;
import org.andersen_project.service.impl.ReservationServiceImpl;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CustomerMenu {
    static Scanner keyboard = new Scanner(System.in);
    private final CoworkingServiceImpl coworkingService;
    private final ReservationServiceImpl reservationService;

    public CustomerMenu(RepositoryContext repositoryContext) {
        CoworkingRepository coworkingRepository = (CoworkingRepository) repositoryContext.getRepository(CoworkingRepository.class);
        ReservationRepository reservationRepository = (ReservationRepository) repositoryContext.getRepository(ReservationRepository.class);

        this.coworkingService = new CoworkingServiceImpl(reservationRepository, coworkingRepository);
        this.reservationService = new ReservationServiceImpl(reservationRepository, coworkingRepository);
    }

    public void run(User user) throws InputException {
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

    private void listOfAvailableSpaces() {
        System.out.println("List of available spaces:");
        List<CoworkingSpace> availableSpaces = coworkingService.getAvailableCoworkingSpaces();
        System.out.println(availableSpaces);
    }

    public void cancelReservation(User user) throws InputException {
        listOfReservations(user);
        System.out.println("Enter a id of reservation that you want to cancel");
        UUID id = UUID.fromString(keyboard.nextLine());
        if (reservationService.cancelReservation(id)) {
            System.out.println("Reservation has been cancelled");
        } else {
            throw new InputException("Reservation with that id does not exist");
        }
    }

    public void makeReservation(User user) throws InputException {
        listOfAvailableSpaces();
        System.out.println("Enter the name of coworking space that you want to reserve");
        String coworkingName = keyboard.nextLine();
        if (reservationService.makeReservation(user, coworkingName)) {
            System.out.println("Reservation has been made");
        }
    }

    private void listOfReservations(User user) {
        System.out.println("Your list of reservations:");
        System.out.println(reservationService.getUserReservations(user.getUserId()));
    }
}
