package org.andersen_project.menu;

import org.andersen_project.context.RepositoryContext;
import org.andersen_project.entity.User;
import org.andersen_project.exception.InputException;
import org.andersen_project.repository.CoworkingRepository;
import org.andersen_project.repository.ReservationRepository;
import org.andersen_project.service.impl.CoworkingServiceImpl;
import org.andersen_project.service.impl.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class AdminMenu {
    static Scanner keyboard = new Scanner(System.in);
    private final CoworkingServiceImpl coworkingService;
    private final ReservationServiceImpl reservationService;

    @Autowired
    public AdminMenu(CoworkingServiceImpl coworkingService, ReservationServiceImpl reservationService) {
        this.coworkingService = coworkingService;
        this.reservationService = reservationService;
    }

    public void run(User user) throws InputException {
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
                case "3" -> reservationService.getReservations();
                default -> operatingMenu = false;
            }
        }
    }

    public void addNewCoworking() throws InputException {
        if (coworkingService.addCoworking()) {
            System.out.println("Coworking added");
        }
    }

    public void removeCoworking() throws InputException {
        coworkingService.removeCoworking();
        System.out.println("Coworking removed");
    }
}
