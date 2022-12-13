import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a placeholder class
 */
public class TicketLoaderFD implements ITicketLoader
{
    @Override
    public List<ITicket> loadTickets(String filepathToDOT) throws FileNotFoundException {
        List<ITicket> list = new ArrayList<>();
        list.add(new FDTicket(1000.0, "Madison", "Boston"));
        list.add(new FDTicket(2000.0, "New York", "Los Angeles"));
        list.add(new FDTicket(300.0, "Atlanta", "New York"));
        list.add(new FDTicket(2000.0, "Atlanta", "Los Angeles"));
        return list;
    }
}

