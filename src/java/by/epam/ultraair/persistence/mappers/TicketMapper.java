package by.epam.ultraair.persistence.mappers;

import by.epam.ultraair.dao.entities.TicketEntity;
import by.epam.ultraair.presentation.dto.TicketDTO;
import org.json.simple.parser.ParseException;
import by.epam.ultraair.persistence.domain_objects.Client;
import by.epam.ultraair.persistence.domain_objects.Flight;
import by.epam.ultraair.persistence.domain_objects.Ticket;
import by.epam.ultraair.persistence.services.ClientService;
import by.epam.ultraair.persistence.services.FlightService;

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
        String username = ticket.getClient().getName();

        return new TicketEntity(flightId, price, rightFirstSitting, rightFirstRegistration, username);

    }

    public Ticket mapToTicket(TicketDTO ticketDTO) {
        String name = ticketDTO.clientName;
        boolean haveLuggage = ticketDTO.clientHaveLuggage;

        Client client = new Client(name, haveLuggage);

        Flight flight = flightService.getFlightByOrder(ticketDTO.flightOrder);
        boolean rightFirstSitting = ticketDTO.wantRightFirstSitting;
        boolean rightFirstRegistration = ticketDTO.wantRightFirstRegistration;
        return new Ticket(client, flight, rightFirstRegistration, rightFirstSitting);
    }

    public Ticket mapToTicket(TicketEntity ticketEntity) throws Exception {
        ClientService clientService = new ClientService();
        Client client = clientService.getByUsername(ticketEntity.getUsername());

        FlightService flightService = new FlightService();
        Flight flight = flightService.getFlightById(ticketEntity.getFlightId());
        boolean rightFirstSitting = ticketEntity.isRightFirstSitting();
        boolean rightFirstRegistration = ticketEntity.isRightFirstRegistration();
        return new Ticket(client, flight, rightFirstRegistration, rightFirstSitting);
    }
}
