package by.epam.ultraair.persistence.service;

import by.epam.ultraair.dao.DatabaseNames;
import by.epam.ultraair.dao.SQLDatabaseConnection;
import by.epam.ultraair.dao.implementations.TicketDAOImpl;
import by.epam.ultraair.dao.implementations.UserDAOImpl;
import by.epam.ultraair.dao.interfaces.UserDAO;
import by.epam.ultraair.persistence.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    private UserDAO userDAO;
    private Logger logger = LogManager.getLogger(UserService.class.getName());

    public UserService(){
        SQLDatabaseConnection sqlDatabaseConnection = new SQLDatabaseConnection(DatabaseNames.MAIN_DATABASE);
        userDAO = new UserDAOImpl(sqlDatabaseConnection);
    }

    public UserService(String database){
        SQLDatabaseConnection sqlDatabaseConnection = new SQLDatabaseConnection(database);
        userDAO = new UserDAOImpl(sqlDatabaseConnection);
    }

    public boolean isAdmin(String login) throws SQLException {
        Optional<User> userOptional = userDAO.get(login);
        User user = userOptional.orElse(null);
        if (user == null){
            throw new SQLException("There is no such user %s", login);
        }
        return user.isAdmin();
    }
}
