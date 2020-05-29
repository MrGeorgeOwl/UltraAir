package by.epam.ultraair.persistence.service;

import by.epam.ultraair.dao.DatabaseNames;
import by.epam.ultraair.dao.SQLDatabaseConnection;
import by.epam.ultraair.dao.implementations.TicketDAOImpl;
import by.epam.ultraair.dao.implementations.UserDAOImpl;
import by.epam.ultraair.dao.interfaces.TicketDAO;
import by.epam.ultraair.dao.interfaces.UserDAO;
import by.epam.ultraair.persistence.domain.Ticket;
import by.epam.ultraair.persistence.domain.User;
import by.epam.ultraair.presentation.dto.TicketDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketService {
    private TicketDAO ticketDAO;
    private UserDAO userDAO;
    private final static Logger logger = LogManager.getLogger(TicketService.class.getName());

    public TicketService() {
        SQLDatabaseConnection sqlDatabaseConnection = new SQLDatabaseConnection(DatabaseNames.MAIN_DATABASE);
        ticketDAO = new TicketDAOImpl();
        userDAO = new UserDAOImpl();
    }

    public TicketService(String database) {
        SQLDatabaseConnection sqlDatabaseConnection = new SQLDatabaseConnection(database);
        ticketDAO = new TicketDAOImpl();
        userDAO = new UserDAOImpl();
    }

    public ArrayList<String> getUserTicketsStrings(String login) throws Exception {
        ArrayList<Ticket> userTickets = getUserTickets(login);
        return userTickets
                .stream()
                .map(Ticket::toString)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Ticket> getUserTickets(String login) throws Exception {
        Optional<User> userOptional = userDAO.get(login);
        User user = userOptional.orElse(null);
        if (user == null){
            throw new Exception("There is no such user");
        }
        return getUserTickets(user);
    }

    public ArrayList<Ticket> getUserTickets(User user) {
        ArrayList<Ticket> userTickets = new ArrayList<>();
        try{
            userTickets = ticketDAO.getUserTickets(user);
        }
        catch (SQLException e){
            logger.error(e);
        }
        return userTickets;
    }

    public double getTicketPrice(TicketDTO ticketDTO){
        double price = 200.;
        price = ticketDTO.wantRightFirstRegistration ? price + 20.: price;
        price = ticketDTO.wantRightFirstSitting ? price + 15.5: price;

        return price;
    }

    public void createUserTicket(TicketDTO ticketDTO) throws SQLException {
        User user = userDAO.get(ticketDTO.clientName).orElse(null);
        if (user == null){
            throw new SQLException("There is no such user with login %s ", ticketDTO.clientName);
        }
        Integer flightID = ticketDTO.flightID;
        boolean rightFirstSitting = ticketDTO.wantRightFirstSitting;
        boolean rightFirstRegistration = ticketDTO.wantRightFirstRegistration;
        Ticket ticket = new Ticket(user.getId(), flightID, rightFirstRegistration, rightFirstSitting);

        ticketDAO.createTicket(ticket);
    }
}
