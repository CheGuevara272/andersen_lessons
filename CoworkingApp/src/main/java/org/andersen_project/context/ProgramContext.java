package org.andersen_project.context;

import org.andersen_project.repository.CoworkingRepository;
import org.andersen_project.repository.ReservationRepository;
import org.andersen_project.repository.UserRepository;

import java.io.*;
import java.util.List;

public class ProgramContext {
    public static final String USERS_FILE_NAME = "users.ser";
    public static final String COWORKING_FILE_NAME = "coworkingSpaces.ser";
    public static final String RESERVATION_FILE_NAME = "reservations.ser";


    public static RepositoryContext atProgramStart() {
        RepositoryContext context = new RepositoryContext();
        UserRepository userRepository = new UserRepository(loadContext(USERS_FILE_NAME));
        context.putRepository(userRepository);

        CoworkingRepository coworkingRepository = new CoworkingRepository(loadContext(COWORKING_FILE_NAME));
        context.putRepository(coworkingRepository);

        ReservationRepository reservationRepository = new ReservationRepository(loadContext(RESERVATION_FILE_NAME));
        context.putRepository(reservationRepository);
        return context;
    }

    public static void atProgramStop(RepositoryContext context) {
        saveContext(context.getRepository(UserRepository.class).findAll(), USERS_FILE_NAME);
        saveContext(context.getRepository(CoworkingRepository.class).findAll(), COWORKING_FILE_NAME);
        saveContext(context.getRepository(ReservationRepository.class).findAll(), RESERVATION_FILE_NAME);
    }

    public static <T> void saveContext(List<T> listToSave, String fileName) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(listToSave);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> loadContext(String fileName) {
        List<T> loadedList = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            loadedList = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return loadedList;
    }
}
