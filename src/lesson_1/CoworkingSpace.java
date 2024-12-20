package lesson_1;

import java.util.Date;

public class CoworkingSpace {
    private String name;
    private boolean reserved;
    private Date reservationDate;

    CoworkingSpace(String name) {
        this.name = name;
        reserved = false;
        reservationDate = new Date();
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }


}
