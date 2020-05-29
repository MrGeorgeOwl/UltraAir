package by.epam.ultraair.dao.interfaces;

import by.epam.ultraair.persistence.domain.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface UserDAO {
    Optional<User> get(Integer id);
    Optional<User> get(String login);
    ArrayList<User> getAll();
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    void deleteUserFixture();
}
