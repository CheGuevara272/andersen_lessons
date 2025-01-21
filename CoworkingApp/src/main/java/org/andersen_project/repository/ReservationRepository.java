package org.andersen_project.repository;

import org.andersen_project.entity.Reservation;
import org.andersen_project.exception.InputException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository<T extends Serializable> implements Repository<Reservation> {
    private final List<Reservation> reservationList = new ArrayList<>();

    public ReservationRepository(List<Reservation> reservationList) {
        this.reservationList.addAll(reservationList);
    }

    @Override
    public boolean update(Reservation reservation) {
        return reservationList.add(reservation);
    }

    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>(reservationList);
        return reservations;
    }

    @Override
    public Reservation findById(Integer id) throws InputException {
        return reservationList.stream()
                .filter(reservation -> reservation.getReservationId().equals(id))
                .findFirst()
                .orElseThrow(() -> new InputException("Coworking space with that name is reserved or does not exist"));
    }

    @Override
    public boolean deleteById(Integer id) {
        return reservationList.removeIf(reservation -> reservation.getReservationId().equals(id));
    }

    public Integer getLastId() {
        return reservationList.get(reservationList.size() - 1).getReservationId();
    }
}
