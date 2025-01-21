package org.andersen_project.service;

import org.andersen_project.entity.Reservation;
import org.andersen_project.entity.User;
import org.andersen_project.exception.InputException;

import java.util.List;

public interface ReservationService {
    List<Reservation> getReservations();

    List<Reservation> getUserReservations(Integer userId);

    boolean cancelReservation(Integer reservationId);

    boolean makeReservation(User user, String coworkingName) throws InputException;
}
