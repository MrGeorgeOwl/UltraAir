package service.mappers;

import dao.entities.FlightEntity;
import presentation.dto.FlightDTO;
import service.domain_objects.Flight;

import java.util.Date;

public class FlightMapper {

    public Flight mapToFlight(FlightEntity flightEntity) {
        String fromPlace = flightEntity.getFromPlace();
        String toPlace = flightEntity.getToPlace();
        Date departureDate = flightEntity.getDepartureDate();
        Date arrivalDate = flightEntity.getArrivalDate();
        int passengersAmount = flightEntity.getPassengersAmount();

        return new Flight(fromPlace, toPlace, departureDate, arrivalDate, passengersAmount);
    }

    public Flight mapToFlight(FlightDTO flightDTO){
        String fromPlace = flightDTO.from;
        String toPlace = flightDTO.to;
        Date departureDate = flightDTO.departureDate;
        Date arrivalDate = flightDTO.arrivalDate;
        int passengersAmount = flightDTO.passengersAmount;

        return new Flight(fromPlace, toPlace, departureDate, arrivalDate, passengersAmount);
    }

    public FlightEntity mapToFlightEntity(FlightDTO flightDTO){
        Flight flight = mapToFlight(flightDTO);

        String fromPlace = flight.getFrom();
        String toPlace = flight.getTo();
        Date departureDate = flight.getDepartureDate();
        Date arrivalDate = flight.getArrivalDate();
        int passengersAmount = flight.getPassengersAmount();

        return new FlightEntity(fromPlace, toPlace, departureDate, arrivalDate, passengersAmount);
    }
}
