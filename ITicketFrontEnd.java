// --== CS400 Project Two File Header ==--
// Name: Gaurav Chopra
// CSL Username: chopra
// Email: gmchopra@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: None

import java.util.List;

public interface ITicketFrontEnd {

    /**
     * Constructor of front-end ticket library class, assign value to each private field
     *
     * @param userInputScanner read user input
     * @param backend read the back-end ticket library
     */
    //TicketLibraryFrontend(Scanner userInputScanner, ITicketBackend back-end);

    /**
     * This method starts the command loop for the front-end, and will
     * terminate when the user exits the application.
     */
    public void runCommandLoop();

    // to help make it easier to test the functionality of this program,
    // the following helper methods will help support runCommandLoop():

    /**
     * Prints command options to the screen (System.out)
     */
    public void displayMainMenu();

    /**
     * Displays a list of places
     * @param ticket the ticket
     */
    public String displayTicket(ITicket ticket);

    /**
     * Prints all tickets
     */
    public void printAllTickets();

    /**
     * Prints all locations
     */
    public void printAllLocations();

    /**
     * Checks if location is added, helper method for printAllLocations()
     */
    public boolean isAdded(List<String> locations, String location);

    /**
     * Prints cheapest path
     */
    public String printCheapestPath(String departure, String destination);

    /**
     * Prints path with least transfers
     */
    public String printLeastTransfer(String departure, String destination);
}

