package by.epam.ultraair.dao.implementations;

import by.epam.ultraair.dao.SQLDatabaseConnection;
import by.epam.ultraair.dao.interfaces.TicketDAO;
import by.epam.ultraair.persistence.domain.Ticket;
import by.epam.ultraair.persistence.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class TicketDAOImpl implements TicketDAO {
    private Connection connection;
    private String database;
    private Logger logger = LogManager.getLogger(TicketDAO.class.getName());

    public TicketDAOImpl(SQLDatabaseConnection sqlDatabaseConnection) {
        try{
            this.connection = sqlDatabaseConnection.getConnection();
            this.database = sqlDatabaseConnection.getDatabase();
        }
        catch (SQLException e){
            logger.error(e.toString());
        }
    }

    @Override
    public Optional<Ticket> get(Integer id) throws SQLException {
        Statement statement = connection.createStatement();
        String query = String.format(
                "SELECT userID, flightID, price, rightFirstSitting, rightFirstRegistration" +
                        " FROM %s.Ticket" +
                        " WHERE id=%d",
                database, id
        );
        ResultSet resultSet = statement.executeQuery(query);
        Ticket ticket = null;

        if(resultSet.next()){
            Integer userID = resultSet.getInt("userID");
            Integer flightID = resultSet.getInt("flightID");
            boolean rightFirstSitting = resultSet.getInt("rightFirstSitting") == 1;
            boolean rightFirstRegistration = resultSet.getInt("rightFirstRegistration") == 1;
            double price = resultSet.getDouble("price");
            ticket = new Ticket(id, userID, flightID, rightFirstRegistration, rightFirstSitting, price);
        }

        statement.close();

        return Optional.of(ticket);
    }

    @Override
    public ArrayList<Ticket> getAll() throws SQLException{
        ArrayList<Ticket> tickets = new ArrayList<>();

        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM %s.Ticket", database);
        ResultSet resultSet =  statement.executeQuery(query);

        while(resultSet.next()){
            tickets.add(getTicketFromResultSet(resultSet));
        }

        statement.close();

        return tickets;
    }

    @Override
    public ArrayList<Ticket> getUserTickets(User user) throws SQLException{
        ArrayList<Ticket> userTickets = new ArrayList<>();

        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM %s.Ticket where userID=%d", database, user.getId());
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            userTickets.add(getTicketFromResultSet(resultSet));
        }

        statement.close();

        return userTickets;
    }

    public Ticket getTicketFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer userID = resultSet.getInt("userID");
        Integer flightID = resultSet.getInt("flightID");
        boolean rightFirstSitting = resultSet.getInt("rightFirstSitting") == 1;
        boolean rightFirstRegistration = resultSet.getInt("rightFirstRegistration") == 1;
        double price = resultSet.getDouble("price");

        return new Ticket(id, userID, flightID, rightFirstRegistration, rightFirstSitting, price);
    }

    @Override
    public void createTicket(Ticket ticket){
        try {
            Statement statement = connection.createStatement();
            String query = String.format(
                    "INSERT INTO %s.Ticket (userID, flightID, price, rightFirstSitting, rightFirstRegistration)" +
                            " VALUES (%d, %d," + ticket.getPrice() +", %d, %d)",
                    database, ticket.getUserId(), ticket.getFlightID(),
                    ticket.isRightFirstSitting() ? 1 : 0, ticket.isRightFirstRegistration() ? 1 : 0
                    );
            statement.executeUpdate(query);
        }
        catch (SQLException e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void deleteTicket(Integer id){
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM %s.Ticket WHERE id=%d", database, id);
            statement.executeUpdate(query);
        }
        catch (SQLException e){
            System.out.println(e.toString());
        }
    }
}
