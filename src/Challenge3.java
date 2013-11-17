import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * @author Laura Bloch
 * 
 *         Challenge 3. Write a program that takes as its input an app's id and
 *         returns the ids of the set of students the app can see.
 * 
 */
public class Challenge3 {

	static final String SECTION_URL = "https://gist.github.com/jonahkagan/e983e30ffe514986fac6/raw/e61a04c543a1e896534887c215817cb919a98b08/data-sections.json";

	public static void main(String[] args) throws IOException, ParseException {
		JSONObject sectionObj;
		JSONArray studentArray;
		JSONArray sectionArray = null;

		// If user didn't provide app's ID
		if (args.length != 1) {
			ChallengeHelper.ReportError(1);
		}

		// grab JSONArray from URL
		try {
			sectionArray = ChallengeHelper.getJSONArray(SECTION_URL);
		}
		// Something went wrong at that URL
		catch (IOException e) {
			ChallengeHelper.ReportError(2);
		}
		// Something went wrong parsing JSON Object
		catch (ParseException e) {
			ChallengeHelper.ReportError(3);
		}

		// SectionArray uninitialized: this should not happen
		if (sectionArray == null) {
			ChallengeHelper.ReportError(4);
		}

		// grab app ID from cmd line
		String appID = args[0];
		
		// loop through all sections
		for (int i = 0; i < sectionArray.size(); i++) {
			sectionObj = (JSONObject) sectionArray.get(i);
			JSONArray currApps = (JSONArray) sectionObj.get("apps");
			if (currApps.contains(appID)) {
				// if current section contains the given appID,
				// print out the section's students
				studentArray = (JSONArray) sectionObj.get("students");
				for (int s = 0; s < studentArray.size(); s++) {
					System.out.println(studentArray.get(s));
				}
			}
		}
	}
}
