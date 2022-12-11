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
}
