package org.andersen_project.service.impl;

import org.andersen_project.entity.CoworkingSpace;
import org.andersen_project.entity.Reservation;
import org.andersen_project.entity.User;
import org.andersen_project.exception.InputException;
import org.andersen_project.repository.CoworkingRepository;
import org.andersen_project.repository.ReservationRepository;
import org.andersen_project.service.ReservationService;

import java.util.List;
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
        return reservations.stream().filter(reservation -> reservation.getReserveeId().equals(userId)).toList();
    }

    @Override
    public boolean cancelReservation(Integer reservationId) {
        return reservationRepository.deleteById(reservationId);
    }

    @Override
    public boolean makeReservation(User user, String coworkingName) throws InputException {
        Scanner keyboard = new Scanner(System.in);
        List<CoworkingSpace> coworkingSpaces = coworkingRepository.findAll();
        for (CoworkingSpace space : coworkingSpaces) {
            if (space.getName().equals(coworkingName) && space.isNotReserved()) {
                Integer reservationId = reservationRepository.getLastId();
                Reservation reservation = new Reservation(reservationId, space.getCoworkingId(), user.getUserId());
                coworkingRepository.findById(space.getCoworkingId()).setReserved(true);

                System.out.println("Enter the time you wish to reserve the coworking space");
                reservation.setReservationStartTime(keyboard.nextLine());

                System.out.println("Enter what time you expect to vacate the coworking space");
                reservation.setReservationEndTime(keyboard.nextLine());

                System.out.println("Enter reservation date");
                reservation.setReservationDate(keyboard.nextLine());

                return reservationRepository.update(reservation);
            } else {
                throw new InputException("Coworking space with that name is reserved");
            }
        }
        throw new InputException("Coworking space with that name does not exist");
    }
}
