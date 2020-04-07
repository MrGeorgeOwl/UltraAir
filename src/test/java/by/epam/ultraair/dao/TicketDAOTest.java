package by.epam.ultraair.dao;

import by.epam.ultraair.dao.entities.TicketEntity;
import by.epam.ultraair.dao.repositories.TicketRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class TicketDAOTest {

    Logger logger = LogManager.getLogger();

    @Test
    public void constructorTest() throws ParseException, IOException {
        TicketRepository ticketRepository = new TicketRepository();
        logger.info(ticketRepository.toString());
    }

    @Test
    public void ticketTest() throws IOException, ParseException {
        TicketRepository ticketRepository = new TicketRepository();
        Optional<TicketEntity> ticketEntityOptional = ticketRepository.get(5642);
        TicketEntity ticketEntity = ticketEntityOptional.orElse(new TicketEntity());
        logger.info(ticketEntity.toString());
    }

    @Test
    public void addTicketTest() throws IOException, ParseException {
        TicketRepository ticketRepository = new TicketRepository();
        logger.info(ticketRepository.toString());
        TicketEntity ticketEntity = new TicketEntity(313, 45.50, true, true, "syoma");
        ticketRepository.add(ticketEntity);
        logger.info(ticketRepository.toString());
    }



    @Test
    public void saveTicketsTest() throws IOException, ParseException {
        String path = "src/resources/Tickets.json";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        logger.info("Saved at " + absolutePath);

        TicketRepository ticketRepository = new TicketRepository();
        ticketRepository.save();
    }

}
