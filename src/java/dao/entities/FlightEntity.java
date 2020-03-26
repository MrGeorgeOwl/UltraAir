package dao.entities;

import java.text.SimpleDateFormat;
import java.util.*;

public class FlightEntity {
    private String fromPlace;
    private String toPlace;
    private Date departureDate;
    private Date arrivalDate;
    private int passengersAmount;



    public FlightEntity(){
        this.fromPlace = null;
        this.toPlace = null;
        this.departureDate = null;
        this.arrivalDate = null;
        this.passengersAmount = 0;
    }

    public FlightEntity(String fromPlace, String toPlace, Date departureDate, Date arrivalDate, int passengersAmount){
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.passengersAmount = passengersAmount;
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

    public Date getArrivalDate() { return arrivalDate; }

    public void setArrivalDate(Date arrivalDate) { this.arrivalDate = arrivalDate; }

    public int getPassengersAmount() {
        return passengersAmount;
    }

    public void setPassengersAmount(int passengersAmount) {
        this.passengersAmount = passengersAmount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append("\"fromPlace\" : ").append("\"").append(fromPlace).append("\",\n");
        sb.append("\"toPlace\" : ").append("\"").append(toPlace).append("\",\n");
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy', 'hh:mm a");
        sb.append("\"departureDate\" : ").append("\"").append(ft.format(departureDate)).append("\",\n");
        sb.append("\"arrivalDate\" : ").append("\"").append(ft.format(arrivalDate)).append("\",\n");
        sb.append("\"passengersAmount\" : ").append(passengersAmount).append("\n");
        return sb.toString();
    }
}
