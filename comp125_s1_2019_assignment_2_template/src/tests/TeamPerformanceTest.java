package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stage1.Match;
import stage2.substage1.TeamPerformance;

class TeamPerformanceTest {
	TeamPerformance liverpool, manCity, chelsea, tottenham, arsenal, manUtd;
	TeamPerformance stoke, fulham, sunderland;

	@BeforeEach
	public void run() {
		liverpool = new TeamPerformance("Liverpool", 10, 8, -3, 28, 15); 
		//the -3 SHOULD result in gamesDrawn becoming 0

		manCity = new TeamPerformance("Man City", 10, 15, 0, 25, 4);
		//the 15 SHOULD result in gamesWon becoming gamesPlayed (10)

		chelsea = new TeamPerformance("Chelsea", 10, 0, 10, -13, -15);
		//the -13 and -15 should result in goalsScored and goalsConceded becoming 0

		tottenham = new TeamPerformance("Tottenham", 10, 7, 3, 19, 12); //same points as liverpool, but lower goal difference

		arsenal = new TeamPerformance("Arsenal", 10, 7, 3, 20, 13); //same points AND goalDifference as Tottenham

		manUtd = new TeamPerformance("Man United", -15, 15, 100, 20, 13); //gamesPlayed should become 0 and then other values are set accordingly


		//second constructor
		stoke = new TeamPerformance("Stoke", -6, 3); //goalsScored should become 0
		fulham = new TeamPerformance("Fulham", 1, -5); //goalsConcded should become 0
		sunderland = new TeamPerformance("Sunderland", 4, 4);
	}

	@Test
	void testTeamPerformanceStringIntIntIntInt() {
		/**
		 * the test for the constructor determines if each setter is implemented correctly.
		 * there are no separate tests for the setters. use the debugger to trace in case 
		 * there are failures
		 */
		assertEquals("Liverpool", liverpool.getName());
		assertEquals("Man City", manCity.getName());
		assertEquals("Chelsea", chelsea.getName());
		assertEquals("Man United", manUtd.getName());
		assertEquals("Tottenham", tottenham.getName());
		assertEquals("Arsenal", arsenal.getName());

		
		assertEquals(0, liverpool.getGamesDrawn());
		assertEquals(10, manCity.getGamesWon());
		assertEquals(0, chelsea.getGoalsScored());
		assertEquals(0, chelsea.getGoalsConceded());

		//manUtd had invalid parameter for gamesPlayed.
		//all subsequent values have chained dependency and are set to 0 with domino effect
		assertEquals(0, manUtd.getGamesPlayed());
		assertEquals(0, manUtd.getGamesWon());
		assertEquals(0, manUtd.getGamesDrawn());
		assertEquals(0, manUtd.getGoalsScored());
		assertEquals(0, manUtd.getGoalsConceded());

		assertEquals("Chelsea", chelsea.getName());
		assertEquals(19, tottenham.getGoalsScored());
		assertEquals(13, arsenal.getGoalsConceded());
	}

	@Test
	void testTeamPerformanceStringIntInt() {
		//stoke lost
		assertEquals("Stoke", stoke.getName());
		assertEquals(1, stoke.getGamesPlayed());
		assertEquals(0, stoke.getGamesWon());
		assertEquals(0, stoke.getGamesDrawn());
		assertEquals(0, stoke.getGoalsScored()); //negative becomes 0
		assertEquals(3, stoke.getGoalsConceded());

		//fulham won
		assertEquals("Fulham", fulham.getName());
		assertEquals(1, fulham.getGamesPlayed());
		assertEquals(1, fulham.getGamesWon());
		assertEquals(0, fulham.getGamesDrawn());
		assertEquals(1, fulham.getGoalsScored()); 
		assertEquals(0, fulham.getGoalsConceded()); //negative becomes 0

		//sunderland drew
		assertEquals("Sunderland", sunderland.getName());
		assertEquals(1, sunderland.getGamesPlayed());
		assertEquals(0, sunderland.getGamesWon());
		assertEquals(1, sunderland.getGamesDrawn());
	}
	
//	excluded from testing
//	@Test
//	void testGetPoints() {
//		assertEquals(24, liverpool.getPoints());
//		assertEquals(30, manCity.getPoints());
//		assertEquals(10, chelsea.getPoints());
//		assertEquals(24, tottenham.getPoints());
//		assertEquals(24, arsenal.getPoints());
//		assertEquals(0, manUtd.getPoints());
//		assertEquals(0, stoke.getPoints());
//		assertEquals(3, fulham.getPoints());
//		assertEquals(1, sunderland.getPoints());
//	}

//	excluded from testing
//	@Test
//	void testGetGoalDifference() {
//		assertEquals(13, liverpool.getGoalDifference());
//		assertEquals(21, manCity.getGoalDifference());
//		assertEquals(0, chelsea.getGoalDifference());
//		assertEquals(7, tottenham.getGoalDifference());
//		assertEquals(7, arsenal.getGoalDifference());
//		assertEquals(0, manUtd.getGoalDifference());
//		assertEquals(-3, stoke.getGoalDifference());
//		assertEquals(1, fulham.getGoalDifference());
//		assertEquals(0, sunderland.getGoalDifference());
//	}

	@Test
	void testCompareTo() {
		//Man City on top with 30 points
		assertEquals(1, manCity.compareTo(liverpool));
		assertEquals(1, manCity.compareTo(chelsea));
		assertEquals(1, manCity.compareTo(tottenham));

		assertEquals(-1, chelsea.compareTo(tottenham));

		assertEquals(-1, chelsea.compareTo(liverpool)); //on points
		assertEquals(-1, tottenham.compareTo(liverpool)); //same points -> on goal difference
		assertEquals(0, tottenham.compareTo(arsenal)); //same points, same goal difference
	}

	@Test
	void testAddMatchRecord() {
		//before the match: 
		//Man City: played 10, won 10, drawn 0, goals scored: 25, goals conceded: 4 (30 points)
		//Liverpool: played 10, won 8, drawn 0, goals scored: 18, goals conceded: 15 (24 points)
		Match m1 = new Match("Man City", "Liverpool", 1, 2); 
		liverpool.addMatchRecord(m1);
		manCity.addMatchRecord(m1);

		assertEquals(27, liverpool.getPoints());
		assertEquals(11, liverpool.getGamesPlayed());
		assertEquals(9, liverpool.getGamesWon());
		assertEquals(0, liverpool.getGamesDrawn());
		assertEquals(30, liverpool.getGoalsScored());
		assertEquals(16, liverpool.getGoalsConceded());

		assertEquals(30, manCity.getPoints());
		assertEquals(11, manCity.getGamesPlayed());
		assertEquals(10, manCity.getGamesWon());
		assertEquals(0, manCity.getGamesDrawn());
		assertEquals(26, manCity.getGoalsScored());
		assertEquals(6, manCity.getGoalsConceded());
		
		//before the match: 
		//Stoke: played 1, won 0, drawn 0, goals scored: 0, goals conceded: 3 (0 points)
		//Arsenal: played 10, won 7, drawn 3, goals scored: 20, goals conceded: 13 (24 points)
		Match m2 = new Match("Stoke", "Arsenal", 5, 0); 
		arsenal.addMatchRecord(m2);
		stoke.addMatchRecord(m2);

		assertEquals(3, stoke.getPoints());
		assertEquals(2, stoke.getGamesPlayed());
		assertEquals(1, stoke.getGamesWon());
		assertEquals(0, stoke.getGamesDrawn());
		assertEquals(5, stoke.getGoalsScored());
		assertEquals(3, stoke.getGoalsConceded());
		
		assertEquals(24, arsenal.getPoints());
		assertEquals(11, arsenal.getGamesPlayed());
		assertEquals(7, arsenal.getGamesWon());
		assertEquals(3, arsenal.getGamesDrawn());
		assertEquals(20, arsenal.getGoalsScored());
		assertEquals(18, arsenal.getGoalsConceded());

		//before the match: 
		//Tottenham: played 10, won 7, drawn 3, goals scored: 19, goals conceded: 12  (24 points)
		//Chelsea: played 10, won 0, drawn 10, goals scored: 0, goals conceded: 0 (10 points)
		Match m3 = new Match("Tottenham", "Chelsea", 3, 3); 
		tottenham.addMatchRecord(m3);
		chelsea.addMatchRecord(m3);
		
		assertEquals(25, tottenham.getPoints());
		assertEquals(11, tottenham.getGamesPlayed());
		assertEquals(7, tottenham.getGamesWon());
		assertEquals(4, tottenham.getGamesDrawn());
		assertEquals(22, tottenham.getGoalsScored());
		assertEquals(15, tottenham.getGoalsConceded());
		
		assertEquals(11, chelsea.getPoints());
		assertEquals(11, chelsea.getGamesPlayed());
		assertEquals(0, chelsea.getGamesWon());
		assertEquals(11, chelsea.getGamesDrawn());
		assertEquals(3, chelsea.getGoalsScored());
		assertEquals(3, chelsea.getGoalsConceded());
	}

}
