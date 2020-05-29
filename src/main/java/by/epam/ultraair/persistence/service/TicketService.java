package by.epam.ultraair.persistence.service;

import by.epam.ultraair.dao.implementation.TicketDAOImpl;
import by.epam.ultraair.dao.implementation.UserDAOImpl;
import by.epam.ultraair.dao.interfaces.TicketDAO;
import by.epam.ultraair.dao.interfaces.UserDAO;
import by.epam.ultraair.persistence.domain.Ticket;
import by.epam.ultraair.persistence.domain.User;
import by.epam.ultraair.presentation.dto.TicketDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketService {
    private TicketDAO ticketDAO;
    private UserDAO userDAO;

    public TicketService() {
        ticketDAO = new TicketDAOImpl();
        userDAO = new UserDAOImpl();
    }

    public ArrayList<String> getUserTicketsStrings(String login) {
        ArrayList<Ticket> userTickets = getUserTickets(login);
        if (userTickets == null){
            return new ArrayList<>();
        }
        return userTickets
                .stream()
                .map(Ticket::toString)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Ticket> getUserTickets(String login){
        Optional<User> userOptional = userDAO.get(login);
        User user = userOptional.orElse(null);
        return getUserTickets(user);
    }

    public ArrayList<Ticket> getUserTickets(User user) {
        return ticketDAO.getUserTickets(user);
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
