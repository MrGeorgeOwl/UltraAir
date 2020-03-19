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

    public static void main(String[] args) throws Exception {
        Console console = new Console(); //variables creating
        String menuPart1 = "\"Меню\""
                + "\n------\n"
                + "1. Show all flights;\n";
        String menuPart2 = "2. Log in as Admin;\n"
                + "3. Exit.\n"
                + "------\n"
                +"Choose menu item\n>> ";
        String menuPart2Admin = "2. Logout;\n"
                + "3. Manage flights;\n"
                + "4. Exit.\n"
                + "------\n"
                +"Choose menu item\n>> ";
        console.menu(false, menuPart1, menuPart2, menuPart2Admin);
    }

    public void menu(boolean admin, String menuPart1
            , String menuPart2, String menuPart2Admin/* + service array of class later*/)
            throws Exception {
        int choose = -1;
        int chooseExit = 4;
        while(choose != chooseExit){
            chooseExit = (admin) ? 4 : 3;
            System.out.print(menuPart1 + ((admin) ? menuPart2Admin : menuPart2)); //check for admin menu
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
                    admin = logInOutAsAdmin(admin);
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

    private boolean logInOutAsAdmin(boolean admin){
        if (admin){ //case when admin want to logout
            System.out.println("Now you logged as usual user");
            log.info("Admin logout");
            pause();
            return false;
        }
        //when you press log in
        System.out.print("\nEnter password\n>> ");
        String password = sc.next();
        if (password.equals("admin")) { //maybe later add some guard
            System.out.println("You've logged as admin!");
            log.info("Admin logged in.");
            admin = true;
        }
        else{
            log.warn("Unsuccessful try to log as admin");
            System.out.println("Incorrect password.");
        }
        pause();
        return admin;
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
