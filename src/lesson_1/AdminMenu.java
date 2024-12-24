package lesson_1;

public class AdminMenu {
    static void adminMenu() {
        boolean operatingMenu = true;
        while (operatingMenu) {
            System.out.println("""
                    Choose one of the following options:");
                     1. Add new coworking space
                     2. Remove coworking space
                     3. View all reservations
                     4. Back""");
            switch (Run.keyboard.nextLine()) {
                case "1" -> {
                    addNewCoworking();
                }
                case "2" -> {
                    removeCoworking();
                }
                case "3" -> {
                    reservationsList();
                }
                default -> {
                    operatingMenu = false;
                }
            }
        }
    }

    private static void addNewCoworking() {
        System.out.println("Enter coworking space name");
        String spaceName = Run.keyboard.nextLine();
        System.out.println("Choose coworking space type");
        printTypes();
        Type type = null;
        switch (Run.keyboard.nextLine()) {
            case "1" -> type = Type.OpenSpace;
            case "2" -> type = Type.Private;
            case "3" -> type = Type.Minimal;
            case "4" -> type = Type.FullService;
            default -> System.out.println("Invalid type");
        }
        System.out.println("Enter coworking price");
        double price = Double.parseDouble(Run.keyboard.nextLine());
        CoworkingSpace space = new CoworkingSpace(spaceName, type, price);
        Run.spaces.add(space);
    }

    private static void removeCoworking() {
        System.out.println("Choose coworking that you want to delete");
        printCoworkingSpaces();
        Run.spaces.remove(Run.keyboard.nextInt() - 1);
    }

    private static void reservationsList() {
        System.out.println("Reservations list:");
        for (Reservation reservation : Run.reservationList) {
            System.out.println(reservation);
        }
    }

    private static void printTypes() {
        int i = 0;
        for (Type type : Type.values()) {
            System.out.println(++i + ") " + type.toString());
        }
    }

    private static void printCoworkingSpaces() {
        for (CoworkingSpace space : Run.spaces) {
            System.out.println("1) " + space);
        }
    }
}
