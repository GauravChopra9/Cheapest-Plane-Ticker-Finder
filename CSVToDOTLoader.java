// --== CS400 Project 3 File Header Information ==--
// Name: Dylan Coe
// CSL Username: dcoe
// Email: dtcoe@wisc.edu
// Lecture #: 002 @2:30
// Notes to Grader:
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CSVToDOTLoader {

  public static void main(String[] args) {
    // run this in the VM and use bash to redirect the output to a file
    CSVToDOTLoader loader = new CSVToDOTLoader();
    List<String> lines;
    try {
      lines = loader.loadCSV("Consumer_Airfare_Report__Table_1_-_Top_1_000_Contiguous_State_City-Pair_Markets.csv");
      printDOT(lines); // print out contents of list in DOT format
    } catch (IOException e) {
      // TODO Auto-generated catch block
      // e.printStackTrace();
    }
    
  }
  
  /**
   * Loads all lines of a csv into a list
   * 
   * @param filepath for the csv
   * @return a list containing all the lines of the csv
   */
  protected List<String> loadCSV(String filepath) throws IOException{
    List<String> lines = new ArrayList<String>();
    try {
      try (// load csv data into stream
      Stream<String> csv = Files.lines(Path.of(filepath))) {
        // convert stream to list
        return csv.collect(Collectors.toList());
        //System.out.println(lines.size());
       
      }
    } catch (IOException e) { // throw exception if it can't find file
      //e.printStackTrace();
    }
    return lines;
  }
  
  /**
   * Print all the lines of the csv into DOT format. 
   * We will be printing to System.out and redirecting it into a file
   * 
   * @param lines
   */
  protected static void printDOT(List<String> lines) {
    Pattern cityPattern = Pattern.compile("\"[A-Z]\\D+\",\"\\D+\""); // regex pattern for finding origin and destination
    Pattern pricePattern = Pattern.compile("\",\\d+,\\d+,\\d+.\\d\\d,"); // regex pattern for finding the fare
    Matcher cityMatcher, priceMatcher;
    System.out.println("Digraph G {"); // header line
    for (String line : lines) { // print each line in dot format
      String departure = ""; // origin city
      String destination = ""; // destination city
      String price = ""; // ticket price
     
      cityMatcher = cityPattern.matcher(line);
      while (cityMatcher.find()) { // find the origin/destination
        String[] cities = cityMatcher.group().split(","); // since it splits by comma, the city name is split into city and state
        departure = cities[0] + ","+ cities[1]; // so we re - merge the two 
        destination = cities[2] + "," + cities[3]; 
      }
      
      priceMatcher = pricePattern.matcher(line);
      while (priceMatcher.find()) {
        // since there's a bunch of colunms with numbers in the csv, the only way I could think of to get 
          // only the fare column was to also grab a few of the columns next to it and then split it
        price = priceMatcher.group().split(",")[3]; 
      }
      // combine all the parts and format it as vertices/edges in a dot
      if (departure != "" && destination != "" && price != "") {
        System.out.println(departure + " -> " + destination + " [label = " + price + "];"); 
      }
    }
    System.out.println("}"); // ending bracket for the dot file
  }
}

