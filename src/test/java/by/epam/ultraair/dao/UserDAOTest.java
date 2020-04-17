package by.epam.ultraair.dao;

import by.epam.ultraair.dao.implementations.UserDAOImpl;
import by.epam.ultraair.dao.interfaces.UserDAO;
import by.epam.ultraair.persistence.domain.BaseEntity;
import by.epam.ultraair.persistence.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDAOTest {
    private UserDAO userDAO;
    private static final Logger logger = LogManager.getLogger(UserDAOTest.class.getName());

    public UserDAOTest(){
        SQLDatabaseConnection sqlDatabaseConnection = new SQLDatabaseConnection(DatabaseNames.TEST_DATABASE);
        userDAO = new UserDAOImpl(sqlDatabaseConnection);
    }

    @Test
    public void getAllTest() throws SQLException {
        ArrayList<User> users = userDAO.getAll();

        Assertions.assertEquals(2, users.size());
    }

    @Test
    public void getTest() throws SQLException {
        Optional<User> userOptional = userDAO.get(1);
        User user = userOptional.orElse(null);

        Assertions.assertEquals(user.getLogin(), "test_admin");
        Assertions.assertEquals(user.getPassword(), "test_password");
        Assertions.assertTrue(user.isAdmin());
    }

    @Test
    public void createUserTest() throws SQLException{
        ArrayList<User> users = userDAO.getAll();
        int was = users.size();

        User user = new User("test_login", "123123", true);
        userDAO.createUser(user);

        users = userDAO.getAll();
        int become = users.size();

        Assertions.assertEquals(was + 1, become);
    }

    @Test
    public void deleteUserTest() throws SQLException{
        ArrayList<User> users = userDAO.getAll();
        users = users
                .stream()
                .sorted(Comparator.comparingInt(BaseEntity::getId))
                .collect(Collectors.toCollection(ArrayList::new));
        int was = users.size();

        User user = users.get(users.size() - 1);
        userDAO.deleteUser(user.getId());

        int become = userDAO.getAll().size();

        Assertions.assertEquals(was - 1, become);
    }

    @AfterEach
    public void deleteUserFixture(){
        userDAO.deleteUserFixture();
    }
}
