/**
 * DO NOT MODIFY
 */
package stage2.substage1;

import java.text.DecimalFormat;

import stage1.*;
import doNotModify.*;

public class TeamPerformance implements Comparable<TeamPerformance> {
	private String name;
	private int gamesPlayed, gamesWon, gamesDrawn;
	private int goalsScored, goalsConceded;

	public String getName() {
		return name;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public int getGamesDrawn() {
		return gamesDrawn;
	}

	public int getGoalsScored() {
		return goalsScored;
	}

	public int getGoalsConceded() {
		return goalsConceded;
	}

	/*
	 * setters are private since we don't want
	 * clients to modify these values.
	 * these setters are called when the 
	 * object is created or when a new match 
	 * record is added using the addMatchRecord method
	 */

	private void setName(String name) {
		this.name = name;
	}

	/**
	 * @param played
	 * assign, to instance variable gamesPlayed, the higher of 0 and played
	 */
	private void setGamesPlayed(int played) {
		//Math.max sets highest value, meaning if parameter is a negative number it will return 0
		gamesPlayed = Math.max(0, played);
	}

	/**
	 * assumption: gamesWon set correctly
	 * @param won
	 * assign, to the instance variable gamesWon, a value based on parameter.
	 * if won > gamesPlayed, gamesWon should becomes gamesPlayed
	 * if won < 0, gamesWon should becomes 0
	 * in all other cases, gamesWon should be assigned the value won
	 */
	private void setGamesWon(int won) {
		//sets gamesWon accordingly
		if(won > gamesPlayed) {
			gamesWon = gamesPlayed;
		}
		else gamesWon = Math.max(0, won);
	}

	/**
	 * assumption: gamesWon AND gamesPlayed set correctly
	 * @param drawn
	 * assign, to the instance variable gamesDrawn, a value based on parameter.
	 * if drawn > gamesPlayed - gamesWon, gamesDrawn should becomes gamesPlayed - gamesWon
	 * if drawn < 0, gamesDrawn should becomes 0
	 * in all other cases, gamesDrawn should be assigned the value drawn
	 */
	private void setGamesDrawn(int drawn) {
		//sets gamesDrawn accordingly
		if(drawn > (gamesPlayed - gamesWon)) {
			gamesDrawn = gamesPlayed - gamesWon;
		}
		else gamesDrawn = Math.max(0, drawn);
	}

	/**
	 * DO NOT MODIFY
	 * assumption: gamesPlayed, gamesWon AND gamesDrawn set correctly.
	 * @return number of games lost
	 */
	public int getGamesLost() {
		return gamesPlayed-gamesWon-gamesDrawn;
	}

	/**
	 * @param gs
	 * assign, to instance variable goalsScored, the higher of 0 and gs
	 * UNLESS... gamesPlayed is 0. in that case, goalsScored should be 0.
	 * because if you haven't played a game, you can't score.
	 * 
	 * I would have liked to include another scenario where you check if 
	 * the team has won a game, in which case, goalsScored CANNOT be zero.
	 * In general, if gamesWon = N, goalsScored >= N
	 * 
	 * However, a bit too much for this assignment, I figured.
	 */
	private void setGoalsScored(int gs) {
		//sets goalsScored accordingly
		if(gamesPlayed != 0) {
			goalsScored = Math.max(0, gs);
		}
		else goalsScored = 0;
	}

	/**
	 * @param gc
	 * assign, to instance variable goalsConceded, the higher of 0 and gc.
	 * UNLESS... gamesPlayed is 0. in that case, goalsConceded should be 0.
	 * because you cannot concede a goal if you haven't played a game 
	 * (no matter how bad you are :D).
	 * 
	 * Again, I would have liked to add the scenario to enforce
	 * that if gamesLost = N, goalsConceded >= N
	 * but... logic is overly complex for majority of students 
	 * for the assignment
	 */
	private void setGoalsConceded(int gc) {
		if(gamesPlayed !=0) {
			goalsConceded = Math.max(0, gc);
		}
		else goalsConceded = 0;
	}

	/**
	 * DO NOT MODIFY!!! 
	 * Complete the setters correctly and the test for the constructor will pass
	 * 
	 * @param name
	 * @param played
	 * @param won
	 * @param drawn
	 * @param gs
	 * @param gc
	 * 
	 * using the setters, set instance variables based on parameters
	 */
	public TeamPerformance(String name, int played, int won, int drawn, int gs, int gc) {
		setName(name);
		setGamesPlayed(played);
		setGamesWon(won);
		setGamesDrawn(drawn);
		setGoalsScored(gs);
		setGoalsConceded(gc);
	}

	/**
	 * When the first match is added for a team, we don't need to pass
	 * 1 for gamesPlayed and a value for gamesWon. We can calculate that 
	 * using goalsScored and goalsConceded.
	 * 
	 * populate TeamPerformance using parameters passed.
	 * set gamesWon and gamesDrawn based on goals scored and goals conceded.
	 * if goalsScored > goalsConceded -> think
	 * if goalsScored == goalsConceded -> think
	 * 
	 * @param name
	 * @param gs: goals scored
	 * @param gc: goals conceded
	 */
	public TeamPerformance(String name, int gs, int gc) {
		setName(name);
		//setters are set based on gs and gc with if statements
		if(gs >= 0 || gc >= 0) {
			setGamesPlayed(1);
		}
		if(gs < 0 && gc < 0) {
			setGamesPlayed(0);
		}
		if(gs > gc) {
			setGamesWon(1);
		}
		if(gs <= gc) {
			setGamesWon(0);
		}
		if(gs == gc) {
			setGamesDrawn(1);
		}
		if(gc != gs) {
			setGamesDrawn(0);
		}
		setGoalsScored(gs);
		setGoalsConceded(gc);
		
	}

	/**
	 * DO NOT MODIFY
	 */
	public String toString() {
		return PrettyPrinterService.padRight(name, 15)+"Games played: "+gamesPlayed+". Games won: "+gamesWon+". Games drawn: "+gamesDrawn+". Games lost: "+getGamesLost()+". Points: "+PrettyPrinterService.padLeft(((3*gamesWon + gamesDrawn)+""), 3)+". Goal difference: "+PrettyPrinterService.padLeft((((goalsScored - goalsConceded))+""), 3);

	}

	/**
	 * DO NOT MODIFY
	 * @return points based on the formula that every win gets you 
	 * 3 points which every draw gets you 1 point
	 */
	public int getPoints() {
		// TODO Auto-generated method stub
		return 3*gamesWon + gamesDrawn;
	}

	/**
	 * DO NOT MODIFY
	 * @return the goal difference (goals scored minus goals conceded)
	 */
	public int getGoalDifference() {
		return this.getGoalsScored() - this.getGoalsConceded();
	}

	/**
	 * return 1 if calling object has more points than that of parameter object
	 * return -1 if calling object has less points than that of parameter object
	 * in case the two have the same number of points - 
	 * 		return 1 if calling object has a higher goal difference than that of parameter object
	 * 		return -1 if calling object has a lower goal difference than that of parameter object
	 * 		return 0 if calling object has the same goal difference as that of parameter object
	 */
	public int compareTo(TeamPerformance other) {
		//returns compareTo accordingly
		if(this.getPoints() > other.getPoints()) {
			return 1;
		}
		if(this.getPoints() < other.getPoints()) {
			return -1;
		}
		if(this.getGoalDifference() > other.getGoalDifference()) {
			return 1;
		}
		if(this.getGoalDifference() < other.getGoalDifference()) {
			return -1;
		}
		else return 0;
	}

	/**
	 * add the match passed to the teams record.
	 * note that the team corresponding to the calling object 
	 * might be the home team or the away team (or neither!) in the game passed.
	 * 
	 * based on different scenarios, the several attributes of the calling object
	 * need to be updated.
	 *  
	 * for example, 
	 * if calling object represents Chelsea's team performance and the
	 * match passed was between Arsenal and Liverpool, nothing changes for the calling object.
	 * 
	 * if calling object represents Chelsea's team performance and the
	 * match passed was between Arsenal (Home) and Chelsea (Away), 
	 * Chelsea's gamesPlayed increases by 1, and then based on who won,
	 * gamesWon or gamesDrawn will be updated. If Chelsea lost, neither gets changed,
	 * and getGamesLost() will return the correct value (as gamesPlayed HAS increased by 1).
	 * Away goals for Chelsea need to be updated too.
	 * 
	 * Similar scenarios when Chelsea draws or wins away.
	 * 
	 * Similar scenarios when Chelsea wins, draws or loses at home.
	 * @param m
	 */
	public void addMatchRecord(Match m) {
		/*checks whether calling object is mentioned in either the home team or away team.
		 * if it is one of them, this program will update the properties of that team's performance accordingly.
		 * if it is not one of them, nothing changes for that team's performance.
		 */
		if(this.getName().equals(m.getHomeTeam())) {
			setGamesPlayed(this.gamesPlayed+=1);
			
			if(m.getHomeGoals() > m.getAwayGoals()) {
				setGamesWon(this.gamesWon+=1);
			}
			
			if(m.getHomeGoals() == m.getAwayGoals()) {
				setGamesDrawn(this.gamesDrawn+=1);
			}
			setGoalsScored(this.goalsScored += m.getHomeGoals());
			setGoalsConceded(this.goalsConceded += m.getAwayGoals());
		}
		
		if(this.getName().equals(m.getAwayTeam())) {
			setGamesPlayed(this.gamesPlayed+=1);
			
			if(m.getAwayGoals() > m.getHomeGoals()) {
				setGamesWon(this.gamesWon+=1);
			}
			if(m.getHomeGoals() == m.getAwayGoals()) {
				setGamesDrawn(this.gamesDrawn+=1);
			}
			setGoalsScored(this.goalsScored += m.getAwayGoals());
			setGoalsConceded(this.goalsConceded += m.getHomeGoals());
		}
	}
}
