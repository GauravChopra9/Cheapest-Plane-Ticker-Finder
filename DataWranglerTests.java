// --== CS400 Project 3 File Header Information ==--
// Name: Dylan Coe
// CSL Username: dcoe
// Email: dtcoe@wisc.edu
// Lecture #: 002 @2:30
// Notes to Grader:
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DataWranglerTests {
  static File dotFile = new File("Dot.gv");
  static List<ITicket> ticketList;
  static List<String> csvList;
  static List<String> dotList;
  static CSVToDOTLoader dotLoader;
  static TicketLoader ticketLoader;
  
  /**
   * Loads and converts a CSV to a DOT, then loads a list of Tickets from the dot
   * 
   * @throws FileNotFoundException
   */
  @BeforeAll
  public static void initializeData() throws FileNotFoundException{
    // convert csv to dot
    PrintStream o = new PrintStream(dotFile); // set printstream to the dot file
    PrintStream console = System.out;
    System.setOut(o); // set output to file
    
    dotLoader = new CSVToDOTLoader();
    try {
      csvList = dotLoader.loadCSV("Consumer_Airfare_Report__Table_1_-_Top_1_000_Contiguous_State_City-Pair_Markets.csv");
    }
    catch (IOException e) {
      assertTrue(false);
    }
    
    CSVToDOTLoader.printDOT(csvList); // print out contents of list in DOT format
    
    // read from dot
    ticketLoader = new TicketLoader();
    try {
      ticketList = ticketLoader.loadTickets("Dot.gv");
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.setOut(console); // set output back to console
    
    // convert DOT to list for testing
    dotList = DOTToList("Dot.gv");
  }
 
  /**
   * Deletes the test dot file after all the tests are finished
   */
  @AfterAll
  public static void cleanup() {
    dotFile.delete();
  }

  /**
   * @param filepathToDot file path to the dot file
   * @return a list containing each line of the dot file
   */
  public static List<String> DOTToList(String filepathToDot) {
    List<String> lines = new ArrayList<String>();
    try {
      try (Stream<String> dot = Files.lines(Path.of(filepathToDot))) {// read dot file into stream
        lines = dot.collect(Collectors.toList());// convert to list
        
        }     
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lines;
  }
   /**
   * Tests functionality of Ticket class
   * 
   * @return true if all test cases pass
   */
  @Test
  public void test1() {
    Ticket tick1 = new Ticket("Miami, FL (Metropolitan Area)", "Rochester, NY", "151.46");
    Ticket tick2 = new Ticket("Atlanta, GA (Metropolitan Area)", "Kansas City, MO", "172.83");
    Ticket tick3 = new Ticket("Los Angeles, CA (Metropolitan Area)", "Salt Lake City, UT","135.24");

    // test getDeparture
    assertEquals(tick1.getDeparture(),"Miami, FL (Metropolitan Area)");
    assertEquals(tick2.getDeparture(),"Atlanta, GA (Metropolitan Area)");
    assertEquals(tick3.getDeparture(),"Los Angeles, CA (Metropolitan Area)");
    // test getDestination
    assertEquals(tick1.getDestination(),"Rochester, NY");
    assertEquals(tick2.getDestination(),"Kansas City, MO");
    assertEquals(tick3.getDestination(),"Salt Lake City, UT");
    // test getPrice
    assertEquals(tick1.getPrice(),"151.46");
    assertEquals(tick2.getPrice(),"172.83");
    assertEquals(tick3.getPrice(),"135.24");
  }
  
  /**
   * Tests loading/converting CSV to DOT and makes sure all rows are loaded
   * 
   * @return true if all test cases pass
   * @throws IOException 
   */
  @Test
  public void test2() throws IOException{
    // test bad filepath
    try {
      dotLoader.loadCSV("fakefile.csv");
    }
    catch (NoSuchFileException e) {
      assertTrue(true);
    }
    // test correct size of loaded csv
    assertEquals(csvList.size(), 284018); // due to some weird formatting in the csv, where some lines are 
                                          // unnecessarily indented, there are more lines of text than actual rows
    // test correct size of converted csv (now dot)
    assertEquals(dotList.size(),106034); // 106032 rows + 2 lines for Digraph G { and }
    
  }
  
  /**
   * Tests that CSVToDOTLoader formats the DOT correctly
   * 
   * @return true if all test cases pass
   */
  @Test
  public void test3() {
    // test regular tickets
    // test (Metropolitan Area) in departure
    // test (Metropolitan Area) in destination
    // test 2 capital letters in price
    // test captial letter, number in price
    
    // test that first line is Digraph G {
    assertEquals(dotList.get(0),"Digraph G {".trim());
    // test that last line is }
    assertEquals(dotList.get(dotList.size()-1),"}".trim());
    
    // test that all rows besides the above 2 are in the dot format - *origin* -> *destination* [label = *weight*]
    Pattern dotPattern = Pattern.compile("\"\\D+,\\D+\" -> \"\\D+, \\D+\" \\[label = \\d+.\\d\\d];"); 
    Matcher dotMatcher;
    boolean correctlyFormatted = true;
    for (int i = 1; i < dotList.size()-1;i++) {
      dotMatcher = dotPattern.matcher(dotList.get(i));
      if (!dotMatcher.find()) { // find the origin/destination
        correctlyFormatted = false;
        break;
      }
    }
    assertTrue(correctlyFormatted);
  }
  
  /**
   * Tests that TicketLoader only takes in dot files and that it loads all lines
   * 
   * @return true if all test cases pass
   */
  @Test
  public void test4() {
    // test isDOTFile()
    assertTrue(ticketLoader.isDOTFile("dot.gv"));
    assertTrue(ticketLoader.isDOTFile("gv.DoT"));
    assertFalse(ticketLoader.isDOTFile("gv.txt"));
    assertFalse(ticketLoader.isDOTFile("gv.dottt"));
    // test that size is correct - loads all Tickets from the DOT
    assertEquals(ticketList.size(),dotList.size()-2); // dotlist size -2 to account for the top and bottom lines
  }
  
  /**
   * Tests that TicketLoader loads all the ticket data correctly from the DOT file
   * 
   * @return true if all test cases pass
   */
  @Test
  public void test5() {
    // test that the contents of the ticketlist match those of the dot file
    for (int i = 0; i < ticketList.size(); i++) {
      Ticket ticket = (Ticket)ticketList.get(i);
      String toMatch = dotList.get(i+1);// dotList index offset by 1 because of the opening Digraph G { line
      // make sure that each line was extracted correctly into the ticket
      assertTrue(toMatch.contains(ticket.getDeparture())&&toMatch.contains(ticket.getDestination())
          &&toMatch.contains(ticket.getPrice()));
    }
  }
  
}

