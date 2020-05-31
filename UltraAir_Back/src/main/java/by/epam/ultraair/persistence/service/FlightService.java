package by.epam.ultraair.persistence.service;

import by.epam.ultraair.dao.implementation.FlightDAOImpl;
import by.epam.ultraair.dao.interfaces.FlightDAO;
import by.epam.ultraair.persistence.domain.Flight;

import java.util.ArrayList;

public class FlightService {
    private FlightDAO flightDAO;

    public FlightService() {
        flightDAO = new FlightDAOImpl();
    }

    public ArrayList<String> getFlightsStrings() {
        ArrayList<Flight> flights = getFlights();
        ArrayList<String> flightStrings = new ArrayList<>();

        for (Flight flight: flights) {
            flightStrings.add(flight.toString());
        }

        return flightStrings;
    }

    public ArrayList<Flight> getFlights() {
        return flightDAO.getAll();
    }

    public Flight getFlight(Integer id) {
        return flightDAO.get(id).orElse(null);
    }

    public void deleteFlight(Flight flight) {
        flightDAO.deleteFlight(flight);
    }
}
