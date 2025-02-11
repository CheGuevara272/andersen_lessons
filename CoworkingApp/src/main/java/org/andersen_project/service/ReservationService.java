package org.andersen_project.service;

import org.andersen_project.entity.Reservation;
import org.andersen_project.entity.User;
import org.andersen_project.exception.InputException;

import java.util.List;

public interface ReservationService {
    boolean makeReservation(User user, String coworkingName) throws InputException;

    boolean cancelReservation(Integer reservationId) throws InputException;

    List<Reservation> getReservations();

    List<Reservation> getUserReservations(Integer userId);
}
