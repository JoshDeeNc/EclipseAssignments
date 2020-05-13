package pageRankOnTrees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.junit.Test;

// This sample of tests assumes the tree given in myTree.txt.
public class WorldWideWebTests {
	
	@Test 
	public void testCountnotEmpty() { // Tests for calculating the number of nodes in the tree.
		WorldWideWeb myTree= new WorldWideWeb();
		myTree.setWorldWideWeb("myTree.txt");

		int count= myTree.wwwSize();	  
		assertEquals(10, count);
	}

	@Test 
	public void testCountEmpty() { // An empty tree has no nodes.
		WorldWideWeb myTree= new WorldWideWeb();		  
		int count= myTree.wwwSize();	  
		assertEquals(0, count);
	}

	@Test 
	public void testRemoveNode() { // Tests for the removal of a node.
		WorldWideWeb myTree= new WorldWideWeb();
		myTree.setWorldWideWeb("myTree.txt");	
		assertEquals(3, myTree.root.links.size());
		myTree.reduceTree("b"); // 
		assertEquals(2, myTree.root.links.size());
	}

	@Test 
	public void testRemoveNodeNodeNotThere() { // Tests for the removal of a node that isn't in the tree.
		WorldWideWeb myTree= new WorldWideWeb();
		myTree.setWorldWideWeb("myTree.txt");	
		assertEquals(3, myTree.root.links.size());
		myTree.reduceTree("z"); // 
		assertEquals(3, myTree.root.links.size());
	}

	@Test 
	public void testCalculateRanks() { // Calculates the page importances.
		WorldWideWeb myTree= new WorldWideWeb();
		myTree.setWorldWideWeb("myTree.txt");
		myTree.calculateTreeImportances();
		Iterator<WebPage> titles;
		Set<WebPage> keys= myTree.pages.importance.keySet();
		titles= keys.iterator();
		Double maximum= 0.0;
		Double temp= 0.0;
		WebPage popular= null;
		WebPage tempW;
		for(;titles.hasNext();) {
			tempW= titles.next();
			temp= myTree.pages.importanceLookUp(tempW);
			if (temp > maximum) {
				maximum= temp;
				popular= tempW;
			}
		}
		assertEquals(2.3, maximum, 0.1); // Comparison of doubles within a margin of error 0.1.
		assertTrue(popular.title.equals("j"));
	}

	@Test 
	public void testTopTen() { // Calculates the page importances and looks at the maximum importance.
		WorldWideWeb myTree= new WorldWideWeb();
		myTree.setWorldWideWeb("myTree.txt");
		myTree.calculateTreeImportances();
		Vector<WebPage> topTen= myTree.topTenRanked();
		for (int i= 0; i<topTen.size(); i++) assertTrue(topTen.elementAt(i).title.equals("j"));
	}

	@Test
	public void testUpdateImportances() { // Tests updateImportances.
		WorldWideWeb myTree= new WorldWideWeb();
		myTree.setWorldWideWeb("myTree.txt");
		WebPage p= myTree.root.links.elementAt(1);
		myTree.updateImportances(p, 2.0);

		Iterator<WebPage> titles;
		Set<WebPage> keys= myTree.pages.importance.keySet();
		titles= keys.iterator();
		Double maximum= 0.0;
		Double temp= 0.0;
		WebPage popular= null;
		WebPage tempW;
		for (;titles.hasNext();) {
			tempW= titles.next();
			temp= myTree.pages.importanceLookUp(tempW);
			if (temp>maximum) {
			   maximum= temp;
			   popular= tempW;
			}
		}
		assertEquals(3.3, maximum, 0.1); // Comparison of doubles within a margin of error 0.1.
		assertTrue(popular.title.equals("j"));
	}
	
	@Test
	public void testChangeReference () { // Tests that the reference has been updated correctly.
		WorldWideWeb myTree= new WorldWideWeb();
		myTree.setWorldWideWeb("myTree.txt");
		WebPage q= myTree.root.links.elementAt(1);
		WebPage s= myTree.root.links.elementAt(0);		
		myTree.changeReference(myTree.root, q, s);
		assertEquals(2, myTree.root.links.size());	 // root loses a link
		assertEquals(3, s.links.size()); // s gains a link
	}

}
