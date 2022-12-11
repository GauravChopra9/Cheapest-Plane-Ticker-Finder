// --== CS400 Project Two File Header ==--
// Name: Gaurav Chopra
// CSL Username: chopra
// Email: gmchopra@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: None

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class contains the main frontend functionality for this application
 */
public class TicketFrontend implements ITicketFrontEnd {

    private Scanner userInput;
    private ITicketBackend backend;
    private String indent = "          ";

    private ITicketLoader loader;

    /**
     * Constructor which creates objects of this class when taking in a Scanner, ITicketBackend and ITicketLoader
     * objects
     *
     * @param userInput The Scanner object used to get input from the user
     * @param backend The ITicketBacked object
     * @param loader The ITicketLoader object
     */
    public TicketFrontend(Scanner userInput, ITicketBackend backend, ITicketLoader loader) {
        this.userInput = userInput;
        this.backend = backend;
        this.loader = loader;
    }

    /**
     * This method starts the command loop for the front-end, and will terminate
     * when the user exits the application.
     */
    @Override
    public void runCommandLoop() {
        // Printing the beginning statements of the application
        System.out.println("Welcome to the Plain Ticket Application!");
        System.out.println("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x");
        // boolean variable which controls when the while loop ends
        boolean validUser = true;
        // Initializing input
        int input = 0;
        // While loop which runs until validUser is false
        while (validUser) {
            // Displays the starting main menu of the application
            displayMainMenu();

            // reading user's input and checking if it's valid
            try {
                input = Integer.parseInt(userInput.nextLine());
                if (input != 1 && input != 2 && input != 3 && input != 4) {
                    System.out.println("Invalid input! Please select a number between 1 and 4");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
                continue;
            }

            // Entering the List All Locations menu of the application form the main menu if
            // input is 1
            if (input == 1) {
                // Printing all the locations within the data
                printAllLocations();
            }

            // Entering the List All Tickets menu of the application form the main menu if
            // input is 2
            else if (input == 2) {
                // Printing all the tickets within the data
                printAllTickets();
            }

            // Entering the Chose Departure and Arrival Location menu of the application form the main menu if
            // input is 3
            else if (input == 3) {
                // Requesting for Departure and Arrival locations
                System.out.println(indent + "Please Enter Departure Location: ");
                System.out.println(indent + "Please Enter Arrival Location");
                String departure = userInput.nextLine();
                String destination = userInput.nextLine();

                // Accounting for Invalid Cases
                if (departure.isBlank() || destination.isBlank()) {
                    System.out.println("Invalid Input!");
                    continue;
                }

                // Printing the cheapest path and path with the least transfers
                printCheapestPath(departure, destination);
                printLeastTransfer(departure, destination);
            }

            // Entering the exit application menu form the main menu if input is 4
            else if (input == 4) {
                // Exiting the application and printing Goodbye! if the input is 4
                System.out.println("Goodbye!");
                validUser = false;
            }
            input = 0;
        }
    }

    /**
     * This method displays the main menu of the application
     */
    @Override
    public void displayMainMenu() {
        // Displaying the main menu of the application using print statements
        System.out.println();
        System.out.println("You are in the Main Menu:");
        System.out.println(indent + "1) List All Locations\n" + indent + "2) List All Tickets\n" + indent +
                "3) Chose Departure and Arrival Location\n" + indent + "4) Exit Application");
    }

    /**
     * This method can display the ticket object in string format
     *
     * @param ticket The ticket to be displayed as a string
     * @return The String representation of the ticket object
     */
    @Override
    public String displayTicket(ITicket ticket) {
        // get the name, publisher, publish year and genre for all the games in the list
        String price= ticket.getPrice();
        String destination = ticket.getDestination();
        String departure = ticket.getDeparture();
        String t =  "Ticket from " + departure + " to " + destination + " is " + price + " dollars";
        return t;
    }

    /**
     * This method displays all tickets that have been loaded into the list by the DataWrangler
     */
    @Override
    public void printAllTickets() {
        List<ITicket> allTickets = new ArrayList<>();
        int count = 0;
        // loading all the tickets from the dot file into an allTickets List
        try {
            allTickets = loader.loadTickets("");
        }

        catch(FileNotFoundException f) {
            // Do Nothing over here
        }

        // Displaying the tickets with the help of a for loop
        for (int i = 0; i < allTickets.size(); i++) {
            count += 1;
            String ticket = displayTicket(allTickets.get(i));
            System.out.println(count + ") " + ticket);
        }
    }

    /**
     * This method displays all locations of tickets that have been loaded into the list by the DataWrangler
     */
    @Override
    public void printAllLocations() {
        List<ITicket> allTickets = new ArrayList<>();
        List<String> locations = new ArrayList<>();
        int count = 0;
        // loading all the tickets from the dot file into an allTickets List
        try {
            allTickets = loader.loadTickets("");
        }

        catch(FileNotFoundException f) {
            // Do Nothing over here
        }

        // Displaying the locations of all the tickets with the help of a for two loop
        for (int i = 0; i < allTickets.size(); i++) {
            String departureLocation = allTickets.get(i).getDeparture();
            String arrivalLocation = allTickets.get(i).getDestination();

            if (isAdded(locations, departureLocation) == false) {
                locations.add(departureLocation);
            }

            if (isAdded(locations, arrivalLocation) == false) {
                locations.add(arrivalLocation);
            }
        }

        for (int i = 0; i < locations.size(); i++) {
            count += 1;
            System.out.println(count + ") " + locations.get(i));
        }
    }

    /**
     * Helper method for printAllLocations(). It makes sure that no duplicate locations get displayed
     * by checking if they have already been added into a list or not
     *
     * @param locations The list which has all the location's to be checked for
     * @param location The location to be checked if it has been added or not to the list
     * @return Boolean True if the location has already been added to the locations list and false otherwise
     */
    @Override
    public boolean isAdded(List<String> locations, String location) {
        // Using a for loop to check if a location has already been added to a list of locations or not
        for (int i = 0; i < locations.size(); i++){
            if (location.equals(locations.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method helps display the cheapest path from a departure location to a destination location
     *
     * @param departure The departure location of the plane
     * @param destination The destination location of the plane
     * @return The cheapest path as a String
     */
    @Override
    public String printCheapestPath(String departure, String destination) {
        // Getting the list with the help of the backend functionality
        List<ITicket> cheapestList = backend.getCheapestPath(departure, destination);
        String cheapestPath = "";

        // Prints out an error message if the list is null
        if (cheapestList == null) {
            System.out.println("Invalid Input: cheapest path not found!");
        }

        // Else displays the list which consists of tickets of the cheapest path
        else {
            System.out.println("Following is the list of tickets comprising of the cheapest path" +
                    " from " + departure + " to " + destination + ":\n");
            for (int i = 0; i < cheapestList.size(); i++) {
                String ticket = displayTicket(cheapestList.get(i));
                System.out.println("Ticket from " + cheapestList.get(i).getDeparture()
                + " to " + cheapestList.get(i).getDestination());
                cheapestPath = cheapestPath + ticket + "\n";
            }
        }
        return cheapestPath;
    }

    /**
     * This method helps display the path with the least edges from a departure location to a destination location
     *
     * @param departure The departure location of the plane
     * @param destination The destination location of the plane
     * @return The path with the least transfers a string
     */
    @Override
    public String printLeastTransfer(String departure, String destination) {
        // Getting the list with the help of the backend functionality
        List<ITicket> leastTransfer = backend.getLeastTransfer(departure, destination);
        String leastTransferPath = "";

        // Printing out an error message if the list is null
        if (leastTransfer == null) {
            System.out.println("Invalid Input: path not found for least transfer!");
        }

        // Else displays the list which consists of tickets of the path with the least edges
        else {
            System.out.println("\nFollowing is the list of tickets comprising of the path with least transfers"+
                    " from " + departure + " to " + destination + ":\n");
            for (int i = 0; i < leastTransfer.size(); i++) {
                String ticket = displayTicket(leastTransfer.get(i));
                System.out.println("Ticket from " + leastTransfer.get(i).getDeparture()
                        + " to " + leastTransfer.get(i).getDestination());
                leastTransferPath = leastTransferPath + ticket + "\n";
            }
        }
        return leastTransferPath;
    }
}
