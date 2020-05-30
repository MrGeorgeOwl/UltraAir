package by.epam.ultraair.dao.implementation;

import by.epam.ultraair.dao.interfaces.UserDAO;
import by.epam.ultraair.persistence.domain.User;
import by.epam.ultraair.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

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
    public void deleteUser(String login) throws SQLException {
        User user = get(login).orElse(null);
        if (user != null){
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            session.close();
        }
        throw new SQLException("No such user as " + login);
    }

    @Override
    public void deleteUser(Integer id) throws SQLException {
        User user = get(id).orElse(null);
        if (user != null){
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            session.close();
        }
        else{
            throw new SQLException("No such user with id: " + id);
        }
    }

    @Override
    public void deleteUserFixture(){

    }
}
