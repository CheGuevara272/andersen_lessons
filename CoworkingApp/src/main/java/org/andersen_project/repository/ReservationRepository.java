package org.andersen_project.repository;

import org.andersen_project.entity.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ReservationRepository {
    private static List<Reservation> reservationList = new ArrayList<>();

    public static Reservation addReservation(Reservation reservation) {
        reservationList.add(reservation);
        return reservation;
    }

    public static List<Reservation> findAll() {
        return reservationList;
    }

    public static boolean deleteById(UUID id) {
        return reservationList.removeIf(reservation -> reservation.getReservationID().equals(id));
    }

    public static Optional<Reservation> findById(UUID id) {
        return reservationList.stream().filter(reservation -> reservation.getReservationID().equals(id)).findFirst();
    }
}
