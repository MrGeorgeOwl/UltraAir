package by.epam.ultraair.dao.implementations;

import by.epam.ultraair.dao.SQLDatabaseConnection;
import by.epam.ultraair.dao.interfaces.TicketDAO;
import by.epam.ultraair.persistence.domain.Ticket;
import by.epam.ultraair.persistence.domain.User;
import by.epam.ultraair.utils.HibernateSessionFactoryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketDAOImpl implements TicketDAO {
    private Connection connection;
    private String database;
    private Logger logger = LogManager.getLogger(TicketDAO.class.getName());


    public TicketDAOImpl(SQLDatabaseConnection sqlDatabaseConnection) {
    }

    @Override
    public Optional<Ticket> get(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return Optional.of(session.get(Ticket.class, id));
    }

    @Override
    public ArrayList<Ticket> getAll() throws SQLException{
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Ticket> tickets = (List<Ticket>) session.createQuery("From Ticket").list();
        return (ArrayList<Ticket>) tickets;
    }

    @Override
    public ArrayList<Ticket> getUserTickets(User user) throws SQLException{
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String query = "From Ticket WHERE userID=" + user.getId();
        List<Ticket> tickets = (List<Ticket>) session.createQuery(query).list();
        return (ArrayList<Ticket>) tickets;
    }

    //По поводу этого метода: как я понял, к бд он не обращается, а значит изменениям не подвергается
    public Ticket getTicketFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer userID = resultSet.getInt("userID");
        Integer flightID = resultSet.getInt("flightID");
        boolean rightFirstSitting = resultSet.getInt("rightFirstSitting") == 1;
        boolean rightFirstRegistration = resultSet.getInt("rightFirstRegistration") == 1;
        double price = resultSet.getDouble("price");

        return new Ticket(id, userID, flightID, rightFirstRegistration, rightFirstSitting, price);
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
