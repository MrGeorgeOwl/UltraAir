package by.epam.ultraair.dao.implementations;

import by.epam.ultraair.dao.SQLDatabaseConnection;
import by.epam.ultraair.dao.interfaces.UserDAO;
import by.epam.ultraair.persistence.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private Connection connection;
    private String database;
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

    public UserDAOImpl(SQLDatabaseConnection sqlDatabaseConnection) {
        try{
            this.connection = sqlDatabaseConnection.getConnection();
            this.database = sqlDatabaseConnection.getDatabase();
        }
        catch (SQLException e){
            logger.error(e.toString());
        }
    }

    @Override
    public Optional<User> get(Integer id) throws SQLException {
        Statement statement = connection.createStatement();
        String query = String
                .format("SELECT login, password, admin FROM %s.Users WHERE id=%d", database, id);
        ResultSet resultSet = statement.executeQuery(query);
        User user = null;

        if(resultSet.next()){
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            boolean admin = resultSet.getInt("admin") == 1;

            user = new User(login, password, admin);
        }

        return Optional.of(user);
    }

    @Override
    public Optional<User> get(String login) throws SQLException{
        Statement statement = connection.createStatement();
        String query = String.format(
                "SELECT id, password, admin " +
                        "FROM %s.Users" +
                        " WHERE login='%s'",
                database, login
        );
        ResultSet resultSet = statement.executeQuery(query);
        User user = null;

        if(resultSet.next()){
            Integer id = resultSet.getInt("id");
            String password = resultSet.getString("password");
            boolean admin = resultSet.getInt("admin") == 1;

            user = new User(id, login, password, admin);
        }

        return Optional.of(user);
    }

    @Override
    public ArrayList<User> getAll() throws SQLException{
        ArrayList<User> users = new ArrayList<>();

        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM %s.Users", database);
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            Integer id = resultSet.getInt("id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            boolean admin = resultSet.getInt("admin") == 1;

            users.add(new User(id, login, password, admin));
        }

        return users;
    }

    @Override
    public void createUser(User user){
        try {
            Statement statement = connection.createStatement();
            String query = String.format(
                    "INSERT INTO %s.Users (login, password, admin)" +
                            "VALUES ('%s', '%s', %d)",
                    database, user.getLogin(), user.getPassword(), user.isAdmin() ? 1 : 0
            );
            statement.executeUpdate(query);
        }
        catch (SQLException e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void deleteUser(Integer id){
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM %s.Users WHERE id=%d", database, id);
            statement.executeUpdate(query);
        }
        catch (SQLException e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void deleteUserFixture(){
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM %s.Users WHERE id > 2", database);
            statement.executeUpdate(query);
        }
        catch (SQLException e){
            System.out.println(e.toString());
        }
    }
}
