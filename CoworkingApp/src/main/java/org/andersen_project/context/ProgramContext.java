package org.andersen_project.context;

import org.andersen_project.entity.*;
import org.andersen_project.repository.CoworkingRepository;
import org.andersen_project.repository.ReservationRepository;
import org.andersen_project.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramContext {
    public static final String USERS_FILE_PATH = "CoworkingApp/src/main/resources/users.ser";
    public static final String COWORKING_FILE_PATH = "CoworkingApp/src/main/resources/coworkingSpaces.ser";
    public static final String RESERVATION_FILE_PATH = "CoworkingApp/src/main/resources/reservations.ser";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "Ernesto";
    private static final String USER_QUERY = "SELECT * FROM users";
    private static final String RESERVATION_QUERY = "SELECT * FROM reservations";
    private static final String COWORKING_QUERY = "SELECT * FROM coworkings";

    public static RepositoryContext atProgramStart() {
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet userSet = stmt.executeQuery(USER_QUERY);
            ResultSet reservationSet = stmt.executeQuery(RESERVATION_QUERY);
            ResultSet coworkingSet = stmt.executeQuery(COWORKING_QUERY);

            RepositoryContext context = new RepositoryContext();
            UserRepository userRepository = new UserRepository(loadUserContext(userSet));
            context.putRepository(userRepository);

            CoworkingRepository coworkingRepository = new CoworkingRepository(loadReservationContext(reservationSet));
            context.putRepository(coworkingRepository);

            ReservationRepository reservationRepository = new ReservationRepository(loadCoworkingSpaceContext(coworkingSet));
            context.putRepository(reservationRepository);
            con.close();
            return context;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void atProgramStop(RepositoryContext context) {
//        saveContext(context.getRepository(UserRepository.class).findAll(), USERS_FILE_PATH);
//        saveContext(context.getRepository(CoworkingRepository.class).findAll(), COWORKING_FILE_PATH);
//        saveContext(context.getRepository(ReservationRepository.class).findAll(), RESERVATION_FILE_PATH);
//    }

    private static List<User> loadUserContext(ResultSet userSet) {
        List<User> users = new ArrayList<>();
        while (true) {
            try {
                if (!userSet.next()) break;

                Integer userId = userSet.getInt("id");
                String name = userSet.getString("name");
                String login = userSet.getString("login");
                String password = userSet.getString("password");
                String role = userSet.getString("role");

                User user = new User(userId, name, login, password, UserRole.valueOf(role));
                users.add(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return users;
    }

    private static List<Reservation> loadReservationContext(ResultSet reservationSet) {
        List<Reservation> reservations = new ArrayList<>();
        while (true) {
            try {
                if (!reservationSet.next()) break;

                Integer reservationId = reservationSet.getInt("reservationid");
                Integer userId = reservationSet.getInt("userid");
                Integer coworkingId = reservationSet.getInt("coworkingid");

                Reservation reservation = new Reservation(reservationId, userId, coworkingId);
                reservations.add(reservation);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return reservations;
    }

    private static List<CoworkingSpace> loadCoworkingSpaceContext(ResultSet coworkingSet) {
        List<CoworkingSpace> coworkingSpaces = new ArrayList<>();
        while (true) {
            try {
                if (!coworkingSet.next()) break;

                Integer spaceId = coworkingSet.getInt("id");
                String name = coworkingSet.getString("coworkingname");
                String coworkingType = coworkingSet.getString("coworkingtype");
                double price = coworkingSet.getDouble("price");

                CoworkingSpace coworkingSpace = new CoworkingSpace(spaceId, name, CoworkingType.valueOf(coworkingType), price);
                coworkingSpaces.add(coworkingSpace);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return coworkingSpaces;
    }


//    public static <T> void saveContext(List<T> listToSave, String fileName) {
//        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
//            oos.writeObject(listToSave);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    public static <T> List<T> loadContext(String fileName) {
//        List<T> loadedList = null;
//
//
//        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
//            loadedList = (List<T>) ois.readObject();
//        } catch (ClassNotFoundException | IOException e) {
//            throw new RuntimeException(e);
//        }
//        return loadedList;
//    }
}
