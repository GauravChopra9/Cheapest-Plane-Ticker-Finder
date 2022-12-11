import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BackendDeveloperTest {

	protected TicketBackend backend;
	protected FlightTicketADT<String, Double> graph;
	
	@BeforeEach
	void setUp() throws Exception {
		graph = new FlightTicketBD<String, Double>();
		// add vertices and edges to the graph
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        graph.insertVertex("G"); // a node without edge
        graph.insertEdge("A", "B", 6.0);
        graph.insertEdge("A", "D", 5.0);
        graph.insertEdge("A", "C", 2.0);
        graph.insertEdge("B", "E", 1.0);
        graph.insertEdge("B", "C", 2.0);
        graph.insertEdge("C", "B", 3.0);
        graph.insertEdge("C", "F", 1.0);
        graph.insertEdge("D", "E", 3.0);
        graph.insertEdge("E", "A", 4.0);
        graph.insertEdge("F", "A", 1.0);
        graph.insertEdge("F", "D", 1.0);
		backend = new TicketBackend(graph);
	}

	@Test
	void testSearchByDeparture() {
		assertTrue(backend.searchByDeparture("A"));
		assertTrue(backend.searchByDeparture("B"));
		assertTrue(backend.searchByDeparture("C"));
		assertTrue(backend.searchByDeparture("D"));
		assertTrue(backend.searchByDeparture("E"));
		assertTrue(backend.searchByDeparture("F"));
	}

	@Test
	void testSearchByDestination() {
		assertTrue(backend.searchByDestination("A"));
		assertTrue(backend.searchByDestination("B"));
		assertTrue(backend.searchByDestination("C"));
		assertTrue(backend.searchByDestination("D"));
		assertTrue(backend.searchByDestination("E"));
		assertTrue(backend.searchByDestination("F"));
	}

	@Test
	void testPathFound() {
		assertTrue(backend.pathFound("F", "E"));
		assertTrue(backend.pathFound("B", "F"));
		assertTrue(backend.pathFound("C", "E"));
		assertFalse(backend.pathFound("A", "G"));
		assertFalse(backend.pathFound("G", "D"));
	}
	
	@Test
	void testGetCheapestPath() {
		assertTrue(backend.getCheapestPath("B", "F").toString().equals("[B -> C, C -> F]"));
		assertTrue(backend.getCheapestPath("F", "E").toString().equals("[F -> D, D -> E]"));
		assertTrue(backend.getCheapestPath("C", "E").toString().equals("[C -> B, B -> E]"));
	}

	@Test
	void testGetLeastTransferPath() {
		assertTrue(backend.getLeastTransfer("C", "E").toString().equals("[C -> B, B -> E]"));
	}

	
}
