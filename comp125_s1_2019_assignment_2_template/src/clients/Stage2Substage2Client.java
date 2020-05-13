package clients;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import stage1.*;
import stage2.*;
import stage2.substage1.*;
import stage2.substage2.Season;

/**
 * DO NOT MODIFY
 * @author gauravgupta
 *
 */
public class Stage2Substage2Client {

	public static void main(String[] args) throws FileNotFoundException {
		Season season = new Season("0910.csv");
		ArrayList<Match> biggestSwings = season.getBiggestSwing();
		System.out.println(season.getSeasonYear()+": "+biggestSwings);

		for(int i=10; i <=18; i++) {
			season = new Season(i+""+(i+1)+".csv");
			biggestSwings = season.getBiggestSwing();
			System.out.println(season.getSeasonYear()+": "+biggestSwings);
		}
	}

}
