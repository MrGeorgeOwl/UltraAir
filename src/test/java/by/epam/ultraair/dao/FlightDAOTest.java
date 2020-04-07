package by.epam.ultraair.dao;

import by.epam.ultraair.dao.entities.FlightEntity;
import by.epam.ultraair.dao.repositories.FlightRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class FlightDAOTest {
    Logger logger = LogManager.getLogger();

    @Test
    public void constructorTest() throws ParseException, java.text.ParseException, IOException {
        FlightRepository flightRepository = new FlightRepository();
        logger.info(flightRepository.toString());
    }

    @Test
    public void flightTest() throws ParseException, java.text.ParseException, IOException {
        FlightRepository flightRepository = new FlightRepository();
        Optional<FlightEntity> flightEntityOptional = flightRepository.get(452);
        FlightEntity flightEntity = flightEntityOptional.orElse(new FlightEntity());

        logger.info(flightEntity.toString());
    }

    @Test
    public void saveTest() throws ParseException, java.text.ParseException, IOException {
        String path = "src/resources/Flights.json";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        logger.info("Saved at " + absolutePath);

        FlightRepository flightRepository = new FlightRepository();
        flightRepository.save();
    }

}
