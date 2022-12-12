// --== CS400 Project 3 File Header Information ==--
// Name: Dylan Coe
// CSL Username: dcoe
// Email: dtcoe@wisc.edu
// Lecture #: 002 @2:30
// Notes to Grader:import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TicketLoader implements ITicketLoader {

  /**
   * Loads all lines of a DOT file into a list of Ticket Objects
   */
  @Override
  public  List<ITicket> loadTickets(String filepathToDot) throws FileNotFoundException { 
    if (!isDOTFile(filepathToDot)) //ensure we're loading from a DOT file
      throw new IllegalArgumentException("Error: file must have .gv or .dot extension.\n");

    List<ITicket> ticketList = new ArrayList<ITicket>();
    try {
      try (Stream<String> dot = Files.lines(Path.of(filepathToDot))) {// read dot file into stream
        List<String> lines = dot.collect(Collectors.toList());// convert to list
        // ignore first and last lines, no actual data there
        lines.remove(0);
        lines.remove(lines.size()-1);
        
        for (String line : lines) {// create a new Ticket object for each line
          String departure = "";
          String destination = "";
          String price = "";
          // use regex to get the departure/destination city
          Pattern cityPattern = Pattern.compile("[A-Z]\\D+[A-Z][A-Z]"); // regex pattern for finding the departure/destination
          Matcher cityMatcher = cityPattern.matcher(line);
          while (cityMatcher.find()) {
            // split by the arrow symbol
            String[] cities = cityMatcher.group().split("\" -> \"");
            departure = cities[0];
            destination = cities[1];  
          }
          // use regex to get the ticket price
          Pattern pricePattern = Pattern.compile("\\d+.\\d\\d"); // regex pattern for finding the price
          Matcher priceMatcher = pricePattern.matcher(line);
          while (priceMatcher.find()) {
            price = priceMatcher.group(); 
          }
          // add the ticket to the list
          ticketList.add(new Ticket(departure, destination, Double.parseDouble(price)));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ticketList;
  }
  
  /**
   * Check that we have the correct file extension (.dot or .gv). It is case insensitive
   * 
   * @param filepath of dot
   * @return true if extension is .dot or .gv, false otherwise
   */
  protected boolean isDOTFile(String filepath) {
    boolean isDOT = Character.toLowerCase(filepath.charAt(filepath.length()-1)) == 't' && Character.toLowerCase(filepath.charAt(filepath.length()-2)) == 'o'
        && Character.toLowerCase(filepath.charAt(filepath.length()-3)) == 'd';
    boolean isGV = Character.toLowerCase(filepath.charAt(filepath.length()-1)) == 'v' && Character.toLowerCase(filepath.charAt(filepath.length()-2)) == 'g';
    return isDOT || isGV;
  }
}

