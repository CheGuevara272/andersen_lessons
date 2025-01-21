package org.andersen_project.entity;

import java.io.Serializable;

public class Reservation implements Serializable {
    private final Integer reserveeId;
    private final Integer reservedSpaceId;
    private Integer reservationId;
    private String reservationDate;
    private String reservationStartTime;
    private String reservationEndTime;

    public Reservation(Integer reservationId, Integer reserveeId, Integer reservedSpaceId) {
        this.reservationId = reservationId;
        this.reserveeId = reserveeId;
        this.reservedSpaceId = reservedSpaceId;
    }

    public Integer getReserveeId() {
        return reserveeId;
    }

    public Integer getReservationId() {
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
                "  Reserved Space ID = " + reservedSpaceId + '\n' +
                "  Reservee ID = " + reserveeId + '\n' +
                "  Reservation Date = " + reservationDate + '\n' +
                "  Reservation Start Time = " + reservationStartTime + '\n' +
                "  Reservation End Time = " + reservationEndTime;
    }
}
