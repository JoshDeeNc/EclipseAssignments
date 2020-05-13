package graphs.freshcode;

import java.util.Iterator;
import java.util.Map;

import graphs.freshcode.PseudoRand;
import graphs.freshcode.VertexIDList;

public class Vertex {
	/*
	 * class Vertex represents a vertex in a graph, containing the
	 * vertex label and other fields
	 * 
	 * contains a list of the vertex's neighbours, giving an adjacency
	 * list representation for the graph
	 */

	Integer id; // Vertex label
	VertexIDList adjs; // List of neighbours
	Boolean marked;  // Used to indicate previously visited
	Integer num;  // Used by Drozdek to indicate order of visit in traversal
	Integer seed;  // Seed for generating pseudo-random numbers
	PseudoRand p;  // Class instance for generating pseudo-random numbers
	Integer visits; // keeps track of visits to vertex
	Double weight; // the weight represents a normalised score for the vertex
	
	public Vertex(Integer n, Integer q) {
		// ADDED+: includes pseudo-random init
		// Constructor
		id = n;
		marked = false;
		seed = q;
		p = new PseudoRand(q);
		adjs = new VertexIDList();
		num = 0;
		visits = 0;
		weight = 0.0;
	}
	
	public void setMarked() {
		marked = true;
	}

	public void setUnmarked() {
		marked = false;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public void addAdj (Integer n) {
		// Adds a neighbour n to the current vertex 
		adjs.push(n);
	}
	
	public void addAdj (Integer n, Double w) {
		// Adds a neighbour n to the current vertex 
		adjs.push(n,w);
	}
	
	public VertexIDList getAdjs() {
		return adjs;
	}

	public Integer getID() {
		return id;
	}
	
	public Integer getNum() {
		return num;
	}
	

	public void setNum(Integer n) {
		num = n;
	}
	
	public Double getWeight() {
		// TODO
		return this.weight;
	}
	
	public Integer getVisits() {
		// TODO
		return this.visits;
	}

	public Double getPseudoRandomDouble() {
		// PRE: -
		// POST: returns a pseudorandom double value
		
		// TODO
		return this.p.genPseudoRandDouble();
	}

	public Integer getPseudoRandomLink() {
		// ADDED
		// PRE: vertex has non-empty adjacency list
		// POST: returns a vertex ID randomly selected from adjacent vertices,
		//         according to distribution of edge weights
		// adapted from Sedgewick: http://introcs.cs.princeton.edu/java/16pagerank/RandomSurfer.java.html
		
		// TODO
		Vertex vert = this;
		Integer chosenVertexID = 0;
		Double closest = 0.0;
		double seed = vert.getPseudoRandomDouble()*this.getAdjs().getWeightTotal();
		for(Map.Entry<Integer, Double> entry : this.getAdjs().entrySet()) {
			if(seed <= entry.getValue()+closest) {
				chosenVertexID = entry.getKey();
				break;
			}
			closest+=entry.getValue();
		}
		return chosenVertexID;
	}
	
	public void print() {
		System.out.print("Node " + id + ":");
		adjs.print();
	}
	
	public static void main(String[] args) {
		Vertex v = new Vertex(2, 1);
		v.addAdj(3);
		v.addAdj(6);
		v.print();

		System.out.println("testing iterator ...");
		VertexIDList vAdjs = v.getAdjs();
		Iterator<Integer> it = vAdjs.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
