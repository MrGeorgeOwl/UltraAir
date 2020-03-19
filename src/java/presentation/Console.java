package presentation;

import service.FlightService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.TicketService;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    public final static Logger log = LogManager.getLogger(Console.class.getName());
    static final Scanner sc = new Scanner(System.in);

    boolean isAdmin = false;
    String userLogin = "guest";

    public static void main(String[] args) throws Exception {
        Console console = new Console(); //variables creating
        String menuStr = "\n------\n"
                + "1. All flights;\n"
                + "2. Log in;\n"
                + "3. Exit.\n"
                + "------\n"
                +"Choose menu item\n>> ";
        String menuStrAdmin = "\n------\n"
                + "1. All flights;\n"
                + "2. Logout;\n"
                + "3. Manage flights;\n"
                + "4. Exit.\n"
                + "------\n"
                +"Choose menu item\n>> ";
        console.menu(menuStr, menuStrAdmin);
    }

    public void menu(String menuStr, String menuStrAdmin) throws Exception {
        int choose = -1;
        int chooseExit = 4;
        while(choose != chooseExit){
            chooseExit = (isAdmin) ? 4 : 3;
            System.out.print("Hello, " + userLogin + "! ");
            System.out.print(((isAdmin) ? menuStrAdmin : menuStr)); //check for admin menu
            while (!sc.hasNextInt() || (choose = sc.nextInt()) < 1 || choose > chooseExit){ //check for proper input
                wrongInput();
                System.out.print(">> ");
            }

            switch (choose) {
                case 1:
                    //get information about all flights from service
                    showFlights();
                    break;
                case 2: {
                    userLogin = logInOut();
                    break;
                }
                case 3: {
                    if (chooseExit == 3){
                        break;
                    }
                    manageFlights();
                }
            }
        }
    }

    private String logInOut(){
        if (!userLogin.equals("guest")){ //case when user want to logout
            System.out.println("Now you logged as guest");
            pause();
            isAdmin = false;
            return "guest";
        }
        //when you press log in
        System.out.print("\nEnter your login\n>> ");
        String login = sc.next();

        if (true/*TODO: get response from service if login as admin*/) { //maybe later add some guard
            System.out.println("\nHello, " + login +". You've logged as admin!");
            log.info("Admin logged in.");
            isAdmin = true;
        }
        else if (false/*TODO: get if login is old user*/) {
            System.out.println("\nHello, " + login +". You've logged as user!");
        }
        else{
            //TODO: create new user with unknown login
            System.out.println("\nWelcome, " + login +". You've logged as a new user!");
        }
        pause();
        return login;
    }

    public void manageFlights(){
        System.out.print("Choose flight to manage\n>> ");
        /*TODO: ability to add and delete flights data*/
        log.warn("***THIS PART INCOMPLETE***\n");
    }

    public void showFlights() throws Exception {
        FlightService flights = new FlightService();
        ArrayList<String> flightsList = flights.getFlightsStrings();
        System.out.print("\nFlights:\n------\n\n");
        for (int i = 0; i < flightsList.size(); i++) {
            System.out.println((i+1) + ". " + flightsList.get(i));
        }
        System.out.print("------\n");

        System.out.print("Enter number of flight to buy a ticket\n>> ");
        while (!sc.hasNextInt()) { //check for proper input
            wrongInput();
            System.out.print(">> ");
        }

        int flightNum = sc.nextInt();
        if (flightNum < 1 || flightNum > flightsList.size()){
            System.out.println("There are no such flight. Returning to menu");
            pause();
            return;
        }
        createTicket(flightNum);
    }

    public void createTicket(int flightNum) throws Exception {
        TicketDTO ticket = new TicketDTO();

        ticket.flightOrder = flightNum - 1;
        System.out.print("Enter you name\n>> ");
        ticket.clientName = sc.next();

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
            ticketService.receiveUserTicket(ticket);
            System.out.println("\nYour " + ticket.toString() + '\n');
        }
        pause();
    }

    private static void wrongInput(){
        sc.nextLine();
        System.out.println("Input error");
    }

    private static void pause(){
        System.out.print("Enter any symbol to continue...");
        sc.next();
        System.out.println();
    }
}
