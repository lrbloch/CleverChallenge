import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * @author Laura Bloch
 * 
 *         Challenge 2. Write a program that takes as its input an app's id and
 *         returns the ids of the set of students the app can see.
 * 
 */
public class Challenge2 {

	static final String TEACHER_URL = "https://gist.github.com/jonahkagan/e983e30ffe514986fac6/raw/d879efe80f9e86688f9d439f904fd6d61f0de614/data-teachers.json";

	public static void main(String[] args) throws IOException, ParseException {
		JSONObject teacherObj;
		JSONArray teacherArray = null;
		
		// If user didn't provide app's ID
		if (args.length != 1) {
			ChallengeHelper.ReportError(1);
		}
		
		// grab JSONArray from url		
		try {
			teacherArray = ChallengeHelper.getJSONArray(TEACHER_URL);
		} 
		// Something went wrong at that URL
		catch (IOException e) {
			ChallengeHelper.ReportError(2);
		} 
		// Something went wrong parsing JSON Object
		catch (ParseException e) {
			ChallengeHelper.ReportError(3);
		}
		
		// grab app ID from cmd line
		String appID = args[0];
		
		//loop through all teachers
		for (int i = 0; i < teacherArray.size(); i++) {
			teacherObj = (JSONObject) teacherArray.get(i);
			JSONArray teacherApps = (JSONArray) teacherObj.get("apps");
			if (teacherApps.contains(appID)) {
				// if current teacher has an appID matching 
				// input, send that teacher's ID to Challenge1_1,
				// effectively printing out that teacher's students
				String teacherID = (String) teacherObj.get("id");
				Challenge1_1.main(new String[] { teacherID });
			}
		}
	}
}
