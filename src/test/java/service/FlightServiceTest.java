package service;

import Service.FlightService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class FlightServiceTest {
    FlightService flightService = new FlightService();

    public FlightServiceTest() throws ParseException, java.text.ParseException, IOException {
    }

    @Test
    public void getFlightTest(){
        ArrayList<String> flightsString = flightService.getFlights();

        for (String flightString: flightsString){
            System.out.println(flightString);
        }
    }
}
