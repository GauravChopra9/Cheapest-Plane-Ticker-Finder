import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FlightMapper {
    
    public static void main(String[] args) throws FileNotFoundException {
        ITicketLoader ticketLoader = new TicketLoader();
	// load the flight tickets from the data file
	List<ITicket> ticketList = ticketLoader.loadTickets("TicketDOT.gv");
	// instantiate the back-end
	ITicketBackend backend = new TicketBackend(new FlightTicketGraph<String, Double>());
	// add all the tickets into the back-end
	for (ITicket ticket: ticketList) {
	    backend.addTicket(ticket);
	}
	// instantiate the scanner for user input
	Scanner userInputScanner = new Scanner(System.in);
        // instantiate the front-end and pass references to the scanner
	// and back-end to it
	ITicketFrontEnd frontend = new TicketFrontend(userInputScanner, backend, ticketLoader);
	// start the input loop of the front end
	frontend.runCommandLoop();
    }
    
}
