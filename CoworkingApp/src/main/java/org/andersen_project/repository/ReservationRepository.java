package org.andersen_project.repository;

import org.andersen_project.entity.Reservation;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationRepository extends HibernateRepository<Reservation> {
    public ReservationRepository(SessionFactory sessionFactory, Class<Reservation> entityClass) {
        super(sessionFactory, entityClass);
    }

    public List<Reservation> findReservationsByReserveeId(Integer reserveeId) {
        List<Reservation> reservations = this.findAll();
        return reservations.stream()
                .filter(reservation -> reservation.getUser().getUserId().equals(reserveeId))
                .collect(Collectors.toList());
    }

    public List<Reservation> findReservationsByCoworkingId(Integer coworkingId) {
        List<Reservation> reservations = this.findAll();
        return reservations.stream()
                .filter(reservation -> reservation.getSpace().getCoworkingId().equals(coworkingId))
                .collect(Collectors.toList());
    }
}
