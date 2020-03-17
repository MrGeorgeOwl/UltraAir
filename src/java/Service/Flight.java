package Service;

import java.util.Date;

public class Flight {
    private String from;
    private String to;
    private Date departureDate;
    private int passengersAmount;

    public String getDestination() {
        return to;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getPassengersAmount() {
        return passengersAmount;
    }

    public Flight(String from, String to, Date departureDate, int passengersAmount){
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
        this.passengersAmount = passengersAmount;
    }

    private boolean enoughtSeats(){
        return passengersAmount <= Constants.MAX_PASSENGERS;
    }

    @Override
    public String toString() {
        return "From: ".concat(from).concat("\n")
                .concat("To: ").concat(to).concat("\n")
                .concat("Date: ").concat(String.valueOf(departureDate)).concat("\n")
                .concat("Amount of valuable sits: ").concat(
                        String.valueOf(Constants.MAX_PASSENGERS - passengersAmount)
                ).concat("\n");
    }
}
