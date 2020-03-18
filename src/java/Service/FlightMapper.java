package Service;

import DAO.FlightEntity;

import java.util.Date;

public class FlightMapper {

    public Flight mapToFlight(FlightEntity flightEntity) throws Exception {
        String fromPlace = flightEntity.getFromPlace();
        String toPlace = flightEntity.getToPlace();
        Date departureDate = flightEntity.getDepartureDate();
        int passengersAmount = flightEntity.getPassengersAmount();

        return new Flight(fromPlace, toPlace, departureDate, passengersAmount);
    }
}
