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
        SQLDatabaseConnection sqlDatabaseConnection = new SQLDatabaseConnection(DatabaseNames.TEST_DATABASE);
        flightDAO = new FlightDAOImpl(sqlDatabaseConnection);
    }

    @Test
    public void createFlightTest() throws SQLException {
        int was = flightDAO.getAll().size();

        Flight flight = new Flight("Minsk", "Minsk", new Date(), new Date());
        flightDAO.createFlight(flight);

        int become = flightDAO.getAll().size();

        Assertions.assertEquals(was + 1, become);
    }

    @Test
    public void deleteFlightTest() throws SQLException {

        ArrayList<Flight> flights = flightDAO.getAll();
        flights = flights
                .stream()
                .sorted(Comparator.comparingInt(BaseEntity::getId))
                .collect(Collectors.toCollection(ArrayList::new));

        int was = flights.size();

        Integer id = flights.get(flights.size() - 1).getId();
        flightDAO.deleteFlight(id);

        int become = flightDAO.getAll().size();

        Assertions.assertEquals(was - 1, become);
    }

    @AfterAll
    static void deleteFlightFixtures(){
        flightDAO.deleteFlightFixtures();
    }

}
