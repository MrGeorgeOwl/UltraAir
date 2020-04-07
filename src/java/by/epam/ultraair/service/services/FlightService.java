package by.epam.ultraair.service.services;

import by.epam.ultraair.presentation.dto.FlightDTO;
import by.epam.ultraair.service.Constants;
import by.epam.ultraair.service.domain_objects.Flight;
import by.epam.ultraair.service.mappers.FlightIndexIdMapper;
import by.epam.ultraair.service.mappers.FlightMapper;
import by.epam.ultraair.dao.entities.FlightEntity;
import by.epam.ultraair.dao.repositories.FlightRepository;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Optional;

public class FlightService {
    FlightRepository flightRepository = new FlightRepository();
    FlightMapper mapper = new FlightMapper();
    FlightIndexIdMapper flightIndexIdMapper = new FlightIndexIdMapper();

    public FlightService() throws ParseException, java.text.ParseException, IOException {
    }

    public ArrayList<String> getFlightsStrings() {
        ArrayList<Flight> flights = getFlights();
        ArrayList<String> flightStrings = new ArrayList<>();

        for (Flight flight: flights) {
            flightStrings.add(flight.toString());
        }

        return flightStrings;
    }

    public ArrayList<Flight> getFlights(){
        Hashtable<Integer, FlightEntity> flightsInFile = flightRepository.getAll();
        ArrayList<Flight> flights = new ArrayList<>();

        for (Integer key : flightsInFile.keySet()) {
            FlightEntity flightEntity = flightsInFile.get(key);
            Flight flight = mapper.mapToFlight(flightEntity);
            flights.add(flight);
        }

        flights.removeIf(n -> (n.getPassengersAmount() == Constants.MAX_PASSENGERS));

        return flights;
    }

    public Flight getFlightByOrder(int order) {
        return getFlights().get(order);
    }

    public void addFlight(FlightDTO flightDTO) throws IOException {

        FlightEntity flightEntity = mapper.mapToFlightEntity(flightDTO);

        flightRepository.add(flightEntity);
    }

    public void deleteFlight(int order) throws IOException {
        Integer flightId = flightIndexIdMapper.getIdByIndex(order);

        flightRepository.delete(flightId);
    }

    public Flight getFlightById(int Integer) throws Exception{
        Optional<FlightEntity> flightEntityOptional = flightRepository.get(Integer);
        FlightEntity flightEntity = flightEntityOptional.orElse(null);
        if (flightEntity == null){
            throw new Exception("No such flight");
        }
        return mapper.mapToFlight(flightEntity);
    }
}
