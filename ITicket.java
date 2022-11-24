/**
 * This interface defines getter methods for each plane tickets data attributes
 * and is implemented by classes that represent a ticket and its associated
 * data.
 */
public interface ITicket {

    /**
     * Returns the price of the ticket.
     * @return name of the ticket
     */
    String getPrice();

    /**
     * Returns a string that contains the destination of the ticket
     * as a single string
     * @return destination as single string
     */
    String getDestination();

    /**
     * Returns  the departure name of this ticket
     * @return departure name of plane ticket
     */
    String getDeparture();
}
