package by.epam.ultraair.persistence.service;

import by.epam.ultraair.dao.implementation.UserDAOImpl;
import by.epam.ultraair.dao.interfaces.UserDAO;
import by.epam.ultraair.persistence.domain.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private UserDAO userDAO;

    public UserService(){
        userDAO = new UserDAOImpl();
    }

    public boolean isAdmin(String login) throws SQLException {
        Optional<User> userOptional = userDAO.get(login);
        User user = userOptional.orElse(null);
        if (user == null){
            throw new SQLException("There is no such user %s", login);
        }
        return user.isAdmin();
    }

    public List<User> getAll(){
        return userDAO.getAll();
    }

    public User findUserByLogin(String login) throws SQLException{
        Optional<User> userOptional = userDAO.get(login);
        User user = userOptional.orElse(null);
        if (user == null){
            throw new SQLException("There is no such user %s", login);
        }
        return user;
    }
}
