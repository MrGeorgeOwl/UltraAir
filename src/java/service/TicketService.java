package service;

import dao.entities.TicketEntity;
import dao.repositories.TicketRepository;
import presentation.DTO.TicketDTO;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class TicketService {
    private TicketMapper ticketMapper = new TicketMapper();
    private TicketRepository ticketRepository = new TicketRepository();

    public TicketService() throws ParseException, java.text.ParseException, IOException {
    }

    public void receiveUserTicket(TicketDTO ticketDTO) throws Exception {
        TicketEntity ticketEntity = ticketMapper.mapToTicketEntity(ticketDTO);

        ticketRepository.add(ticketEntity);
    }

    public double getTicketPrice(TicketDTO ticketDTO) throws Exception {
        return ticketMapper.mapToTicket(ticketDTO).getPrice();
    }
}
