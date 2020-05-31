package by.epam.ultraair.presentation;

import by.epam.ultraair.persistence.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class FlightsConsoleMenuTest {
    public static Logger log = LogManager.getLogger(FlightsConsoleMenuTest.class.getName());

    @Test
    public void showTicketsTest() throws Exception {
        TicketService tickets = new TicketService();
        ArrayList<String> ticketsList = tickets.getUserTicketsStrings("test_admin");
        StringBuilder result = new StringBuilder();
        for (String ticket : ticketsList) {
            result.append(ticket);
        }
        log.debug(result);
        //assertEquals(result.toString(), "");
    }
}