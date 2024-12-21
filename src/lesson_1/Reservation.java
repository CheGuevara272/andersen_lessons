package lesson_1;

public class Reservation {
    private String reservationID;
    private CoworkingSpace reservedSpace;
    private String reserveeName;
    private String reservationDate;
    private String reservationStartTime;
    private String reservationEndTime;

    public Reservation(CoworkingSpace reservedSpace, String reserveeName, String reservationDate, String reservationStartTime, String reservationEndTime) {
        this.reservationID = generateUniqueId();
        this.reservedSpace = reservedSpace;
        this.reserveeName = reserveeName;
        this.reservationDate = reservationDate;
        this.reservationStartTime = reservationStartTime;
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
