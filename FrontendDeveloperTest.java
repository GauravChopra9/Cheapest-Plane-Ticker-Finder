// --== CS400 Project Two File Header ==--
// Name: Gaurav Chopra
// CSL Username: chopra
// Email: gmchopra@wisc.edu
// Lecture #: 002 @2:30pm
// Notes to Grader: None

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

/**
 * This class has methods which tests the functionality of the TicketFrontend class
 */
class FrontendDeveloperTest {

    protected Scanner scan;
    protected TicketBackendFD backend;
    protected TicketLoaderFD loader;
    protected TicketFrontend frontend;
    protected TextUITester test;
    protected String output;

    /**
     * This tester checks if the application main menu prints out correctly
     */
    @Test
    void test1() {
        // print the main menu and store the output printed to System.out
        test = new TextUITester("");
        scan = new Scanner(System.in);
        backend = new TicketBackendFD();
        loader = new TicketLoaderFD();
        frontend = new TicketFrontend(scan, backend, loader);
        frontend.displayMainMenu();
        output = test.checkOutput();

        // check contents of the main menu
        assertTrue(output.contains("You are in the Main Menu:"));
        assertTrue(output.contains("1) List All Locations"));
        assertTrue(output.contains("2) List All Tickets"));
        assertTrue(output.contains("3) Chose Departure and Arrival Location"));
        assertTrue(output.contains("4) Exit Application"));
    }

    /**
     * Check if the functionality of the command loop and if it exits correctly
     */
    @Test
    void test2() {
        // run the command loop and store the System.out output
        test = new TextUITester("7\nHe71823b\n4\n");
        scan = new Scanner(System.in);
        backend = new TicketBackendFD();
        loader = new TicketLoaderFD();
        frontend = new TicketFrontend(scan, backend, loader);
        frontend.runCommandLoop();
        output = test.checkOutput();

        // check the beginning message of command loop
        assertEquals(output.substring(0, 40), "Welcome to the Plain Ticket Application!");
        assertTrue(output.contains("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x"));

        // check the output if enter a string instead of a number between 1 and 4
        assertTrue(output.contains("Invalid input!"));
        assertTrue(output.contains("Invalid input! Please select a number between 1 and 4"));

        // check the exit message
        assertTrue(output.contains("Goodbye!"));
    }


    /**
     * This tester checks the output of the List All Locations option in the main menu.
     * Hence, tests the functionality of the printAllLocations() and isAdded() methods
     */
    @Test
    void test3() {
        // run the command loop and store the System.out output
        test = new TextUITester("1\n4\n");
        scan = new Scanner(System.in);
        backend = new TicketBackendFD();
        loader = new TicketLoaderFD();
        frontend = new TicketFrontend(scan, backend, loader);
        frontend.runCommandLoop();
        output = test.checkOutput();

        // checking if the output is as expected with the given input
        assertTrue(output.contains("1) Madison\n2) Boston\n3) New York\n4) Los Angeles\n5) Atlanta"));
        assertTrue(output.contains("Goodbye!"));
    }

    /**
     * This tester checks the output of the List All Tickets option in the main menu.
     * Hence, tests the functionality of the printAllTickets() and displayTicket() methods
     */
    @Test
    void test4() {
        // run the command loop and store the System.out output
        test = new TextUITester("2\n4\n");
        scan = new Scanner(System.in);
        backend = new TicketBackendFD();
        loader = new TicketLoaderFD();
        frontend = new TicketFrontend(scan, backend, loader);
        frontend.runCommandLoop();
        output = test.checkOutput();

        // checking if the output is as expected with the given input
        assertTrue(output.contains("1) Ticket from Madison to Boston is 1000.0 dollars\n" +
                "2) Ticket from New York to Los Angeles is 2000.0 dollars\n" +
                "3) Ticket from Atlanta to New York is 300.0 dollars\n" +
                "4) Ticket from Atlanta to Los Angeles is 2000.0 dollars"));
        assertTrue(output.contains("Goodbye!"));
    }

    /**
     * This tester checks the output of the Chose Departure and Arrival Location in the main menu.
     * Hence, tests the functionality of the printCheapestPath() and printLeastTransfer() methods
     */
    @Test
    void test5() {
        // run the command loop and store the System.out output
        test = new TextUITester("3\nMadison\nBoston\n4\n");
        scan = new Scanner(System.in);
        backend = new TicketBackendFD();
        loader = new TicketLoaderFD();
        frontend = new TicketFrontend(scan, backend, loader);
        frontend.runCommandLoop();
        output = test.checkOutput();

        // Checking if the output is as expected for the given valid input
        assertTrue(output.contains("Following is the list of tickets comprising of the cheapest path from Madison to Boston:"));
        assertTrue(output.contains("Ticket from Madison to Chicago\n" +
                "Ticket from Chicago to New York\n" +
                "Ticket from New York to Boston"));
        assertTrue(output.contains("\nFollowing is the list of tickets comprising of the path with least transfers"));
        assertTrue(output.contains("Ticket from Madison to Chicago\n" +
                "Ticket from Chicago to Seattle\n" +
                "Ticket from Seattle to Boston"));
        assertTrue(output.contains("Goodbye!"));

        // run the command loop and store the System.out output
        test = new TextUITester("3\n   \n\n4\n");
        scan = new Scanner(System.in);
        backend = new TicketBackendFD();
        loader = new TicketLoaderFD();
        frontend = new TicketFrontend(scan, backend, loader);
        frontend.runCommandLoop();
        output = test.checkOutput();
        // Checking if the output is as expected when accounting for invalid input cases
        assertTrue(output.contains("Invalid Input!"));
        assertTrue(!output.contains("\nFollowing is the list of tickets comprising of the path with least transfers"));
    }
}



