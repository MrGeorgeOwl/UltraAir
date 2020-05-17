package by.epam.ultraair.persistence.service;

import by.epam.ultraair.dao.DatabaseNames;
import by.epam.ultraair.dao.SQLDatabaseConnection;
import by.epam.ultraair.dao.implementations.FlightDAOImpl;
import by.epam.ultraair.dao.interfaces.FlightDAO;
import by.epam.ultraair.persistence.domain.Flight;
import by.epam.ultraair.presentation.dto.FlightDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

public class FlightService {
    private FlightDAO flightDAO;
    private final Logger logger = LogManager.getLogger(FlightService.class.getName());

    public FlightService() {
        flightDAO = new FlightDAOImpl();
    }

    public FlightService(String database){
        flightDAO = new FlightDAOImpl();
    }

    public ArrayList<String> getFlightsStrings() throws SQLException {
        ArrayList<Flight> flights = getFlights();
        ArrayList<String> flightStrings = new ArrayList<>();

        for (Flight flight: flights) {
            flightStrings.add(flight.toString());
        }

        return flightStrings;
    }

    public ArrayList<Flight> getFlights() throws SQLException{
        return flightDAO.getAll();
    }

    public void addFlight(FlightDTO flightDTO) {
        Flight flight = new Flight(flightDTO.from, flightDTO.to, flightDTO.departureDate, flightDTO.arrivalDate);
        flightDAO.createFlight(flight);
    }

    public void deleteFlight(Flight flight) {
        flightDAO.deleteFlight(flight);
    }
}
