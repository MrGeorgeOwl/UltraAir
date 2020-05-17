package by.epam.ultraair.dao;

import by.epam.ultraair.dao.implementations.FlightDAOImpl;
import by.epam.ultraair.dao.interfaces.FlightDAO;
import by.epam.ultraair.persistence.domain.BaseEntity;
import by.epam.ultraair.persistence.domain.Flight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

public class FlightDAOTest {
    private static final Logger logger = LogManager.getLogger();
    private static FlightDAO flightDAO;

    public FlightDAOTest() {
        flightDAO = new FlightDAOImpl();
    }

    @Test
    public void getFlightTest() throws SQLException{
        int index = flightDAO.getAll().size() - 1;
        int id = flightDAO.getAll().get(index).getId();
        logger.info(flightDAO.get(id).orElse(null));
    }

    @Test
    public void getAllTest() throws SQLException{
        logger.info(flightDAO.getAll());
    }

    @Test
    public void createFlightTest() throws SQLException {
        int expected = flightDAO.getAll().size() + 1;
        Flight flight = new Flight("Minsk", "Minsk", new Date(), new Date());
        flightDAO.createFlight(flight);
        int actual = flightDAO.getAll().size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void updateFlightTest() throws SQLException{
        int index = flightDAO.getAll().size() - 1;
        Flight flight = new Flight("Minsk", "Moscow", new Date(), new Date());
        flight.setId(flightDAO.getAll().get(index).getId());
        flightDAO.updateFlight(flight);
    }

    @Test
    public void deleteFlightTest() throws SQLException {
        int expected = flightDAO.getAll().size() - 1;
        Flight flight = flightDAO.getAll().get(expected);
        flightDAO.deleteFlight(flight);
        int actual = flightDAO.getAll().size();
        Assertions.assertEquals(expected, actual);
    }

}
