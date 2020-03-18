package Service;

import DAO.FlightEntity;
import DAO.FlightRepository;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class FlightService {
    FlightRepository flightRepository = new FlightRepository();
    FlightMapper mapper = new FlightMapper();

    public FlightService() throws ParseException, java.text.ParseException, IOException {
    }

    public ArrayList<String> getFlightsStrings() throws Exception {
        ArrayList<Flight> flights = getFlights();
        ArrayList<String> flightStrings = new ArrayList<>();

        for (Flight flight: flights) {
            flightStrings.add(flight.toString());
        }

        return flightStrings;
    }

    public ArrayList<Flight> getFlights() throws Exception{
        Hashtable<Integer, FlightEntity> flightsInFile = flightRepository.getAll();
        ArrayList<Flight> flights = new ArrayList<>();

        for (Integer key : flightsInFile.keySet()) {
            FlightEntity flightEntity = flightsInFile.get(key);
            Flight flight = mapper.mapToFlight(flightEntity);
            flights.add(flight);
        }

        return flights;
    }

    public Flight getFlightByOrder(int order) throws Exception {
        return getFlights().get(order);
    }
}
