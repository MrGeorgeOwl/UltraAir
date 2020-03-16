package java.Service;

import java.util.Date;

public class Flight {
    private String destination;
    private Date departureDate;
    private int passengersAmount;

    public String getDestination() {
        return destination;
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

    public Flight(String destination, Date departureDate){
        this.destination = destination;
        this.departureDate = departureDate;
        this.passengersAmount = 0;
    }

    private boolean enoughtSeats(){
        return passengersAmount <= Constants.MAX_PASSENGERS;
    }
}
