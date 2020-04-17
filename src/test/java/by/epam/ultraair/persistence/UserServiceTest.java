package by.epam.ultraair.persistence;

import by.epam.ultraair.dao.DatabaseNames;
import by.epam.ultraair.dao.interfaces.UserDAO;
import by.epam.ultraair.persistence.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class UserServiceTest {
    private UserDAO userDAO;
    private UserService userService;
    public UserServiceTest(){
        userService = new UserService(DatabaseNames.TEST_DATABASE);
    }

    @Test
    public void isAdminTest() throws SQLException {
        boolean isAdmin = userService.isAdmin("test_admin");
        Assertions.assertTrue(isAdmin);
    }
}
