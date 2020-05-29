package by.epam.ultraair.presentation.output;

import by.epam.ultraair.persistence.domain.Flight;
import by.epam.ultraair.persistence.service.TicketService;
import by.epam.ultraair.persistence.service.UserService;
import by.epam.ultraair.presentation.dto.FlightDTO;
import by.epam.ultraair.presentation.dto.TicketDTO;
import by.epam.ultraair.persistence.service.FlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;

public class FlightsConsoleMenu {
    public final static Logger log = LogManager.getLogger(FlightsConsoleMenu.class.getSimpleName());
    static final Scanner sc = new Scanner(System.in);

    boolean isAdmin = false;
    String userLogin = "guest";

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        FlightsConsoleMenu console = new FlightsConsoleMenu(); //variables creating
        String menuStr = "\n------\n"
                + "1. All flights;\n"
                + "2. Log in;\n"
                + "3. Exit.\n"
                + "------\n"
                +"Choose menu item\n>> ";
        String menuStrAdmin = "\n------\n"
                + "1. All flights;\n"
                + "2. Your tickets;\n"
                + "3. Logout;\n"
                + "4. Manage flights;\n"
                + "5. Exit.\n"
                + "------\n"
                +"Choose menu item\n>> ";
        String menuStrUser = "\n------\n"
                + "1. All flights;\n"
                + "2. Your tickets;\n"
                + "3. Logout;\n"
                + "4. Exit.\n"
                + "------\n"
                +"Choose menu item\n>> ";
        console.menu(menuStr, menuStrAdmin, menuStrUser);
    }

    public void menu(String menuStr, String menuStrAdmin, String menuStrUser) {
        int choose = -1;
        int chooseExit = 3;
        while(choose != chooseExit){
            chooseExit = 3;
            chooseExit = (isAdmin) ? 5 : chooseExit;
            chooseExit = (!isAdmin && !userLogin.equals("guest")) ? 4 : chooseExit;
            menuOutput(menuStr, menuStrAdmin, menuStrUser);
            while (!sc.hasNextInt() || (choose = sc.nextInt()) < 1 || choose > chooseExit){ //check for proper input
                wrongInput();
                System.out.print(">> ");
            }
            switch (choose) {
                case 1:
                    try {
                        chooseFlight();
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
                    break;
                case 2:
                    if(userLogin.equals("guest")) {
                        userLogin = logInOut();
                    }
                    else {
                        try {
                            showTickets();
                        } catch (Exception e){
                            log.error(e.getMessage());
                        }
                    }
                    break;
                case 3:
                    if (chooseExit == 3) break;
                    userLogin = logInOut();
                    break;
                case 4:
                    if (chooseExit == 4) break;
                    if(isAdmin) manageFlights();
            }
        }
    }

    private String logInOut() {
        if (!userLogin.equals("guest")){ //case when user press logout
            System.out.println("Now you logged as guest");
            pause();
            isAdmin = false;
            return "guest";
        }

        //when you press log in
        System.out.print("\nEnter your login\n>> ");
        String login = sc.next();
        UserService userService = new UserService();
        boolean admin;
        try {
            admin = userService.isAdmin(login);
        }
        catch (Exception ignored){
            System.out.println("There are no such user. Returning to menu.");
            pause();
            return "guest";
        }
        if (admin) {
            System.out.println("\nHello, " + login +". You've logged as admin!");
            log.info("Admin logged in.");
            isAdmin = true;
        }
        else {
            System.out.println("\nHello, " + login +". You've logged as user!");
        }
        pause();
        return login;
    }

    public void manageFlights() {
        int chooseExit = 3;
        int choose = -1;
        while(choose != chooseExit) {
            System.out.print("\nManage menu"
                    + "\n------\n"
                    + "1. Add;\n"
                    + "2. Delete;\n"
                    + "3. Exit.\n"
                    + "------\n"
                    + "Choose menu item\n>> ");
            while (!sc.hasNextInt() || (choose = sc.nextInt()) < 1 || choose > 3) { //check for proper input
                wrongInput();
                System.out.print(">> ");
            }
            switch (choose) {
                case 1:
                    addFlight();
                    break;
                case 2:
                    try {
                        deleteFlight();
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
            }
            System.out.println();
        }
    }

    public void addFlight() {
        FlightDTO flight = new FlightDTO();
        System.out.print("\nWhere does the flight come from?\n>> ");
        flight.from = sc.next();

        System.out.print("Where the flight goes to?\n>> ");
        flight.to = sc.next();

        System.out.print("Enter amount of passengers:\n>> ");
        while (!sc.hasNextInt() || (flight.passengersAmount = sc.nextInt()) < 0) { //check for proper input
            wrongInput();
            System.out.print(">> ");
        }

        System.out.print("Enter departure date(dd.MM.yyyy)\n>> ");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        if (sc.hasNextLine()){
            sc.nextLine();
        }
        boolean inputSuccess = false;
        while (!inputSuccess) {
            try {
                Date date = format.parse(sc.nextLine());
                if (date.getTime() < 0){
                    throw new Exception();
                }
                flight.departureDate = date;
                inputSuccess = true;
            } catch (Exception ignored) {
                System.out.print("Input error\n>> ");
            }
        }

        System.out.print("Enter arrival date(dd.MM.yyyy)\n>> ");
        inputSuccess = false;
        while (!inputSuccess) {
            try {
                Date date = format.parse(sc.nextLine());
                if (date.getTime() < 0){
                    throw new Exception();
                }
                flight.arrivalDate = date;
                inputSuccess = true;
            } catch (Exception ignored) {
                System.out.print("Input error\n>> ");
            }
        }

        FlightService flightService = new FlightService();
        flightService.addFlight(flight);
        System.out.println("Flight successfully added.");
        pause();
    }

    public void deleteFlight() throws Exception {
        int flightsSize = showFlights();
        System.out.print("Choose flight\n>> ");
        while (!sc.hasNextInt()) { //check for proper input
            wrongInput();
            System.out.print(">> ");
        }
        int flightNum = sc.nextInt();
        if (flightNum < 1 || flightNum > flightsSize){
            System.out.println("There are no such flight. Go to menu");
            pause();
            return;
        }
        FlightService flightService = new FlightService();
        ArrayList<Flight> flights = flightService.getFlights();
        flightService.deleteFlight(flights.get(flightNum - 1));
        System.out.println("Flight deleted successfully.");
        pause();
    }

    public void chooseFlight() throws Exception {
        int flightsSize = showFlights();

        if(userLogin.equals("guest")) {
            System.out.println("Log in to order a ticket. Returning to main menu.");
            pause();
            return;
        }

        System.out.print("Enter number of flight to buy a ticket\n>> ");
        while (!sc.hasNextInt()) { //check for proper input
            wrongInput();
            System.out.print(">> ");
        }

        int flightNum = sc.nextInt();
        if (flightNum < 1 || flightNum > flightsSize){
            System.out.println("There are no such flight. Returning to menu");
            pause();
            return;
        }
        createTicket(flightNum);
    }

    //also return size of flights array
    int showFlights() throws Exception {
        FlightService flights = new FlightService();
        ArrayList<String> flightsList = flights.getFlightsStrings();
        System.out.print("\nFlights:\n------\n");
        for (int i = 0; i < flightsList.size(); i++) {
            System.out.println((i+1) + ". " + flightsList.get(i));
        }
        System.out.print("------\n");
        return flightsList.size();
    }

    public void createTicket(int flightNum) throws Exception {
        TicketDTO ticket = new TicketDTO();
        Flight flight = new FlightService().getFlights().get(flightNum - 1);

        ticket.flightID = flight.getId();
        ticket.clientName = userLogin;

        System.out.print("Do you have luggage?(y/n)\n>> ");
        ticket.clientHaveLuggage = sc.next().equals("y");
        if(ticket.clientHaveLuggage){
            System.out.print("Enter luggage weight\n>> ");
            while (!sc.hasNextInt() || (ticket.luggageWeight = sc.nextInt()) <= 0) { //check for proper input
                wrongInput();
                System.out.print(">> ");
            }
        }

        System.out.print("Do you want to be first on registration?(y/n)\n>> ");
        ticket.wantRightFirstRegistration = sc.next().equals("y");

        System.out.print("Do you want to be first on sitting?(y/n)\n>> ");
        ticket.wantRightFirstSitting = sc.next().equals("y");

        //service processing
        TicketService ticketService = new TicketService();
        double ticketPrice = ticketService.getTicketPrice(ticket);
        System.out.print("\nFinal price is " + ticketPrice + ". Do you want to buy it?(y/n)\n>> ");
        if(sc.next().equals("y")){
            ticketService.createUserTicket(ticket);
            System.out.println("\nYour " + ticket.toString() + '\n');
        }
        pause();
    }

    public void showTickets() throws Exception{
        TicketService tickets = new TicketService();
        ArrayList<String> ticketsList = tickets.getUserTicketsStrings(userLogin);
        System.out.print("\nYour tickets:\n------\n");
        for (int i = 0; i < ticketsList.size(); i++) {
            System.out.println((i+1) + ". " + ticketsList.get(i));
        }
        if(ticketsList.size() == 0){
            System.out.println("You have no tickets...");
        }
        System.out.print("------\n");
        pause();
    }

    public void menuOutput(String menuStr, String menuStrAdmin, String menuStrUser){
        System.out.print("Hello, " + userLogin + "! ");
        if(isAdmin) {
            System.out.print(menuStrAdmin);//check for admin menu
        }
        else if(!userLogin.equals("guest")) {
            System.out.print(menuStrUser);
        }
        else {
            System.out.print(menuStr);
        }
    }

    public static void wrongInput(){
        sc.nextLine();
        System.out.println("Input error");
    }

    public static void pause(){
        System.out.print("Enter any symbol to continue...");
        sc.next();
        System.out.println();
    }
}
