package by.andersen.coworkingapp.service;

import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.model.entity.Reservation;
import by.andersen.coworkingapp.model.entity.User;

import java.util.List;

public interface ReservationService {
    boolean makeReservation(User user, String coworkingName) throws InputException;

    boolean cancelReservation(Integer reservationId) throws InputException;

    List<Reservation> getReservations();

    List<Reservation> getUserReservations(Integer userId);
}
