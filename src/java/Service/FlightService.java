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

    public ArrayList<String> getFlights(){
        Hashtable<Integer, FlightEntity> flights = flightRepository.getAll();
        ArrayList<String> flightStrings = new ArrayList<>();

        for (Integer key: flights.keySet()){
            FlightEntity flightEntity = flights.get(key);
            Flight flight = mapper.mapToFlight(flightEntity);
            flightStrings.add(flight.toString());
        }

        return flightStrings;
    }
}
