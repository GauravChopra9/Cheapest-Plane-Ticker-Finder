// --== CS400 Project 3 File Header Information ==--
// Name: Dylan Coe
// CSL Username: dcoe
// Email: dtcoe@wisc.edu
// Lecture #: 002 @2:30
// Notes to Grader:
public class Ticket implements ITicket{
  String departure;
  String destination;
  String price;
  public Ticket(String departure, String destination, String price) {
    this.departure = departure;
    this.destination = destination;
    this.price = price;
  }
  
  @Override
  public String getDeparture() {
    return this.departure;
  }
  
  @Override
  public String getDestination() {
    return this.destination;
  }
  @Override
  public String getPrice() {
    return this.price;
  }

  public String toString() {
    return "[ Departure: " + this.getDeparture() + ", Destination: " + this.getDestination() + ", Price: " + this.getPrice() + " ]";
  }

}

