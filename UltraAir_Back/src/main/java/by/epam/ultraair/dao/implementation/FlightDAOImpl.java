package by.epam.ultraair.dao.implementation;

import by.epam.ultraair.dao.interfaces.FlightDAO;
import by.epam.ultraair.persistence.domain.Flight;
import by.epam.ultraair.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDAOImpl implements FlightDAO {
    @Override
    public Optional<Flight> get(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return Optional.of(session.get(Flight.class, id));
    }

    @Override
    public ArrayList<Flight> getAll(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Flight> flights = (List<Flight>) session.createQuery("From Flight").list();
        return (ArrayList<Flight>) flights;
    }

    @Override
    public void createFlight(Flight flight){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(flight);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateFlight(Flight flight){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(flight);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteFlight(Flight flight){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(flight);
        tx1.commit();
        session.close();
    }
}
