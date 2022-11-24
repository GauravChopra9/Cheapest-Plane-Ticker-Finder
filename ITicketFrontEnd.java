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
     * @param ticket the ticket list
     */
    public void displayPlaces(IFDTicket ticket);

    /**
     * Displays a list of prices
     * @param ticket the ticket list
     */
    public void displayPrices(IFDTicket ticket);

    /**
     * Displays the cheapest path based on the inputted start and destination points
     */
    public void cheapestPathSearch();
}


