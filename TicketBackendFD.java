import java.util.ArrayList;
import java.util.List;

/**
 * This is a placeholder class
 */
public class TicketBackendFD implements ITicketBackend {


	public void addTicket(ITicket ticket){
		// Something Happenes
	}

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
        path.add(new FDTicket(400.0, "Madison", "Chicago"));
        path.add(new FDTicket(500.0, "Chicago", "New York"));
        path.add(new FDTicket(200.0, "New York", "Boston"));
        return path;
    }

    public List<ITicket> getLeastTransfer(String departure, String destination) {
        List<ITicket> path = new ArrayList<>();
        path.add(new FDTicket(400.0, "Madison", "Chicago"));
        path.add(new FDTicket(500.0, "Chicago", "Seattle"));
        path.add(new FDTicket(200.0, "Seattle", "Boston"));
        return path;
    }
}

