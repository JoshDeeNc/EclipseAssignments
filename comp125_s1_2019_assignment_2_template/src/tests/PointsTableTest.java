package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stage1.Match;
import stage2.substage2.Season;
import stage2.substage3.PointsTable;

class PointsTableTest {
	PointsTable blank, table12, table15, table17;

	@BeforeEach
	public void run() throws FileNotFoundException {
		blank = new PointsTable(new Season("0102blank.csv")); //blank season
		
		/**
		 * IMPORTANT: Correctness of PointsTable constructor depends upon the 
		 * correct implementation of addMatchEntry. Make sure testAddMatchEntry
		 * passes and only then will these objects be constructed correctly.
		 */
		table12 = new PointsTable(new Season("1213.csv"));
		table15 = new PointsTable(new Season("1516.csv"));
		table17 = new PointsTable(new Season("1718.csv"));
	}
	
	@Test
	void testAddMatchEntry() {
		blank.addMatchEntry(new Match("Chelsea", "Liverpool", 2, 3));
		
		assertEquals(0, blank.getTeamIndex("Liverpool")); 
		assertEquals(1, blank.getTeamIndex("Chelsea"));  
		assertEquals(2, blank.size());
		
		blank.addMatchEntry(new Match("Liverpool", "Man City", 1, 1));
		
		assertEquals(0, blank.getTeamIndex("Liverpool")); //liverpool has 4 points
		assertEquals(1, blank.getTeamIndex("Man City")); //man city has 1 point
		assertEquals(2, blank.getTeamIndex("Chelsea"));  //chelsea have 0 points
		assertEquals(3, blank.size());
		
		table15.addMatchEntry(new Match("Tottenham", "Arsenal", 2, 0));
		//in 2015, arsenal pipped tottenham by a point
		//so if there was another match between the two that tottenham
		//won, they'd be 2nd
		assertEquals(1, table15.getTeamIndex("Tottenham")); //up
		assertEquals(2, table15.getTeamIndex("Arsenal")); //down 
		assertEquals(20, table15.size());

		table15.addMatchEntry(new Match("Leeds", "Man City", 2, 2));
		assertEquals(21, table15.size()); //Leeds now added to table
	}

	@Test
	void testTeamExists() {
		assertTrue(table12.teamExists("Arsenal"));
		assertTrue(table15.teamExists("Arsenal"));
		assertTrue(table17.teamExists("Arsenal"));

		assertTrue(table12.teamExists("Chelsea"));
		assertTrue(table15.teamExists("Chelsea"));
		assertTrue(table17.teamExists("Chelsea"));

		assertFalse(table12.teamExists("Leeds")); //what happened :( ?!
		assertFalse(table15.teamExists("Leeds"));
		assertFalse(table17.teamExists("Leeds"));

		assertFalse(table12.teamExists("Leicester"));
		assertTrue(table12.teamExists("QPR"));
		assertTrue(table15.teamExists("Leicester")); //OH YEAH!!!
		assertFalse(table17.teamExists("QPR"));
		assertTrue(table17.teamExists("Leicester"));
	}

	@Test
	void testGetTeamIndex() {
		assertEquals(0, table15.getTeamIndex("Leicester"));
		assertEquals(1, table15.getTeamIndex("Arsenal"));
		assertEquals(10, table15.getTeamIndex("Everton"));
		assertEquals(18, table15.getTeamIndex("Norwich"));
		assertEquals(19, table15.getTeamIndex("Aston Villa"));

		assertEquals(0, table17.getTeamIndex("Man City"));
		assertEquals(1, table17.getTeamIndex("Man United"));
		assertEquals(10, table17.getTeamIndex("Crystal Palace"));
		assertEquals(18, table17.getTeamIndex("Stoke"));
		assertEquals(19, table17.getTeamIndex("West Brom"));
	}

	@Test
	void testGetTeamByStanding() {
		assertNull(table15.getTeamByStanding(-1));
		assertNull(table15.getTeamByStanding(0));
		assertNull(table15.getTeamByStanding(21));

		assertNotNull(table15.getTeamByStanding(1));
		assertEquals("Leicester", table15.getTeamByStanding(1));

		assertNotNull(table15.getTeamByStanding(20));
		assertEquals("Aston Villa", table15.getTeamByStanding(20));

		assertNotNull(table15.getTeamByStanding(10));
		assertEquals("Chelsea", table15.getTeamByStanding(10));
	}

	@Test
	void testGetStanding() {
		assertNull(table15.getStanding("Leeds")); //not there

		assertNotNull(table15.getStanding("Arsenal"));
		assertEquals((Integer)2, table15.getStanding("Arsenal"));

		assertNotNull(table15.getStanding("Tottenham"));
		assertEquals((Integer)3, table15.getStanding("Tottenham"));

		assertNotNull(table15.getStanding("Arsenal"));
		assertEquals((Integer)17, table15.getStanding("Sunderland"));
	}


	@Test
	void testGetRelegatedTeams() {
		ArrayList<String> relegated15 = table15.getRelegatedTeams();
		assertNotNull(relegated15);
		assertEquals("[Newcastle, Norwich, Aston Villa]", relegated15.toString());

		ArrayList<String> relegated17 = table17.getRelegatedTeams();
		assertNotNull(relegated17);
		assertEquals("[Swansea, Stoke, West Brom]", relegated17.toString());		
	}
}
