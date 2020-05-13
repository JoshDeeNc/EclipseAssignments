package doNotModify;

/**
 * DO NOT MODIFY THIS CLASS
 * @author gauravgupta
 * provides a service to pacify my O.C.D. (so that columns in outputs are aligned)
 */
public class PrettyPrinterService {
	/**
	 * @param s
	 * @param len
	 * @return string s padded with spaces to the right so its length is len
	 */
	public static String padRight(String s, int len) {
		int columnLength = len;
		int gap = columnLength - s.length();
		String result = s;
		for(int i=0; i < gap; i++) {
			result+=" ";
		}
		return result;
	}
	
	/**
	 * @param s
	 * @param len
	 * @return string s padded with spaces to the right so its length is len
	 */
	public static String padLeft(String s, int len) {
		int columnLength = len;
		int gap = columnLength - s.length();
		String result = "";
		for(int i=0; i < gap; i++) {
			result+=" ";
		}
		return result + s;
	}
}
