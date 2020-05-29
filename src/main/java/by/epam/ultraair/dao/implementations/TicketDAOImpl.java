package by.epam.ultraair.dao.implementations;

import by.epam.ultraair.dao.interfaces.TicketDAO;
import by.epam.ultraair.persistence.domain.Ticket;
import by.epam.ultraair.persistence.domain.User;
import by.epam.ultraair.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDAOImpl implements TicketDAO {

    @Override
    public Optional<Ticket> get(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return Optional.of(session.get(Ticket.class, id));
    }

    @Override
    public ArrayList<Ticket> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Ticket> tickets = (List<Ticket>) session.createQuery("From Ticket").list();
        return (ArrayList<Ticket>) tickets;
    }

    @Override
    public ArrayList<Ticket> getUserTickets(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String query = "From Ticket WHERE userID=" + user.getId();
        List<Ticket> tickets = (List<Ticket>) session.createQuery(query).list();
        return (ArrayList<Ticket>) tickets;
    }

    @Override
    public void createTicket(Ticket ticket){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(ticket);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateTicket(Ticket ticket){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(ticket);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteTicket(Ticket ticket){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(ticket);
        tx1.commit();
        session.close();
    }
}
