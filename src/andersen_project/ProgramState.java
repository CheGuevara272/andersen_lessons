package andersen_project;

import java.io.*;
import java.util.List;

public class ProgramState {
    static final String USERS_FILE_NAME = "users.ser";
    static final String COWORKING_FILE_NAME = "coworkingSpaces.ser";
    static final String RESERVATION_FILE_NAME = "reservations.ser";

    private static  <T> void saveState(List<T> listToSave, String fileName) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(listToSave);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static  <T> List<T> loadState(String fileName) {
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

    static void atProgramStart() {
        Run.users = loadState(USERS_FILE_NAME);
        Run.spaces = loadState(COWORKING_FILE_NAME);
        Run.reservationList = loadState(RESERVATION_FILE_NAME);
    }

    static void atProgramStop() {
        saveState(Run.users, USERS_FILE_NAME);
        saveState(Run.spaces, COWORKING_FILE_NAME);
        saveState(Run.reservationList, RESERVATION_FILE_NAME);
    }
}
