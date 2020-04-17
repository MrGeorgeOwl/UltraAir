package by.epam.ultraair.dao;

import by.epam.ultraair.dao.implementations.FlightDAOImpl;
import by.epam.ultraair.dao.implementations.TicketDAOImpl;
import by.epam.ultraair.dao.implementations.UserDAOImpl;
import by.epam.ultraair.dao.interfaces.FlightDAO;
import by.epam.ultraair.dao.interfaces.TicketDAO;
import by.epam.ultraair.dao.interfaces.UserDAO;
import by.epam.ultraair.persistence.domain.BaseEntity;
import by.epam.ultraair.persistence.domain.Flight;
import by.epam.ultraair.persistence.domain.Ticket;
import by.epam.ultraair.persistence.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

public class TicketDAOTest {
    private UserDAO userDAO;
    private FlightDAO flightDAO;
    private TicketDAO ticketDAO;
    private static final Logger logger = LogManager.getLogger();

    public TicketDAOTest(){
        SQLDatabaseConnection sqlDatabaseConnection = new SQLDatabaseConnection(DatabaseNames.TEST_DATABASE);
        userDAO = new UserDAOImpl(sqlDatabaseConnection);
        flightDAO = new FlightDAOImpl(sqlDatabaseConnection);
        ticketDAO = new TicketDAOImpl(sqlDatabaseConnection);
    }

    @Test
    public void createTicketTest() throws SQLException {
        User user = new User("test", "test", false);
        userDAO.createUser(user);

        Flight flight = new Flight("Test", "Test", new Date(), new Date());
        flightDAO.createFlight(flight);

        Integer userID = userDAO.get(user.getLogin()).orElse(null).getId();

        ArrayList<Flight> flights = flightDAO.getAll()
                .stream()
                .sorted(Comparator.comparingInt(BaseEntity::getId))
                .collect(Collectors.toCollection(ArrayList::new));
        Integer flightID = flights.get(flights.size() - 1).getId();

        int was =  ticketDAO.getAll().size();

        Ticket ticket = new Ticket(userID, flightID, true, false);
        ticketDAO.createTicket(ticket);

        ArrayList<Ticket> tickets = ticketDAO.getAll()
                .stream()
                .sorted(Comparator.comparingInt(BaseEntity::getId))
                .collect(Collectors.toCollection(ArrayList::new));
        int become = tickets.size();

        Assertions.assertEquals(was + 1, become);


        ticketDAO.deleteTicket(tickets.get(tickets.size() - 1).getId());
        flightDAO.deleteFlight(flightID);
        userDAO.deleteUser(userID);
    }

}
