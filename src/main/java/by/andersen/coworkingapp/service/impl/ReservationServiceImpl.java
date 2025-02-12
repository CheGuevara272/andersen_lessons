package by.andersen.coworkingapp.service.impl;

import by.andersen.coworkingapp.exception.InputException;
import by.andersen.coworkingapp.model.entity.CoworkingSpace;
import by.andersen.coworkingapp.model.entity.Reservation;
import by.andersen.coworkingapp.model.entity.User;
import by.andersen.coworkingapp.repository.CoworkingRepository;
import by.andersen.coworkingapp.repository.ReservationRepository;
import by.andersen.coworkingapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final CoworkingRepository coworkingRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, CoworkingRepository coworkingRepository) {
        this.reservationRepository = reservationRepository;
        this.coworkingRepository = coworkingRepository;
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
        boolean result = false;
        if (reservationRepository.findById(reservationId).isPresent()) {
            result = true;
        }
        reservationRepository.deleteById(reservationId);
        return result;
    }

    @Override
    public boolean makeReservation(User user, String coworkingName) throws InputException {
        Scanner keyboard = new Scanner(System.in);
        Optional<CoworkingSpace> optionalCoworkingSpace = coworkingRepository.findByName(coworkingName);
        if (optionalCoworkingSpace.isEmpty()) {
            return false;
        }

        CoworkingSpace coworkingSpace = optionalCoworkingSpace.get();
        Reservation reservation = Reservation.builder()
                .reservationId(0)
                .user(user)
                .space(coworkingSpace)
                .build();

        reservationRepository.update(reservation);
        return true;
    }
}
