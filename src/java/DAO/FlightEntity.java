package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FlightEntity {
    private Integer id;
    private String fromPlace;
    private String toPlace;
    private Date departureDate;
    private int passengersAmount;
    private int FlightDurationHours;



    public FlightEntity(){
        this.id = null;
        this.fromPlace = null;
        this.toPlace = null;
        this.departureDate = null;
        this.passengersAmount = 0;
        this.FlightDurationHours = 0;
    }

    public FlightEntity(String fromPlace, String toPlace, Date departureDate, int passengersAmount, int FlightDurationHours){
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.departureDate = departureDate;
        this.passengersAmount = passengersAmount;
        this.FlightDurationHours = FlightDurationHours;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
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

    public void setPassengersAmount(int passengersAmount) {
        this.passengersAmount = passengersAmount;
    }

    public int getFlightDurationHours() {
        return FlightDurationHours;
    }

    public void setFlightDurationHours(int flightDurationHours) {
        FlightDurationHours = flightDurationHours;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flight{");
        sb.append("\nfromPlace='").append(fromPlace).append('\'');
        sb.append("\n, toPlace='").append(toPlace).append('\'');
        sb.append("\n, departureDate=").append(departureDate);
        sb.append("\n, passengersAmount=").append(passengersAmount);
        sb.append("\n, FlightDurationHours=").append(FlightDurationHours);
        sb.append('}').append('\n');
        return sb.toString();
    }
}


//TODO: configure some fields and toString() method