import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Laura Bloch
 * 
 *         Helper functions for Challenges
 * 
 */
public class ChallengeHelper {

	/**
	 * Method to get a JSONArray from a JSON's URL
	 * 
	 * @param url
	 *            : location of JSONObject
	 * @return : JSONArray made from JSON at given URL
	 * @throws IOException
	 *             : if the URL does not contain a JSONObject or the URL does
	 *             not exist
	 * @throws ParseException
	 *             : if the object cannot be parsed
	 */
	public static JSONArray getJSONArray(String url) throws IOException,
			ParseException {
		JSONParser parser = new JSONParser();
		URL u = new URL(url);
		InputStream in = u.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		return (JSONArray) parser.parse(br);
	}

	/**
	 * Used by other programs in order to print out error messages due to caught
	 * exceptions, missing arguments, uninitialized objects
	 * 
	 * @param errCode
	 *            : the number associated with the type of error
	 */
	public static void ReportError(int errCode) {
		switch (errCode) {
		case 1:
			System.out
					.println("Please enter the program name followed by a string ID");
			break;
		case 2:
			System.out.println("Could not fetch JSON from URL");
			break;
		case 3:
			System.out.println("Not a valid JSON Object");
			break;
		default:
			System.out.println("System encountered unknown error with ID "
					+ errCode);
		}
		System.exit(errCode);
	}
}
