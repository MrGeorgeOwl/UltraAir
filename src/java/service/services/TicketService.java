package service.services;

import dao.entities.TicketEntity;
import dao.repositories.TicketRepository;
import presentation.DTO.TicketDTO;
import org.json.simple.parser.ParseException;
import service.domain_objects.Ticket;
import service.mappers.TicketMapper;

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
