import java.util.ArrayList;
import java.util.List;

/**
 * This is a placeholder class
 */
public class TicketBackendFD implements ITicketBackend {

    public boolean searchByDeparture(String name) {
        return true;
    }

    public boolean searchByDestination(String name) {
        return true;
    }

    public boolean pathFound(String departure, String destination) {
        return true;
    }

    public List<ITicket> getCheapestPath(String departure, String destination) {
        List<ITicket> path = new ArrayList<>();
        path.add(new Ticket("400", "Madison", "Chicago"));
        path.add(new Ticket("500", "Chicago", "New York"));
        path.add(new Ticket("200", "New York", "Boston"));
        return path;
    }

    public List<ITicket> getLeastTransfer(String departure, String destination) {
        List<ITicket> path = new ArrayList<>();
        path.add(new Ticket("400", "Madison", "Chicago"));
        path.add(new Ticket("500", "Chicago", "Seattle"));
        path.add(new Ticket("200", "Seattle", "Boston"));
        return path;
    }
}

