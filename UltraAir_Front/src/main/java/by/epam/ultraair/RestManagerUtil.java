package by.epam.ultraair;

import by.epam.ultraair.domain.Flight;

import java.text.SimpleDateFormat;
import java.util.*;

import by.epam.ultraair.domain.Ticket;
import by.epam.ultraair.domain.User;
import by.epam.ultraair.presentation.transfer.FlightDTO;
import by.epam.ultraair.presentation.transfer.TicketDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestManagerUtil {
    public static final Logger log = LogManager.getLogger(RestManagerUtil.class.getSimpleName());
    public static final String REST_URL = "http://localhost:8080/api/";

    /* Get all methods */

    public static ArrayList<Flight> getAllFlights() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Flight[]> response = template.getForEntity(
                        REST_URL + "flight",
                        Flight[].class);
        Flight[] rawFlights = response.getBody();
        ArrayList<Flight> flights = new ArrayList<>();
        if (rawFlights != null) {
            Collections.addAll(flights, rawFlights);
        }
        return flights;
    }

    public static ArrayList<Ticket> getAllTickets() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Ticket[]> response = template.getForEntity(
                REST_URL + "tickets",
                Ticket[].class);
        Ticket[] rawTickets = response.getBody();
        ArrayList<Ticket> tickets = new ArrayList<>();
        if (rawTickets != null) {
            Collections.addAll(tickets, rawTickets);
        }
        return tickets;
    }

    public static ArrayList<User> getAllUsers() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<User[]> response = template.getForEntity(
                REST_URL + "users",
                User[].class);
        User[] rawUsers = response.getBody();
        ArrayList<User> users = new ArrayList<>();
        if (rawUsers != null) {
            Collections.addAll(users, rawUsers);
        }
        return users;
    }

    public static ArrayList<Ticket> getUserTickets(String user) {
        ArrayList<Ticket> userTickets = new ArrayList<>();
        for(Ticket ticket: getAllTickets()){
            if (ticket.getUserId() == getUserId(user)){
                userTickets.add(ticket);
            }
        }
        return userTickets;
    }

    /* Util methods */

    public static int getUserId(String username) {
        for(User user: getAllUsers()){
            if (user.getLogin().equals(username)){
                return user.getId();
            }
        }
        return -1;
    }

    public static boolean isUserExists(String username) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<User[]> response = template.getForEntity(
                REST_URL + "users",
                User[].class);
        User[] users = response.getBody();
        if (users != null) {
            for (User user: users){
                if (user.getLogin().equals(username)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isUserAdmin(String username) {
        RestTemplate template = new RestTemplate();
        ResponseEntity<User[]> response = template.getForEntity(
                REST_URL + "users",
                User[].class);
        User[] users = response.getBody();
        if (users != null) {
            for (User user: users){
                if (user.getLogin().equals(username)){
                    return user.isAdmin();
                }
            }
        }
        return false;
    }

    /* Flight CRUD*/

    public static void createFlight(FlightDTO flight) {
        RestTemplate template = new RestTemplate();
        HashMap<String, String> flightMap = new HashMap<>();
        flightMap.put("fromPlace", flight.from);
        flightMap.put("toPlace", flight.to);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        flightMap.put("departureDate", formatter.format(flight.departureDate));
        flightMap.put("arrivalDate", formatter.format(flight.arrivalDate));
        String response = template.postForObject(
                REST_URL + "flight",
                flightMap,
                String.class
                );
        log.debug(response);
    }

    public static Flight getFlight(int id) {
        RestTemplate template = new RestTemplate();
        String url = REST_URL + "flight/" + id;
        ResponseEntity<Flight> response = template.getForEntity(
                url,
                Flight.class);
        return response.getBody();
    }

    public static void updateFlight(Integer id, FlightDTO flight) {
        RestTemplate template = new RestTemplate();
        String url = REST_URL + "flight/" + id;

        HashMap<String, String> flightMap = new HashMap<>();
        flightMap.put("fromPlace", flight.from);
        flightMap.put("toPlace", flight.to);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        flightMap.put("departureDate", formatter.format(flight.departureDate));
        flightMap.put("arrivalDate", formatter.format(flight.arrivalDate));

        template.put(
                url,
                flightMap
        );
    }

    public static void deleteFlight(int id) {
        RestTemplate template = new RestTemplate();
        String url = REST_URL + "flight/" + id;
        template.delete(url);
    }

    /* Ticket CRUD */

    public static void createTicket(TicketDTO ticket) {
        RestTemplate template = new RestTemplate();
        HashMap<String, String> ticketMap = new HashMap<>();
        ticketMap.put("userId", String.valueOf(getUserId(ticket.clientName)));
        ticketMap.put("flightId", String.valueOf(ticket.flightID));
        ticketMap.put("rightFirstRegistration", String.valueOf(ticket.wantRightFirstRegistration));
        ticketMap.put("rightFirstSitting", String.valueOf(ticket.wantRightFirstSitting));
        String response = template.postForObject(
                REST_URL + "tickets",
                ticketMap,
                String.class
        );
        log.debug(response);
    }

    public static Ticket getTicket(int id) {
        RestTemplate template = new RestTemplate();
        String url = REST_URL + "tickets/" + id;
        ResponseEntity<Ticket> response = template.getForEntity(
                REST_URL + "tickets",
                Ticket.class);
        return response.getBody();
    }

    public static void updateTicket(Integer id, TicketDTO ticket) {
        RestTemplate template = new RestTemplate();
        String url = REST_URL + "tickets/" + id;

        HashMap<String, String> ticketMap = new HashMap<>();
        ticketMap.put("userId", String.valueOf(getUserId(ticket.clientName)));
        ticketMap.put("flightId", String.valueOf(ticket.flightID));
        ticketMap.put("rightFirstRegistration", String.valueOf(ticket.wantRightFirstRegistration));
        ticketMap.put("rightFirstSitting", String.valueOf(ticket.wantRightFirstSitting));

        template.put(
                url,
                ticketMap
        );
    }

    public static void deleteTicket(int id) {
        RestTemplate template = new RestTemplate();
        String url = REST_URL + "tickets/" + id;
        template.delete(url);
    }

}
