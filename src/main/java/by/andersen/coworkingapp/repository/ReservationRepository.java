package by.andersen.coworkingapp.repository;

import by.andersen.coworkingapp.model.entity.Reservation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReservationRepository extends HibernateRepository<Reservation> {
    @Autowired
    public ReservationRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Reservation.class);
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
