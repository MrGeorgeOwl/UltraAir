package by.epam.ultraair.dao.interfaces;

import by.epam.ultraair.persistence.domain.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface UserDAO {
    Optional<User> get(Integer id) throws SQLException;
    Optional<User> get(String login) throws SQLException;
    ArrayList<User> getAll() throws SQLException;
    void createUser(User user);
    void deleteUser(Integer id);
    void deleteUserFixture();
}
