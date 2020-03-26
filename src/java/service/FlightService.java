package service;

import DAO.FlightEntity;
import DAO.FlightRepository;
import org.json.simple.parser.ParseException;
import presentation.DTO.FlightDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

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
}
