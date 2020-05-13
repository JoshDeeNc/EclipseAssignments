package clients;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import stage3.PastDecade;

public class Stage3Client {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		PastDecade lastTenYears = new PastDecade(9);
		System.out.println(lastTenYears);
		ArrayList<String> overallTable = lastTenYears.getWeightedTable();
		int standing = 1;
		System.out.println("Last ten years' weighted standings: ");
		for(String t: overallTable) {
			System.out.println((standing++)+". "+t+": (oldest first) "+lastTenYears.getStandings(t));
		}
	}

}
