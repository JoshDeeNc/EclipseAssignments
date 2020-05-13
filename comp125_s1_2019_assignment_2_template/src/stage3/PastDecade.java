package stage3;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import stage2.*;
import stage2.substage1.*;
import stage2.substage2.*;
import stage2.substage3.*;

public class PastDecade {
	private ArrayList<PointsTable> endOfSeasonTables;
	private int startYear;

	/**
	 * DO NOT MODIFY
	 * @param start
	 * @throws FileNotFoundException
	 */
	public PastDecade(int start) throws FileNotFoundException {
		endOfSeasonTables = new ArrayList<PointsTable>();
		DecimalFormat df = new DecimalFormat("00");
		startYear = start;
		for(int i=startYear; i < startYear+10; i++) {
			endOfSeasonTables.add(new PointsTable(new Season(df.format(i)+df.format(i+1)+".csv")));
		}
	}

	/**
	 * DO NOT MODIFY
	 */
	public String toString() {
		String result = "";
		for(PointsTable table: endOfSeasonTables) {
			result = result + table + "\n";
		}
		return result;
	}
	
	/**
	 * 
	 * @param team
	 * @return a list of the passed team's standings from oldest to newest.
	 * if a team doesn't appear in a particular season, null should be inserted
	 * for that season
	 */
	public ArrayList<Integer> getStandings(String team) {
		//gets the teams standings from oldest to newest and adds null if the team didn't play that season
		ArrayList<Integer> Standings = new ArrayList<Integer>();
		for(PointsTable teamStandings: endOfSeasonTables) {
			if(teamStandings.teamExists(team)) {
				Standings.add(teamStandings.getStanding(team));
			}
			else Standings.add(null);
		}
		return Standings;
	}
	
	/**
	 * D question
	 * @param team
	 * @return the weighted standing of the team passed as the parameter.
	 * the oldest season (first item in list endOfSeasonTables) has weight 1,
	 * the second-oldest season (second item in list endOfSeasonTables) has weight 2,
	 * ...
	 * the latest season (last item in list endOfSeasonTables) has weight endOfSeasonTables.size(),
	 * 
	 * Example (I wrote an explanation but the example is much better):
	 * 
	 * Return null if the club hasn't played during ANY season
	 * Note: It is possible that a club hasn't played some seasons during the last decade,
	 * in such a situation, that year should simply be ignored for the club.
	 * 
	 * For example, 
	 * Newcastle's standings are [null, 12, 5, 16, 10, 15, 18, null, 10, 13]
	 * null means they didn't play 09/10 16/17 seasons
	 * Their weighted standing would be:
	 * (2*12 + 3*5 + 4*16 + 5*10 + 6*15 + 7*18 + 9*10 + 10*13)/(2+3+4+5+6+7+9+10)
	 * 
	 * Brighton only played season 17/18 and 18/19.
	 * Hence their weighted standing would be (9*15 + 10*17)/(9+10) = 305/19 = 16.05 
	 */
	public Double getWeightedStanding(String team) {
		/*first adds the teams standings for each season to the standingsWithWeighting ArrayList.
		 * Also adds 1 every time a team has played a season to seasonsPlayed ArrayList.
		 * if the team hasn't played any season, it returns null.
		 * Using this array, we add all the standings in standingsWithWeighting to the double totalStandings,
		 * and seasonsPlayed to totalSeasonsPlayed. finally, we divide totalStandings by totalSeasonsPlayed
		 * to get the average, then return the average (I didn't round so that it is a more accurate result).
		 */
		ArrayList<Integer> standingsWithWeighting = new ArrayList<Integer>();
		ArrayList<Integer> seasonsPlayed = new ArrayList<Integer>();
		double totalStandings = 0;
		double totalSeasonsPlayed = 0;
		boolean noSeason = true;
		
		for(int i = 0; i < endOfSeasonTables.size(); i++) {
			if(endOfSeasonTables.get(i).teamExists(team)) {
				standingsWithWeighting.add((i+1)*(endOfSeasonTables.get(i).getStanding(team)));
				seasonsPlayed.add(i+1);
				noSeason = false;
			}
		}
		
		if(noSeason) {
			return null;
		}
		
		for(double standings: standingsWithWeighting) {
			totalStandings+=standings;
		}
		
		for(double seasons: seasonsPlayed) {
			totalSeasonsPlayed += seasons;
		}
		
		double totalWeighting = totalStandings/totalSeasonsPlayed;
		return totalWeighting;		
	}
	
	/**
	 * HD question
	 * @return a table with clubs ranked based on their weighted standings
	 */
	public ArrayList<String> getWeightedTable() {
		return null; //to be completed
	}
}
