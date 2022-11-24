import java.util.List;

public interface ITicketBackend {
    /**
     * Adds a new stop between the departure and destination to the Backend's 
     * database and is stored in the graph
     * 
     * @param stop the stop name to add
     */
    public boolean addStop(String stop);

    /**
     * Deletes a stop between the departure and destination from the graph.
     * 
     * @param stop to remove
     */
    public boolean removeStop(String stop);

    /**
     * Search through all the stops in the graph and return a list of tickets
     * which have the lowest cost combined to reach the destination
     * 
     * @param dest destination of the journey
     * @return list of tickets
     */
    public List<ITicket> searchByDestination(String dest);
}
