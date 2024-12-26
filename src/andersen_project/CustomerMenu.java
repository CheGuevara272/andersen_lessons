package andersen_project;

import andersen_project.exception.InputException;

public class CustomerMenu {
    static void customerMenu(User user) throws InputException {
        boolean operatingMenu = true;
        while (operatingMenu) {
            System.out.println("""
                    Choose one of the following options:
                     1. Browse available spaces
                     2. Make a reservation
                     3. View my reservations
                     4. Cancel a reservation
                     5. Back""");
            switch (Run.keyboard.nextLine()) {
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
        for (CoworkingSpace space : Run.spaces) {
            if (space.isNotReserved()) {
                System.out.println(space);
            }
        }
    }

    private static void makeReservation(User user) throws InputException {
        listOfAvailableSpaces();
        System.out.println("Enter a coworking space name that you want to reserve");
        String spaceName = Run.keyboard.nextLine();
        for (CoworkingSpace space : Run.spaces) {
            if (space.getName().equals(spaceName) && space.isNotReserved()) {
                System.out.println("Enter your name");
                Reservation reservation = new Reservation(space, user, Run.keyboard.nextLine());
                Run.reservationList.add(reservation);

                System.out.println("Enter the time you wish to reserve the coworking space");
                reservation.setReservationStartTime(Run.keyboard.nextLine());

                System.out.println("Enter what time you expect to vacate the coworking space");
                reservation.setReservationEndTime(Run.keyboard.nextLine());

                System.out.println("Enter reservation date");
                reservation.setReservationDate(Run.keyboard.nextLine());
                break;
            } else {
                throw new InputException("Coworking space with that name is reserved or does not exist");
            }
        }
    }

    private static void listOfReservations(User user) {
        System.out.println("Your list of reservations:");
        for (Reservation reservation : Run.reservationList) {
            if (user.equals(reservation.getReservee())) {
                System.out.println(reservation);
            }
        }
    }

    private static void cancelReservation(User user) throws InputException {
        listOfReservations(user);
        System.out.println("Enter a id of reservation that you want to cancel");
        String id = Run.keyboard.nextLine();
        boolean removed = Run.reservationList.removeIf(reservation -> id.equals(reservation.getReservationID()));
        if (removed) {
            System.out.println("Reservation has been cancelled");
        } else {
            throw new InputException("Reservation with that id does not exist");
        }
    }
}
