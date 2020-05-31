package by.epam.ultraair.persistence.service;

import by.epam.ultraair.dao.implementation.TicketDAOImpl;
import by.epam.ultraair.dao.implementation.UserDAOImpl;
import by.epam.ultraair.dao.interfaces.TicketDAO;
import by.epam.ultraair.dao.interfaces.UserDAO;
import by.epam.ultraair.persistence.domain.Ticket;
import by.epam.ultraair.persistence.domain.User;

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
        return ticketDAO.findUserTickets(user);
    }
}
