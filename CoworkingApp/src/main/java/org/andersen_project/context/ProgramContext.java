package org.andersen_project.context;

import org.andersen_project.entity.CoworkingSpace;
import org.andersen_project.entity.Reservation;
import org.andersen_project.entity.User;
import org.andersen_project.repository.CoworkingRepository;
import org.andersen_project.repository.ReservationRepository;
import org.andersen_project.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;
import java.util.List;

public class ProgramContext {
    private RepositoryContext context;


    public static RepositoryContext atProgramStart() {
        RepositoryContext context = new RepositoryContext();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        UserRepository userRepository = new UserRepository(sessionFactory, User.class);
        ReservationRepository reservationRepository = new ReservationRepository(sessionFactory, Reservation.class);
        CoworkingRepository coworkingRepository = new CoworkingRepository(sessionFactory, CoworkingSpace.class);

        context.putRepository(userRepository);
        context.putRepository(reservationRepository);
        context.putRepository(coworkingRepository);

        return context;
    }

    public static void atProgramStop(RepositoryContext context) {
    }

    public static <T> void saveContext(List<T> listToSave, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(listToSave);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> loadContext(String fileName) {
        List<T> loadedList = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
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
