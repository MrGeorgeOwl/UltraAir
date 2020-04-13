package by.epam.ultraair.persistence.services;

import by.epam.ultraair.dao.repositories.TicketRepository;
import by.epam.ultraair.presentation.dto.TicketDTO;
import by.epam.ultraair.persistence.domain_objects.Ticket;
import by.epam.ultraair.persistence.mappers.TicketMapper;
import by.epam.ultraair.dao.entities.TicketEntity;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class TicketService {
    private TicketMapper ticketMapper = new TicketMapper();
    private TicketRepository ticketRepository = new TicketRepository();

    public TicketService() throws ParseException, java.text.ParseException, IOException {
    }

    public void receiveUserTicket(TicketDTO ticketDTO) throws Exception {
        TicketEntity ticketEntity = ticketMapper.mapToTicketEntity(ticketDTO);

        ticketRepository.add(ticketEntity);
    }

    public double getTicketPrice(TicketDTO ticketDTO){
        return ticketMapper.mapToTicket(ticketDTO).getPrice();
    }

    public ArrayList<Ticket> getTickets() throws Exception {
        Hashtable<Integer, TicketEntity> ticketEntityHashtable = ticketRepository.getAll();
        ClientService clientService = new ClientService();
        ArrayList<Ticket> tickets = new ArrayList<>();

        for (Integer key: ticketEntityHashtable.keySet()){
            TicketEntity ticketEntity = ticketEntityHashtable.get(key);
            Ticket ticket = ticketMapper.mapToTicket(ticketEntity);
            tickets.add(ticket);
        }

        return tickets;
    }

    public ArrayList<Ticket> getUserTickets(String username) throws Exception {
        ArrayList<Ticket> tickets = getTickets();
        tickets.removeIf(ticket -> !ticket.getClient().getName().equals(username));
        return tickets;
    }

    public ArrayList<String> getUserTicketsStrings(String username) throws Exception{
        ArrayList<Ticket> userTickets = getUserTickets(username);
        ArrayList<String> userTicketsStrings = new ArrayList<>();

        for(Ticket ticket: userTickets){
            userTicketsStrings.add(ticket.toString());
        }

        return userTicketsStrings;
    }
}
