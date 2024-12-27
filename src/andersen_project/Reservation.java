package andersen_project;

public class Reservation {
    private final String reservationID;
    private final CoworkingSpace reservedSpace;
    private final User reservee;
    private final String reserveeName;
    private String reservationDate;
    private String reservationStartTime;
    private String reservationEndTime;

    public Reservation(CoworkingSpace reservedSpace, User user, String reserveeName) {
        this.reservationID = generateUniqueId();
        this.reservedSpace = reservedSpace;
        reservedSpace.setReserved(true);
        this.reservee = user;
        this.reserveeName = reserveeName;
    }

    public User getReservee() {
        return reservee;
    }

    public String getReservationID() {
        return reservationID;
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

    private String generateUniqueId() {
        long uniqueId = System.currentTimeMillis();
        return Long.toString(uniqueId);
    }

    @Override
    public String toString() {
        return "Reservation:" +
                "  Reservation ID= " + reservationID + '\n' +
                "  Reserved Space = " + reservedSpace.toString() + '\n' +
                "  Reservee Name = " + reserveeName + '\n' +
                "  Reservation Date = " + reservationDate + '\n' +
                "  Reservation Start Time = " + reservationStartTime + '\n' +
                "  Reservation End Time = " + reservationEndTime;
    }
}
