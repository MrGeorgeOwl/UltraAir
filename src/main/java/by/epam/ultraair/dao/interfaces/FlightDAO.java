package by.epam.ultraair.dao.interfaces;

import by.epam.ultraair.persistence.domain.Flight;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface FlightDAO {
    Optional<Flight> get(Integer id) throws SQLException;
    ArrayList<Flight> getAll() throws SQLException;
    void createFlight(Flight flight);
    void updateFlight(Flight flight);
    void deleteFlight(Flight flight);
}
