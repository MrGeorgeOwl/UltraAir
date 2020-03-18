package Service;

import Presentation.TicketDTO;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TicketMapper {
    private FlightService flightService = new FlightService();

    public TicketMapper() throws ParseException, java.text.ParseException, IOException {
    }

    // TODO: Create method for mapping data from DTO to Entity
    public Ticket mapToTicket(TicketDTO ticketDTO) throws Exception {
        String name = ticketDTO.clientName;
        boolean haveLuggage = ticketDTO.clientHaveLuggage;

        Client client = new Client(name, haveLuggage);

        Flight flight = flightService.getFlightByOrder(ticketDTO.flightOrder);
        boolean rightFirstSitting = ticketDTO.wantRightFirstSitting;
        boolean rightFirstRegistration = ticketDTO.wantRightFirstRegistration;
        return new Ticket(client, flight, rightFirstRegistration, rightFirstSitting);
    }
}
