package org.andersen_project.entity;

import org.andersen_project.util.IdGenerator;

import java.io.Serializable;
import java.util.UUID;

public class Reservation implements Serializable {
    private final CoworkingSpace reservedSpace;
    private final User reservee;
    private final UUID reservationId;
    private String reservationDate;
    private String reservationStartTime;
    private String reservationEndTime;

    public Reservation(CoworkingSpace reservedSpace, User user) {
        this.reservee = user;
        this.reservationId = IdGenerator.getInstance().generateUniqueId();
        this.reservedSpace = reservedSpace;
        reservedSpace.setReserved(true);
    }

    public User getReservee() {
        return reservee;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setReservationStartTime(String reservationStartTime) {
        this.reservationStartTime = reservationStartTime;
    }

    public void setReservationEndTime(String reservationEndTime) {
        this.reservationEndTime = reservationEndTime;
    }

    @Override
    public String toString() {
        return "Reservation:" +
                "  Reservation ID = " + reservationId + '\n' +
                "  Reserved Space = " + reservedSpace.toString() + '\n' +
                "  Reservee = " + reservee.getName() + '\n' +
                "  Reservation Date = " + reservationDate + '\n' +
                "  Reservation Start Time = " + reservationStartTime + '\n' +
                "  Reservation End Time = " + reservationEndTime;
    }
}
