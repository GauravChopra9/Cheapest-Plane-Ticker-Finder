// --== CS400 File Header Information ==--
// Name: Yanbin Chen
// Email: ychen877@wisc.edu
// Team: CT
// TA: Ilay Raz
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Placeholder class that implements IGameBackend
 */
public class FlightTicketBD<NodeType, EdgeType extends Number> implements FlightTicketADT<NodeType, EdgeType> {

	/**
	 * Vertex objects group a data field with an adjacency list of weighted directed
	 * edges that lead away from them.
	 */
	protected class Vertex {
		public String data; // vertex label or application specific data
		public LinkedList<Edge> edgesLeaving;

		public Vertex(String data) {
			this.data = data;
			this.edgesLeaving = new LinkedList<>();
		}
	}

	/**
	 * Edge objects are stored within their source vertex, and group together their
	 * target destination vertex, along with an integer weight.
	 */
	protected class Edge implements ITicket{
		public Vertex departure;
		public Vertex destination;
		public Double price;

		public Edge(Vertex departure, Vertex destination, Double price) {
			this.departure = departure;
			this.destination = destination;
			this.price = price;
		}

	    /**
	     * Returns the price of the ticket.
	     * @return price of the ticket
	     */
		@Override
		public Double getPrice() {
			return price.doubleValue();
		}

	    /**
	     * Returns a string that contains the destination of the ticket
	     * as a single string
	     * @return destination as single string
	     */
		@Override
		public String getDestination() {	
			return destination.data;
		}

	    /**
	     * Returns  the departure name of this ticket
	     * @return departure name of plane ticket
	     */
		@Override
		public String getDeparture() {
			return departure.data;
		}
		
		public String toString() {
			return departure.data + " -> " + destination.data;
		}
	}

	protected Hashtable<String, Vertex> vertices; // holds graph vertices, key=data

	public FlightTicketBD() {
		vertices = new Hashtable<>();
	}

	
	/**
     * Insert a new vertex into the graph.
     * 
     * @param data the data item stored in the new vertex
     * @return true if the data can be inserted as a new vertex, false if it is already in the graph
     * @throws NullPointerException if data is null
     */
    public boolean insertVertex(NodeType data) {
		vertices.put("A", new Vertex("A"));
		vertices.put("B", new Vertex("B"));
		vertices.put("C", new Vertex("C"));
		vertices.put("D", new Vertex("D"));
		vertices.put("E", new Vertex("E"));
		vertices.put("F", new Vertex("F"));
		vertices.put("G", new Vertex("G"));
    	return true;
    }
    
    /**
     * Remove a vertex from the graph.
     * Also removes all edges adjacent to the vertex from the graph (all edges that have the vertex as a source or a destination vertex).
     * 
     * @param data the data item stored in the vertex to remove
     * @return true if a vertex with *data* has been removed, false if it was not in the graph
     * @throws NullPointerException if data is null
     */
    public boolean removeVertex(NodeType data) {
    	return true;
    }
    
    /**
     * Insert a new directed edge with a positive edges weight into the graph.
     * 
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @param weight the weight for the edge (has to be a positive Number)
     * @return true if the edge could be inserted or its weight updated, false if the edge with the same weight was already in the graph with the graph
     * @throws IllegalArgumentException if either sourceVertex or targetVertex or both are not in the graph, or weight is < 0
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     */
    public boolean insertEdge(NodeType source, NodeType target, EdgeType weight) {
		if (source == null || target == null)
			throw new NullPointerException("Cannot add edge with null source or target");
		Vertex sourceVertex = this.vertices.get(source);
		sourceVertex.edgesLeaving.add(new Edge(new Vertex("A"), new Vertex("B"), 6.0));
		sourceVertex.edgesLeaving.add(new Edge(new Vertex("A"), new Vertex("D"), 5.0));
		sourceVertex.edgesLeaving.add(new Edge(new Vertex("A"), new Vertex("C"), 2.0));
		sourceVertex.edgesLeaving.add(new Edge(new Vertex("B"), new Vertex("E"), 1.0));
		sourceVertex.edgesLeaving.add(new Edge(new Vertex("B"), new Vertex("C"), 2.0));
		sourceVertex.edgesLeaving.add(new Edge(new Vertex("C"), new Vertex("B"), 3.0));
		sourceVertex.edgesLeaving.add(new Edge(new Vertex("C"), new Vertex("F"), 1.0));
		sourceVertex.edgesLeaving.add(new Edge(new Vertex("D"), new Vertex("E"), 3.0));
		sourceVertex.edgesLeaving.add(new Edge(new Vertex("E"), new Vertex("A"), 4.0));
		sourceVertex.edgesLeaving.add(new Edge(new Vertex("F"), new Vertex("A"), 1.0));
		sourceVertex.edgesLeaving.add(new Edge(new Vertex("F"), new Vertex("D"), 1.0));
    	return true;
    }
    
    /**
     * Remove an edge from the graph.
     * 
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return true if the edge could be removed, false if it was not in the graph
     * @throws IllegalArgumentException if either sourceVertex or targetVertex or both are not in the graph
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     */
    public boolean removeEdge(NodeType source, NodeType target) {
    	return true;
    }
    
    /**
     * Check if the graph contains a vertex with data item *data*.
     * 
     * @param v the data item to check check for
     * @return true if data item is stored in a vertex of the graph, false otherwise
     * @throws NullPointerException if *data* is null
     */
    public boolean containsVertex(NodeType data) {
    	if(data == null) throw new NullPointerException("Cannot contain null data vertex");
    	return true;
    }
    
    /**
     * Check if edge is in the graph.
     * 
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return true if the edge is in the graph, false if it is not in the graph
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     */
    public boolean containsEdge(NodeType source, NodeType target) {
    	return true;
    }
    
    /**
     * Return the weight of an edge.
     * 
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return the weight of the edge (0 or positive integer)
     * @throws IllegalArgumentException if either sourceVertex or targetVertex or both are not in the graph
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     * @throws NoSuchElementException if edge is not in the graph
     */
    public EdgeType getWeight(NodeType source, NodeType target) {
		throw new NoSuchElementException("No directed edge found between these vertices");
    }
    
    /**
     * Returns a list of the shortest paths between startingVertex and destinationVertex.
     * 
     * 
     * @param start the data item in the starting vertex for the path
     * @param end the data item in the destination vertex for the path
     * @return list of the shortest path's nodes
     */
    public List<ITicket> cheapestPath(NodeType start, NodeType end) {
    	List<ITicket> list1 = new ArrayList<ITicket>();
    	list1.add(new Edge(new Vertex("B"), new Vertex("C"), 2.0));
    	list1.add(new Edge(new Vertex("C"), new Vertex("F"), 1.0));

    	List<ITicket> list2 = new ArrayList<ITicket>();
    	list2.add(new Edge(new Vertex("F"), new Vertex("D"), 1.0));
    	list2.add(new Edge(new Vertex("D"), new Vertex("E"), 3.0));
    	
    	List<ITicket> list3 = new ArrayList<ITicket>();
    	list3.add(new Edge(new Vertex("C"), new Vertex("B"), 3.0));
    	list3.add(new Edge(new Vertex("B"), new Vertex("E"), 1.0));
    	
    	if (start.equals("B") && end.equals("F"))
    		return list1;
    	else if (start.equals("F") && end.equals("E")) 
    		return list2;
    	else if (start.equals("C") && end.equals("E")) 
    		return list3;
    	throw new NoSuchElementException();
    }
    
    /**
     * Returns a list of the least edge paths between startingVertex and destinationVertex.
     * 
     * 
     * @param start the data item in the starting vertex for the path
     * @param end the data item in the destination vertex for the path
     * @return list of the least edge path's nodes
     */
    public List<ITicket> leastTransferPath(NodeType start, NodeType end) {
    	List<ITicket> list2 = new ArrayList<ITicket>();
    	list2.add(new Edge(new Vertex("C"), new Vertex("B"), 3.0));
    	list2.add(new Edge(new Vertex("B"), new Vertex("E"), 1.0));
    	if (start.equals("C") && end.equals("E"))
    		return list2;
    	throw new NoSuchElementException();
    }
    
    /**
     * Returns the cost of the path (sum over edge weights) between startingVertex and destinationVertex.
     * Uses Dijkstra's shortest path algorithm to find the shortest path.
     * 
     * @param start the data item in the starting vertex for the path
     * @param end the data item in the destination vertex for the path
     * @return the cost of the shortest path between vertex with data item startingVertex and vertex with data item destinationVertex, including both startingVertex and destinationVertex
     */
    public double getCheapestPathCost(NodeType start, NodeType end) {
    	return 0;
    }
    
    /**
     * Returns the cost of the path (sum over edge weights) between startingVertex and destinationVertex.
     * Uses Dijkstra's shortest path algorithm to find the path with Least.
     * 
     * @param start the data item in the starting vertex for the path
     * @param end the data item in the destination vertex for the path
     * @return the cost of the least edges path between vertex with data item startingVertex and vertex with data item destinationVertex, including both startingVertex and destinationVertex
     */
    public double getLeastTransferPathCost(NodeType start, NodeType end) {
    	return 0;
    }
    
	/**
	 * Return the number of edges in the graph.
	 * 
	 * @return the number of edges in the graph
	 */
	public int getEdgeCount() {
		int edgeCount = 0;
		for (Vertex v : vertices.values())
			edgeCount += v.edgesLeaving.size();
		return edgeCount;
	}

	/**
	 * Return the number of vertices in the graph
	 * 
	 * @return the number of vertices in the graph
	 */
	public int getVertexCount() {
		return vertices.size();
	}

	/**
	 * Check if the graph is empty (does not contain any vertices or edges).
	 * 
	 * @return true if the graph does not contain any vertices or edges, false
	 *         otherwise
	 */
	public boolean isEmpty() {
		return vertices.size() == 0;
	}
}
