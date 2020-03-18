package Service;

import DAO.TicketEntity;
import Presentation.TicketDTO;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TicketMapper {
    private FlightService flightService = new FlightService();
    private FlightIndexIdMapper flightIndexIdMapper = new FlightIndexIdMapper();

    public TicketMapper() throws ParseException, java.text.ParseException, IOException {
    }

    public TicketEntity mapToTicketEntity(TicketDTO ticketDTO) throws Exception {
        Ticket ticket = mapToTicket(ticketDTO);

        Integer flightId = flightIndexIdMapper.getIdByIndex(ticketDTO.flightOrder);
        double price = ticket.getPrice();
        boolean rightFirstSitting = ticket.isRightFirstSitting();
        boolean rightFirstRegistration = ticket.isRightFirstRegistration();

        return new TicketEntity(flightId, price, rightFirstSitting, rightFirstRegistration);

    }

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
