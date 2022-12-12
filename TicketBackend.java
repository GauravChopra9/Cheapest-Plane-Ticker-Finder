import java.util.List;
import java.util.NoSuchElementException;

public class TicketBackend implements ITicketBackend {

	private FlightTicketADT<String, Double> graph;
	
	public TicketBackend (FlightTicketADT<String, Double> graph) {
		this.graph = graph;
	}
//
//	/**
//     * Adds a new stop to the backend's database and is stored in the graph internally
//     * 
//     * @param stop the stop to add
//     */
//	@Override
//	public void addStop(String stop) {
//		try {
//		    graph.insertVertex(stop);
//		} catch (NullPointerException e) {
//			// Do nothing
//		}
//	}

	/**
	 * Adds a new ticket to the backend's database and is stored in the graph internally
	 * 
	 * @param ticket the ticket to add
	 */
	@Override
	public void addTicket(ITicket ticket) {
		String departure = ticket.getDeparture();
		String destination = ticket.getDestination();
	    Double price = ticket.getPrice();
		try {
			if (departure == null) {
				graph.insertVertex(departure);
			} else if (destination == null) {
				graph.insertVertex(destination);
			}
			graph.insertEdge(departure, destination, price);
		} catch (Exception e) {
			// Do nothing
		}
	}
	
    /**
     * Search through all the stops in the graph and return true if it has given departure
     * 
     * @param departure departure of the journey
     * @return true when the departure exists
     */
	@Override
    public boolean searchByDeparture(String departure) {
		return graph.containsVertex(departure);
	}

    /**
     * Search through all the stops in the graph and return true if it has given destination
     * 
     * @param destination destination of the journey
     * @return true when the departure exists
     */
	@Override
    public boolean searchByDestination(String destination) {
		return graph.containsVertex(destination);
	}

    /**
     * Search the graph and return true if it has the path from given departure 
     * and destination
     * 
     * @param departure departure of the journey
     * @param destination destination of the journey
     * @return true if there is a path
     */
	@Override
    public boolean pathFound(String departure, String destination) {
		try {
			graph.cheapestPath(departure, destination);
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

    /**
     * Search the graph and return the list of the paths which are cheapest top three
     * 
     * @param departure departure of the journey
     * @param destination destination of the journey
     * @return List List of Ticket for the cheapest path
     */
	@Override
    public List<ITicket> getCheapestPath(String departure, String destination) {
		List<ITicket> cheapestPath = null;
		try {
			cheapestPath = graph.cheapestPath(departure, destination);
		} catch (NoSuchElementException e) {
			// do nothing
		}
		return cheapestPath;
	}

    /**
     * Search the graph and return the list of the paths which are cheapest top three
     * 
     * @param departure departure of the journey
     * @param destination destination of the journey
     * @return List List of Ticket for the least transfer path
     */
	@Override
    public List<ITicket> getLeastTransfer(String departure, String destination) {
		List<ITicket> leastTransferPath = null;
		try {
		    leastTransferPath = graph.leastTransferPath(departure, destination);
		} catch (NoSuchElementException e) {
			// do nothing
		}
		return leastTransferPath;
	}

}
