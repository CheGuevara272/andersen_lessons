package org.andersen_project.service.impl;

import org.andersen_project.entity.CoworkingSpace;
import org.andersen_project.entity.Reservation;
import org.andersen_project.entity.User;
import org.andersen_project.exception.InputException;
import org.andersen_project.repository.CoworkingRepository;
import org.andersen_project.repository.ReservationRepository;
import org.andersen_project.service.ReservationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final CoworkingRepository coworkingRepository;

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
