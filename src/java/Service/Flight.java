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

    public Flight(String to, Date departureDate){
        this.to = to;
        this.departureDate = departureDate;
        this.passengersAmount = 0;
    }

    private boolean enoughtSeats(){
        return passengersAmount <= Constants.MAX_PASSENGERS;
    }
}
