package by.epam.ultraair.persistence;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import by.epam.ultraair.persistence.services.FlightService;

import java.io.IOException;
import java.util.ArrayList;

public class FlightServiceTest {
    FlightService flightService = new FlightService();

    public FlightServiceTest() throws ParseException, java.text.ParseException, IOException {
    }

    @Test
    public void getFlightTest() throws Exception {
        ArrayList<String> flightsString = flightService.getFlightsStrings();

        for (String flightString: flightsString){
            System.out.println(flightString);
        }
    }
}