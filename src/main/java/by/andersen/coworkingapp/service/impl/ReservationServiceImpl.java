package by.andersen.coworkingapp.service.impl;

import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.model.entity.CoworkingSpace;
import by.andersen.coworkingapp.model.entity.Reservation;
import by.andersen.coworkingapp.model.entity.User;
import by.andersen.coworkingapp.repository.CoworkingSpaceRepository;
import by.andersen.coworkingapp.repository.ReservationRepository;
import by.andersen.coworkingapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final CoworkingSpaceRepository coworkingSpaceRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, CoworkingSpaceRepository coworkingSpaceRepository) {
        this.reservationRepository = reservationRepository;
        this.coworkingSpaceRepository = coworkingSpaceRepository;
    }

    @Override
    public List<Reservation> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations;
    }

    @Override
    public List<Reservation> getUserReservations(Integer userId) {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream().filter(reservation -> reservation.getUser().getUserId().equals(userId)).toList();
    }

    @Override
    public boolean cancelReservation(Integer reservationId) throws InputException {
        boolean result = reservationRepository.findById(reservationId).isPresent();
        reservationRepository.deleteById(reservationId);
        return result;
    }

    @Override
    public boolean makeReservation(User user, String coworkingName) {
        List<CoworkingSpace> coworkingsList = coworkingSpaceRepository.findByCoworkingName(coworkingName);
        if (coworkingsList.isEmpty()) {
            return false;
        }

        CoworkingSpace coworkingSpace = coworkingsList.get(0);
        Reservation reservation = Reservation.builder()
                .reservationId(0)
                .user(user)
                .space(coworkingSpace)
                .build();

        reservationRepository.save(reservation);
        return true;
    }
}
