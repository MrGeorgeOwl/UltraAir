package by.epam.ultraair.persistence.rest.controller;

import by.epam.ultraair.dao.implementation.TicketDAOImpl;
import by.epam.ultraair.dao.interfaces.TicketDAO;
import by.epam.ultraair.persistence.domain.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/tickets")
public class TicketController {
    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets(){
        return ResponseEntity.ok(
                new TicketDAOImpl().getAll()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Ticket> findTicketById(@PathVariable Integer id) {
        Ticket ticket = new TicketDAOImpl().findById(id).orElse(null);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("create")
    public ResponseEntity<String> createTicket(@RequestBody Map<String, String> requestBody){
        Integer userId = Integer.valueOf(requestBody.get("userId"));
        Integer flightId = Integer.valueOf(requestBody.get("flightId"));
        boolean rightFirstRegistration = requestBody.get("rightFirstRegistration").equals("true");
        boolean rightFirstSitting = requestBody.get("rightFirstSitting").equals("true");
        new TicketDAOImpl().createTicket(new Ticket(userId, flightId, rightFirstRegistration, rightFirstSitting));
        return new ResponseEntity<>("Ticket created", HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Integer id){
        TicketDAOImpl ticketDAO = new TicketDAOImpl();
        Ticket ticket = ticketDAO.findById(id).orElse(null);
        ResponseEntity<String> responseEntity = null;
        if (ticket != null){
            ticketDAO.deleteTicket(ticket);
            responseEntity = new ResponseEntity<>("Ticket with id: " + id + " deleted", HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>("No such ticket with id " + " id", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateTicket(@PathVariable Integer id, @RequestBody Map<String, String> requestBody){
        TicketDAO ticketDAO = new TicketDAOImpl();
        Ticket ticket = ticketDAO.findById(id).orElse(null);
        ResponseEntity<String> responseEntity = null;
        if (ticket != null){
            ticket.setUserId(Integer.parseInt(requestBody.get("userId")));
            ticket.setFlight(Integer.parseInt(requestBody.get("flightId")));
            ticket.setRightFirstRegistration(requestBody.get("rightFirstRegistration").equals("true"));
            ticket.setRightFirstSitting(requestBody.get("rightFirstSitting").equals("true"));
            ticketDAO.updateTicket(ticket);

            responseEntity = new ResponseEntity<>("Ticket with id: " + id + " updated", HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>("No such ticket with id " + " id", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
