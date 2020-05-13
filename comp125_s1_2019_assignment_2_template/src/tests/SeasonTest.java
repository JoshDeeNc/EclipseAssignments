package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stage1.Match;
import stage2.substage2.Season;

class SeasonTest {
	Season s11, s14, s15, s16;

	@BeforeEach
	public void run() throws FileNotFoundException {
		s11 = new Season("1112.csv");
		s14 = new Season("1415.csv");
		s15 = new Season("1516.csv");
		s16 = new Season("1617.csv");
	}

	@Test
	void testGetMatch() {
		assertNull(s15.getMatch(-1));
		assertNull(s15.getMatch(s15.size()));


		Match m1 = s15.getMatch(0);
		assertNotNull(m1);
		assertEquals("Bournemouth", m1.getHomeTeam());
		assertEquals("Aston Villa", m1.getAwayTeam());
		assertEquals(0, m1.getHomeGoals());
		assertEquals(1, m1.getAwayGoals());


		Match m2 = s15.getMatch(s15.size()-1);
		assertNotNull(m1);
		assertEquals("Man United", m2.getHomeTeam());
		assertEquals("Bournemouth", m2.getAwayTeam());
		assertEquals(3, m2.getHomeGoals());
		assertEquals(1, m2.getAwayGoals());

		Match m3 = s15.getMatch(18);
		assertNotNull(m1);
		assertEquals("Man City", m3.getHomeTeam());
		assertEquals("Chelsea", m3.getAwayTeam());
		assertEquals(3, m3.getHomeGoals());
		assertEquals(0, m3.getAwayGoals());
	}

	@Test
	void testGetWinner() {
		assertEquals("Aston Villa", s15.getWinner(0));
		assertEquals("We're all winners!", s15.getWinner(1)); //chelsea drew against swansea
		assertEquals("Man City", s15.getWinner(18));
		assertEquals("Man United", s15.getWinner(s15.size()-1));
	}

	@Test
	void testGetHomeGameCount() {
		assertEquals(19, s15.getHomeGameCount("Chelsea"));
		assertEquals(19, s15.getHomeGameCount("Man City"));
	}

	@Test
	void testGetHomeWinCount() {
		assertEquals(5, s15.getHomeWinCount("Chelsea"));
		assertEquals(12, s15.getHomeWinCount("Man City"));
	}

	@Test
	void testGetAwayGameCount() {
		assertEquals(19, s15.getAwayGameCount("Chelsea"));
		assertEquals(19, s15.getAwayGameCount("Man City"));
	}

	@Test
	void testGetAwayWinCount() {
		assertEquals(7, s15.getAwayWinCount("Chelsea"));
		assertEquals(7, s15.getAwayWinCount("Man City"));
	}
	
	@Test
	void testGetMatchesDrawn() {
		ArrayList<Match> drawnMatches = s15.getMatchesDrawn();
		assertNotNull(drawnMatches);
		assertEquals(107, drawnMatches.size());
		assertEquals("Chelsea 2 : 2 Swansea", drawnMatches.get(0).toString());
		assertEquals("West Brom 1 : 1 Liverpool", drawnMatches.get(drawnMatches.size()-1).toString());
		assertEquals("[Chelsea 2 : 2 Swansea, Everton 2 : 2 Watford, Newcastle 2 : 2 Southampton, Tottenham 2 : 2 Stoke, Watford 0 : 0 West Brom, Leicester 1 : 1 Tottenham, Man United 0 : 0 Newcastle, Norwich 1 : 1 Stoke, Sunderland 1 : 1 Swansea, Watford 0 : 0 Southampton, Arsenal 0 : 0 Liverpool, Aston Villa 2 : 2 Sunderland, Bournemouth 1 : 1 Leicester, Tottenham 0 : 0 Everton, West Brom 0 : 0 Southampton, Stoke 2 : 2 Leicester, Swansea 0 : 0 Everton, Liverpool 1 : 1 Norwich, Newcastle 2 : 2 Chelsea, West Ham 2 : 2 Norwich, Bournemouth 1 : 1 Watford, Sunderland 2 : 2 West Ham, Everton 1 : 1 Liverpool, Swansea 2 : 2 Tottenham, Southampton 2 : 2 Leicester, Tottenham 0 : 0 Liverpool, Liverpool 1 : 1 Southampton, Man United 0 : 0 Man City, Crystal Palace 0 : 0 Man United, Newcastle 0 : 0 Stoke, West Ham 1 : 1 Everton, Arsenal 1 : 1 Tottenham, Aston Villa 0 : 0 Man City, Swansea 2 : 2 Bournemouth, Bournemouth 3 : 3 Everton, Leicester 1 : 1 Man United, Norwich 1 : 1 Arsenal, Tottenham 0 : 0 Chelsea, West Ham 1 : 1 West Brom, Man United 0 : 0 West Ham, Southampton 1 : 1 Aston Villa, West Brom 1 : 1 Tottenham, Everton 1 : 1 Crystal Palace, Norwich 1 : 1 Everton, West Ham 0 : 0 Stoke, Liverpool 2 : 2 West Brom, Newcastle 1 : 1 Aston Villa, Swansea 0 : 0 West Ham, Aston Villa 1 : 1 West Ham, Bournemouth 0 : 0 Crystal Palace, Chelsea 2 : 2 Watford, Crystal Palace 0 : 0 Swansea, Man United 0 : 0 Chelsea, Leicester 0 : 0 Man City, Leicester 0 : 0 Bournemouth, Everton 1 : 1 Tottenham, Newcastle 3 : 3 Man United, Chelsea 2 : 2 West Brom, Liverpool 3 : 3 Arsenal, Man City 0 : 0 Everton, Aston Villa 1 : 1 Leicester, Chelsea 3 : 3 Everton, Stoke 0 : 0 Arsenal, Sunderland 1 : 1 Bournemouth, West Brom 0 : 0 Aston Villa, West Ham 2 : 2 Man City, Arsenal 0 : 0 Southampton, West Brom 1 : 1 Swansea, Watford 0 : 0 Chelsea, Liverpool 2 : 2 Sunderland, Swansea 1 : 1 Crystal Palace, Chelsea 1 : 1 Man United, Norwich 2 : 2 West Ham, Watford 0 : 0 Bournemouth, Leicester 2 : 2 West Brom, Sunderland 2 : 2 Crystal Palace, Chelsea 1 : 1 Stoke, Southampton 1 : 1 Sunderland, Tottenham 2 : 2 Arsenal, Norwich 0 : 0 Man City, Chelsea 2 : 2 West Ham, Newcastle 1 : 1 Sunderland, Liverpool 1 : 1 Tottenham, Stoke 2 : 2 Swansea, Sunderland 0 : 0 West Brom, West Ham 2 : 2 Crystal Palace, Watford 1 : 1 Everton, West Ham 3 : 3 Arsenal, Crystal Palace 0 : 0 Everton, Everton 1 : 1 Southampton, Arsenal 1 : 1 Crystal Palace, Leicester 2 : 2 West Ham, Newcastle 1 : 1 Man City, Liverpool 2 : 2 Newcastle, Sunderland 0 : 0 Arsenal, Tottenham 1 : 1 West Brom, Stoke 1 : 1 Sunderland, Man United 1 : 1 Leicester, Chelsea 2 : 2 Tottenham, Aston Villa 0 : 0 Newcastle, Bournemouth 1 : 1 West Brom, Man City 2 : 2 Arsenal, Liverpool 1 : 1 Chelsea, Chelsea 1 : 1 Leicester, Swansea 1 : 1 Man City, Watford 2 : 2 Sunderland, West Brom 1 : 1 Liverpool]", drawnMatches.toString().trim());
	}

	@Test
	void testGetBiggestSwing() {
		ArrayList<Match> swings11 = s11.getBiggestSwing();
		assertNotNull(swings11);
		assertEquals(4, swings11.size());
		assertNotNull(swings11.get(0));
		assertEquals("Wolves 2 : 0 Fulham", swings11.get(0).toString());
		assertNotNull(swings11.get(1));
		assertEquals("Fulham 5 : 0 Wolves", swings11.get(1).toString());
		assertNotNull(swings11.get(2));
		assertEquals("Blackburn 4 : 3 Arsenal", swings11.get(2).toString());
		assertNotNull(swings11.get(3));
		assertEquals("Arsenal 7 : 1 Blackburn", swings11.get(3).toString());
		
		ArrayList<Match> swings15 = s15.getBiggestSwing();
		assertNotNull(swings15);
		assertEquals(2, swings15.size());
		assertNotNull(swings15.get(0));
		assertEquals("Everton 6 : 2 Sunderland", swings15.get(0).toString());
		assertNotNull(swings15.get(1));
		assertEquals("Sunderland 3 : 0 Everton", swings15.get(1).toString());
		
		/*
		 * 
		 */
		ArrayList<Match> swings14 = s14.getBiggestSwing();
		assertNotNull(swings14);
		assertEquals(2, swings14.size());
		assertNotNull(swings14.get(0));
		assertEquals("Sunderland 0 : 8 Southampton", swings14.get(0).toString());
		assertNotNull(swings14.get(1));
		assertEquals("Southampton 1 : 2 Sunderland", swings14.get(1).toString());
	}

}
