package Service;

import Presentation.TicketDTO;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TicketService {
    private TicketMapper ticketMapper = new TicketMapper();

    public TicketService() throws ParseException, java.text.ParseException, IOException {
    }

    public void receiveUserTicket(TicketDTO ticketDTO){
        // TODO: Using mapper to translate info to entity and then give it to TicketRepository
    }

    public double getTicketPrice(TicketDTO ticketDTO) throws Exception {
        return ticketMapper.mapToTicket(ticketDTO).getPrice();
    }
}
