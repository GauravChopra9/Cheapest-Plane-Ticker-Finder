// --== CS400 File Header Information ==--
// Name: Ishan Bhutoria
// Email: ibhutoria@wisc.edu
// Team: CT
// TA: Ilay Raz
// Lecturer: Gary Dahl
// Notes to Grader: none

import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

class AlgorithmEngineerTests {

	private FlightTicketGraph<String, Integer> cities;

	/**
	 * Instantiate graph.
	 */
	@BeforeEach
	public void createGraph() {
		cities = new FlightTicketGraph<>();
		// inserting cities into the a graph
		cities.insertVertex("Madison");
		cities.insertVertex("Chicago");
		cities.insertVertex("Washington");
		cities.insertVertex("Boston");
		cities.insertVertex("Miami");
		cities.insertVertex("Austin");
		// inserting edges between the cities
		cities.insertEdge("Madison", "Chicago", 6);
		cities.insertEdge("Madison", "Washington", 2);
		cities.insertEdge("Madison", "Boston", 5);
		cities.insertEdge("Chicago", "Miami", 1);
		cities.insertEdge("Chicago", "Washington", 2);
		cities.insertEdge("Washington", "Chicago", 3);
		cities.insertEdge("Washington", "Austin", 1);
		cities.insertEdge("Boston", "Miami", 3);
		cities.insertEdge("Miami", "Madison", 4);
		cities.insertEdge("Austin", "Madison", 9);
		cities.insertEdge("Austin", "Boston", 1);
		cities.insertEdge("Washington", "Boston", 2);
	}

	/**
	 * This test checks the implementation of four methods for the flights from
	 * Madison to Boston. It checks the cheapestPath(), leastTransferPath(),
	 * getCheapestPathCost(), and the getLeastTransferPathCost() methods.
	 */
	@Test
	public void testGetCheapestPathAndTransferPathAndPathCostMadisonToBoston() {
		assertTrue(cities.cheapestPath("Madison", "Boston").toString()
				.equals("[(Madison --> Washington), (Washington --> Boston)]"));
		assertTrue(cities.leastTransferPath("Madison", "Boston").toString().equals("[(Madison --> Boston)]"));
		assertTrue(cities.getCheapestPathCost("Madison", "Boston") == 4.0);
		assertTrue(cities.getLeastTransferPathCost("Madison", "Boston") == 5.0);
	}

	/**
	 * This test checks the implementation of four methods for the flights from
	 * Madison to Chicago. It checks the cheapestPath(), leastTransferPath(),
	 * getCheapestPathCost(), and the getLeastTransferPathCost() methods.
	 */
	@Test
	public void testGetCheapestAndLeastTransferPathAndPathCostMadisonToChicago() {
		assertTrue(cities.leastTransferPath("Madison", "Chicago").toString().equals("[(Madison --> Chicago)]"));
		assertTrue(cities.cheapestPath("Madison", "Chicago").toString()
				.equals("[(Madison --> Washington), (Washington --> Chicago)]"));
		assertTrue(cities.getCheapestPathCost("Madison", "Chicago") == 5.0);
		assertTrue(cities.getLeastTransferPathCost("Madison", "Chicago") == 6.0);
	}

	/**
	 * This test checks the implementation of four methods for the flights from
	 * Madison to Miami. It checks the cheapestPath(), leastTransferPath(),
	 * getCheapestPathCost(), and the getLeastTransferPathCost() methods.
	 */
	@Test
	public void testGetCheapestAndLeastTransferPathAndPathCostMadisonToMiami() {
		assertTrue(cities.cheapestPath("Madison", "Miami").toString()
				.equals("[(Madison --> Washington), (Washington --> Chicago), (Chicago --> Miami)]"));
		assertTrue(cities.leastTransferPath("Madison", "Miami").toString()
				.equals("[(Madison --> Boston), (Boston --> Miami)]"));
		assertTrue(cities.getCheapestPathCost("Madison", "Miami") == 6.0);
		assertTrue(cities.getLeastTransferPathCost("Madison", "Miami") == 8.0);
	}

	/**
	 * This test checks the implementation of four methods for the flights from
	 * Austin to Madison. It checks the cheapestPath(), leastTransferPath(),
	 * getCheapestPathCost(), and the getLeastTransferPathCost() methods.
	 */
	@Test
	public void testGetCheapestAndLeastTransferPathAndPathCostAustinToMadison() {
		assertTrue(cities.cheapestPath("Austin", "Madison").toString()
				.equals("[(Austin --> Boston), (Boston --> Miami), (Miami --> Madison)]"));
		assertTrue(cities.leastTransferPath("Austin", "Madison").toString().equals("[(Austin --> Madison)]"));
		assertTrue(cities.getCheapestPathCost("Austin", "Madison") == 8.0);
		assertTrue(cities.getLeastTransferPathCost("Austin", "Madison") == 9.0);
	}

	/**
	 * This test checks the implementation of four methods with invalid input (i.e,
	 * it takes vertices which are not present in our graph). It checks the
	 * cheapestPath(), leastTransferPath(), getCheapestPathCost(), and the
	 * getLeastTransferPathCost() methods and checks if they throw a
	 * NoSuchElementException. It returns false if they don't throw an error or if
	 * they return any other value.
	 */
	@Test
	public void testCheapestPathAndLeastTransferPathWithInvalidInput() {
		// Testing our cheapestPath() and leastTransferPath() methods with a city not
		// present in our cities graph
		boolean checkForCheapestPath = false;
		try {
			cities.cheapestPath("Chicago", "New York");
		} catch (NoSuchElementException excpt) {
			checkForCheapestPath = true;
		} catch (Exception excpt) {
			checkForCheapestPath = false;
		}

		assertTrue(checkForCheapestPath);

		boolean checkForLeastTransferPath = false;
		try {
			cities.leastTransferPath("Milwaukee", "Los Angeles");
		} catch (NoSuchElementException excpt) {
			checkForLeastTransferPath = true;
		} catch (Exception excpt) {
			checkForLeastTransferPath = false;
		}

		assertTrue(checkForLeastTransferPath);

	}

	@Test
	public void integrationTest1() {
		FlightTicketGraph<String, Integer> integrationTest1Cities = new FlightTicketGraph<>();
		integrationTest1Cities.insertVertex("New York");
		integrationTest1Cities.insertVertex("Los Angeles");
		integrationTest1Cities.insertVertex("San Francisco");
		integrationTest1Cities.insertVertex("Seattle");
		integrationTest1Cities.insertVertex("San Diego");
		integrationTest1Cities.insertEdge("New York", "San Diego", 1);
		integrationTest1Cities.insertEdge("Los Angeles", "Seattle", 3);
		integrationTest1Cities.insertEdge("San Francisco", "Seattle", 1);
		integrationTest1Cities.insertEdge("San Diego", "San Francisco", 2);
		integrationTest1Cities.insertEdge("New York", "San Francisco", 5);
		integrationTest1Cities.insertEdge("Seattle", "San Diego", 5);
		integrationTest1Cities.insertEdge("Seattle", "New York", 3);
		integrationTest1Cities.insertEdge("Los Angeles", "New York", 2);
		integrationTest1Cities.insertEdge("Los Angeles", "San Francisco", 1);

		// Testing the cheapestPath() and leastTransferPath() from New York to San Francisco
		assertTrue(integrationTest1Cities.cheapestPath("New York", "San Francisco").toString()
				.equals("[(New York --> San Diego), (San Diego --> San Francisco)]"));
		assertTrue(integrationTest1Cities.getCheapestPathCost("New York", "San Francisco") == 3.0);
		assertTrue(integrationTest1Cities.leastTransferPath("New York", "San Francisco").toString()
				.equals("[(New York --> San Francisco)]"));
		assertTrue(integrationTest1Cities.getLeastTransferPathCost("New York", "San Francisco") == 5.0);

		// Checking with a vertex that does not exist
		boolean checkForInvalid = false;
		try {
			integrationTest1Cities.cheapestPath("New York", "Chicago");
		} catch (NoSuchElementException excpt) {
			checkForInvalid = true;
		} catch (Exception expct) {
			checkForInvalid = false;
		}

		assertTrue(checkForInvalid);

	}

	@Test
	public void integrationTest2() {
		FlightTicketGraph<String, Integer> integrationTest2Cities = new FlightTicketGraph<>();
		integrationTest2Cities.insertVertex("New York");
		integrationTest2Cities.insertVertex("Los Angeles");
		integrationTest2Cities.insertVertex("San Francisco");
		integrationTest2Cities.insertVertex("Seattle");
		integrationTest2Cities.insertVertex("San Diego");
		integrationTest2Cities.insertEdge("New York", "San Diego", 1);
		integrationTest2Cities.insertEdge("Los Angeles", "Seattle", 3);
		integrationTest2Cities.insertEdge("San Francisco", "Seattle", 1);
		integrationTest2Cities.insertEdge("San Diego", "San Francisco", 2);
		integrationTest2Cities.insertEdge("New York", "San Francisco", 5);
		integrationTest2Cities.insertEdge("Seattle", "San Diego", 5);
		integrationTest2Cities.insertEdge("Seattle", "New York", 3);
		integrationTest2Cities.insertEdge("Los Angeles", "New York", 2);
		integrationTest2Cities.insertEdge("Los Angeles", "San Francisco", 1);

		// Testing the cheapestPath() and leastTransferPath() from Los Angeles to Seattle
		assertTrue(integrationTest2Cities.cheapestPath("Los Angeles", "Seattle").toString()
				.equals("[(Los Angeles --> San Francisco), (San Francisco --> Seattle)]"));
		assertTrue(integrationTest2Cities.getCheapestPathCost("Los Angeles", "Seattle") == 2.0);
		assertTrue(integrationTest2Cities.leastTransferPath("Los Angeles", "Seattle").toString()
				.equals("[(Los Angeles --> Seattle)]"));
		assertTrue(integrationTest2Cities.getLeastTransferPathCost("Los Angeles", "Seattle") == 3.0);

		// Testing the cheapestPath() and leastTransferPath(0 from New York to Seattle
		assertTrue(integrationTest2Cities.cheapestPath("New York", "Seattle").toString()
				.equals("[(New York --> San Diego), (San Diego --> San Francisco), (San Francisco --> Seattle)]"));
		assertTrue(integrationTest2Cities.getCheapestPathCost("New York", "Seattle") == 4.0);
		assertTrue(integrationTest2Cities.leastTransferPath("New York", "Seattle").toString()
				.equals("[(New York --> San Francisco), (San Francisco --> Seattle)]"));
		assertTrue(integrationTest2Cities.getLeastTransferPathCost("New York", "Seattle") == 6.0);
	}

	@Test
	public void codeReviewOfFrontendDeveloper1() {
		Scanner scan = new Scanner(System.in);
		TextUITester test = new TextUITester("");
		TicketBackendFD backend = new TicketBackendFD();
		TicketLoader loader = new TicketLoader();
		TicketFrontend frontend = new TicketFrontend(scan, backend, loader);
		frontend.displayMainMenu();
		String output = test.checkOutput();

		assertTrue(output.contains("You are in the Main Menu:"));
		assertTrue(output.contains("1) List All Locations"));
		assertTrue(output.contains("2) List All Tickets"));
        	assertTrue(output.contains("3) Chose Departure and Arrival Location"));
        	assertTrue(output.contains("4) Exit Application"));	
	}

	@Test
	public void codeReviewOfFrontendDeveloper2() {
		Scanner scan = new Scanner(System.in);
                TextUITester test = new TextUITester("");
                TicketBackendFD backend = new TicketBackendFD();
                TicketLoader loader = new TicketLoader();
                TicketFrontend frontend = new TicketFrontend(scan, backend, loader);
                frontend.displayMainMenu();
                String output = test.checkOutput();

		assertTrue(output.contains("1) Madison\n2) Boston\n3) New York\n4) Los Angeles\n5) Atlanta"));
        	assertTrue(output.contains("Goodbye!"));
	}
}
