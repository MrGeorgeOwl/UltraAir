package by.epam.ultraair.dao.interfaces;

import by.epam.ultraair.persistence.domain.Ticket;
import by.epam.ultraair.persistence.domain.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface TicketDAO {
    Optional<Ticket> findById(Integer id);
    ArrayList<Ticket> getAll();
    ArrayList<Ticket> findUserTickets(User user);
    void createTicket(Ticket ticket);
    void updateTicket(Ticket ticket);
    void deleteTicket(Ticket ticket);
}
