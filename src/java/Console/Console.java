package Console;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Console {
    public static Logger log = LogManager.getLogger(Console.class.getName());
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Console console = new Console(); //variables creating
        String menuPart1 = "\"Меню\""
                + "\n------\n"
                + "1. Show all flights;\n"
                + "2. Log in as Admin;\n";
        String menuPart2Admin = "3. Manage flights;\n"
                + "4. Exit.\n"
                + "------\n"
                +"Choose menu item\n>> ";
        String menuPart2 = "3. Exit.\n"
                + "------\n"
                +"Choose menu item\n>> ";
        console.menu(false, menuPart1, menuPart2, menuPart2Admin);
    }

    public void menu(boolean admin, String menuPart1
            , String menuPart2, String menuPart2Admin/*service array of class*/) {
        int choose = -1;
        int chooseExit = 4;
        while(choose != chooseExit){
            chooseExit = (admin) ? 4 : 3;
            System.out.print(menuPart1 + ((admin) ? menuPart2Admin : menuPart2)); //check for admin menu
            while (!sc.hasNextInt() || (choose = sc.nextInt()) < 1 || choose > chooseExit){ //check for proper input
                wrongInput(sc);
                System.out.print(">> ");
            }

            switch (choose) {
                case 1:
                    //get all flights from service
                    //complete service required
                    break;
                case 2: {
                    admin = logInAsAdmin();
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

    private boolean logInAsAdmin(){
        boolean admin = false;
        System.out.print("\nEnter password\n>> ");
        String password = sc.next();
        if (password.equals("admin")) { //later add some password guard!!!
            System.out.println("You've logged as admin!");
            admin = true;
        }
        else{
            System.out.println("Incorrect password.");
        }
        System.out.print("Enter any symbol to continue...");
        sc.next();
        System.out.println();
        return admin;
    }

    public void manageFlights(){
        System.out.print("Choose flight to manage\n>> ");

        System.out.println("***THIS PART INCOMPLETE***\n");
    }

    public void showFlights(){
        System.out.print("\nFlights:\n------\n");
        /*for(int i = 0; i < ...; i++){...}*/
        //get flights from service
        System.out.println("***THIS PART INCOMPLETE***\n");
        System.out.print("\n------\n");
    }

    private static void wrongInput(Scanner sc){
        sc.nextLine();
        System.out.println("Input error");
    }
}
