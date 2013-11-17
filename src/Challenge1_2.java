import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * @author Laura Bloch
 * 
 *         Challenge 1.2: Write a program that takes as its input a student's id
 *         and outputs the set of teachers that can see that student
 */

public class Challenge1_2 {
	static final String SECTION_URL = "https://gist.github.com/jonahkagan/e983e30ffe514986fac6/raw/e61a04c543a1e896534887c215817cb919a98b08/data-sections.json";
	public static void main(String[] args) throws IOException, ParseException {
		JSONArray sectionArray = null;
		JSONObject sectionObj = null;
		
		// If user didn't provide student's ID
		if (args.length != 1) {
			ChallengeHelper.ReportError(1);
		}
		
		// Grab JSONArray from URL
		try {
			sectionArray = ChallengeHelper
					.getJSONArray(SECTION_URL);
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
		
		// grab studentID from cmd line
		String studentID = args[0];
		
		// loop through all sections
		for (int i = 0; i < sectionArray.size(); i++) {
			sectionObj = (JSONObject) sectionArray.get(i);
			JSONArray currStudents = (JSONArray) sectionObj.get("students");
			for (Object obj : currStudents) {
				if (obj.equals(studentID)) {
					// if current section contains given studentID,
					// print out the section's teacherID
					System.out.println(sectionObj.get("teacher"));
				}
			}
		}
	}
}
