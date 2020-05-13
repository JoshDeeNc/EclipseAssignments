package stage1;

import java.text.DecimalFormat;

/**
 * 
 * @author gauravgupta
 * class holds the most relevant information from a soccer game's result.
 * For example, if Chelsea play at home stadium against Liverpool (thus,
 * we say, liverpool is playing "away" or is the away team while chelsea 
 * is the home team), and the score is Chelsea 2 - Liverpool 1, the 
 * home team has scored 2 goals and the away team has scored 1 goal.
 * The home team ends up winning.
 */
public class Match {
	private String homeTeam, awayTeam;
	private int homeGoals, awayGoals;
	
	public String getHomeTeam() {
		return homeTeam;
	}
	
	private void setHomeTeam(String ht) {
		this.homeTeam = ht;
	}
	
	public String getAwayTeam() {
		return awayTeam;
	}
	
	private void setAwayTeam(String at) {
		this.awayTeam = at;
	}
	
	public int getHomeGoals() {
		return homeGoals;
	}
	
	/**
	 * 
	 * @param hg
	 * set homeGoals to the higher of 0 and hg
	 */
	private void setHomeGoals(int hg) {
		this.homeGoals = Math.max(0, hg);
	}
	
	public int getAwayGoals() {
		return awayGoals;
	}
	
	/**
	 * 
	 * @param ag
	 * set awayGoals to the higher of 0 and ag
	 */
	private void setAwayGoals(int ag) {
		this.awayGoals = Math.max(0, ag);
	}
	
	/**
	 * DO NOT MODIFY!!! 
	 * Complete the setters correctly and the test for the constructor will pass
	 * @param ht
	 * @param at
	 * @param hg
	 * @param ag
	 */
	public Match(String ht, String at, int hg, int ag) {
		setHomeTeam(ht);
		setAwayTeam(at);
		setHomeGoals(hg);
		setAwayGoals(ag);
	}
	
	/**
	 * 
	 * @return goal difference (home team over away team)
	 */
	public int goalDifference() {
		return homeGoals - awayGoals;
	}
	
	/**
	 * 
	 * @return 1 if home team won, -1 if away team won, 0 if draw
	 */
	public int winner() {
		if(homeGoals > awayGoals) {
			return 1;
		}
		if(homeGoals < awayGoals) {
			return -1;
		}
		return 0;
	}
	
	public String toString() {
		return homeTeam+" "+homeGoals+" : "+awayGoals+" "+awayTeam;	
	}

}