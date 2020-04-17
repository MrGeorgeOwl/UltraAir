package by.epam.ultraair.dao.implementations;

import by.epam.ultraair.dao.SQLDatabaseConnection;
import by.epam.ultraair.dao.interfaces.FlightDAO;
import by.epam.ultraair.persistence.domain.Flight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

public class FlightDAOImpl implements FlightDAO {
    private Connection connection;
    private String database;
    private static final Logger logger = LogManager.getLogger(FlightDAOImpl.class.getName());

    public FlightDAOImpl(SQLDatabaseConnection sqlDatabaseConnection) {
        try{
            this.connection = sqlDatabaseConnection.getConnection();
            this.database = sqlDatabaseConnection.getDatabase();
        }
        catch (SQLException e){
            logger.error(e.toString());
        }
    }

    @Override
    public Optional<Flight> get(Integer id) throws SQLException {
        Statement statement = connection.createStatement();
        String query = String.format(
                "SELECT fromPlace, toPlace, departureDate, arrivalDate" +
                        " FROM %s.Flight" +
                        " WHERE id=%d",
                database, id
        );
        ResultSet resultSet = statement.executeQuery(query);
        Flight flight = null;

        if(resultSet.next()){
            String fromPlace = resultSet.getString("fromPlace");
            String toPlace = resultSet.getString("toPlace");
            Date departureDate = resultSet.getDate("departureDate");
            Date arrivalDate = resultSet.getDate("arrivalDate");
            flight = new Flight(id, fromPlace, toPlace, departureDate, arrivalDate);
        }

        statement.close();

        return Optional.of(flight);
    }

    @Override
    public ArrayList<Flight> getAll() throws SQLException{
        ArrayList<Flight> flights = new ArrayList<>();

        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM %s.Flight", database);
        ResultSet resultSet =  statement.executeQuery(query);

        while(resultSet.next()){
            Integer id = resultSet.getInt("id");
            String fromPlace = resultSet.getString("fromPlace");
            String toPlace = resultSet.getString("toPlace");
            Date departureDate = resultSet.getDate("departureDate");
            Date arrivalDate = resultSet.getDate("arrivalDate");

            flights.add(new Flight(id, fromPlace, toPlace, departureDate, arrivalDate));
        }

        statement.close();

        return flights;
    }

    @Override
    public void createFlight(Flight flight){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm a");
            String arrivalDate = dateFormat.format(flight.getArrivalDate());
            String departureDate = dateFormat.format(flight.getDepartureDate());

            Statement statement = connection.createStatement();
            String query = String.format(
                    "INSERT INTO %s.Flight (fromPlace, toPlace, departureDate, arrivalDate)" +
                            "VALUES ('%s', '%s', '%s', '%s')",
                    database, flight.getFromPlace(), flight.getToPlace(), departureDate, arrivalDate
            );
            statement.executeUpdate(query);
        }
        catch (SQLException e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void deleteFlight(Integer id){
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM %s.Flight WHERE id=%d", database, id);
            statement.executeUpdate(query);
        }
        catch (SQLException e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void deleteFlightFixtures(){
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM %s.Flight WHERE id > 3", database);
            statement.executeUpdate(query);
        }
        catch (SQLException e){
            System.out.println(e.toString());
        }
    }
}
