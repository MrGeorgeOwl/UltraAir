package by.epam.ultraair.dao.implementations;

import by.epam.ultraair.dao.interfaces.UserDAO;
import by.epam.ultraair.persistence.domain.User;
import by.epam.ultraair.utils.HibernateSessionFactoryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

    public UserDAOImpl() {
    }

    @Override
    public Optional<User> get(Integer id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return Optional.of(session.get(User.class, id));
    }

    @Override
    public Optional<User> get(String login){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        String query = String.format("FROM User WHERE login='%s'", login);
        return Optional.of(session.createQuery(query, User.class).getSingleResult());
    }

    @Override
    public ArrayList<User> getAll(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<User> users = (List<User>) session.createQuery("From User").list();
        return (ArrayList<User>) users;
    }

    @Override
    public void createUser(User user){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateUser(User user){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(User user){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUserFixture(){

    }
}
