package clients;

import java.io.FileNotFoundException;

import stage2.*;
import stage2.substage1.*;
import stage2.substage2.Season;
import stage2.substage3.PointsTable;

/**
 * DO NOT MODIFY
 * @author gauravgupta
 *
 */
public class Stage2Substage3Client {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Season season = new Season("1516.csv");

		PointsTable table = new PointsTable(season);
		
		System.out.println(table);
		System.out.println();
	}

}
