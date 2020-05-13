package graphs.freshcode;

import java.io.IOException;
import java.util.*;

/*
 * CHANGELOG
 * 
 * 08/05: 
 * - removed incorrect import statements from Vertex.java
 * - in GraphApplic.java main(), added example to show access to edge weight
 * 
 */

public class GraphApplic extends Graph {

	public GraphApplic(long s) {
		super(s);
	}

	// PASS LEVEL
	
	public Integer surfNoJump(Integer v, Integer n) {
		// PRE: v is vertex to start surf; n >= 0
		// POST: surfs the graph randomly for n moves, 
		//       choosing adjacent vertex according to distribution of edge weights
		//       modifies # visits in vertices
		//       returns last visited vertex at end of surfing

		// TODO
		for(int i = 0; i < n; i++) {
			this.getVertex(v).visits++;
			v = this.getVertex(v).getPseudoRandomLink();
		}
		return v;
	}
	
	public Integer surfWithJump(Integer v, Integer n, Double jumpThreshold) {
		// PRE: v is vertex to start surf; n >= 0; 0 <= jumpThreshold <= 1.0
		// POST: surfs the graph randomly for n moves, 
		//       choosing adjacent vertex according to distribution of edge weights if random number is below jumpThreshold, 
		//       choosing any vertex uniformly randomly otherwise;
		//       modifies # visits in vertices
		//       returns last visited vertex at end of surfing

		// TODO
		PseudoRand rand = new PseudoRand(n);
		for(int i = 0; i < n; i++) {
			this.getVertex(v).visits++;
			double r = rand.genPseudoRandDouble();
			if(r < jumpThreshold) {
				v = this.getVertex(v).getPseudoRandomLink();
			}
			else {
				
			}
		}
		return v;
	}
	
	public ArrayList<Integer> topN(Integer N) {
		// PRE: none
		// POST: returns N vertices with highest number of visits, in order;
		//       returns all vertices if <N in the graph;
		//       returns vertices ranked 1,..,N,N+1,..,N+k if these k have the
		//         same number of visits as vertex N
		
		// TODO
		
		return new ArrayList<Integer>();
	}
	
	// CREDIT LEVEL
	
	public void setVertexWeights () {
		// PRE: -
		// POST: set weights of each vertex v to be v.visits divided by
		//         the total of visits for all vertices

		// TODO
	}
	
	public void convergenceWeights(Integer dp, Double jumpThreshold) {
		// PRE: dp >= 0 representing number of decimal places
		//		0 <= jumpThreshold <= 1.0
		// POST: web is surfed until all weights are constant to dp decimal places,
		//         for at least one iteration
		
		// TODO
	}

	// DISTINCTION LEVEL

	public Integer surfWithJumpUntilHit(Integer v, Integer n, Double jumpThreshold) {
		// PRE: v is vertex to start surf; n >= 0; 0 <= jumpThreshold <= 1.0
		// POST: surfs the graph randomly until visit v for second time (maximum n moves), 
		//       choosing adjacent vertex according to distribution of edge weights if random number is below jumpThreshold, 
		//       choosing any vertex uniformly randomly otherwise;
		//       modifies # visits in vertices
		//       returns number of vertices visited

		// TODO
		
		return 0;
	}

	public Double averageHittingTime(Integer v, Integer n, Integer maxHits, Double jumpThreshold) {
		// PRE: n >= 1 is number of iterations for averaging; maxHits >= 0; 0 <= jumpThreshold <= 1.0
		// POST: returns average number of vertices visited until hitting, across n iterations,
		//         (not including those which stopped because they reached maxHits)
		//       returns 0 if all iterations reached maxVisits
		
		// TODO
		
		return 0.0;
	}

	public Integer surfWithJumpUntilCover(Integer v, Integer n, Double jumpThreshold) {
		// PRE: v is vertex to start surf; n >= 0; 0 <= jumpThreshold <= 1.0
		// POST: surfs the graph randomly until all vertices visited (with maximum n vertices visited), 
		//       choosing adjacent vertex according to distribution of edge weights if random number is below jumpThreshold, 
		//       choosing any vertex uniformly randomly otherwise;
		//       modifies # visits in vertices
		//       returns number of vertices visited
		
		// TODO
		
		return 0;
	}
	
	public Double averageCoverTime(Integer n, Integer maxVisits, Double jumpThreshold) {
		// PRE: n >= 1 is number of iterations for averaging; maxVisits >= 0; 0 <= jumpThreshold <= 1.0
		// POST: returns average number of vertices visited until cover, across n iterations,
		//         (not including those which stopped because they reached maxVisits)
		//         randomly selecting start vertex each iteration
		//       returns 0 if all iterations reached maxVisits
		
		// TODO
		
		return 0.0;
	}

	public Integer boostVertex(Integer v, Integer dp) {
		// PRE: v is a vertex in the graph
		// POST: returns a vertex v2 (!= v) such that when edge (v,v2,1.0) is added to the graph,
		//         the weight of v is larger than when edge (v,v3,1.0) is added to the graph
		//         (for any v3), when the graph is surfed to convergence
		//       if there is no such vertex v2 (i.e. v is already connected to all other vertices),
		//         return v
		//       edges are only added if they do not already exist in the graph
		
		// TODO
		
		return 0;
	}
	
	
	public static void main(String[] args) {
		GraphApplic g = new GraphApplic(1);

		try {
			g.readWeightedFromFileWSeedAndSetDirected("/Users/josh/eclipse-workspace/PageRankGraphAssignment2020/assignment-sample-graphs/tiny-weight.txt");
			  // change this path to wherever you store your data files
			g.print();
			System.out.print("weight of edge (1,2): ");
			System.out.println(g.getVertex(1).getAdjs().get(2));			
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}

	}
}
