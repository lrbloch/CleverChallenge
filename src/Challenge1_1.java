import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * @author Laura Bloch
 * 
 *         Challenge 1.1: Write a program that takes as its input a teacher's id
 *         and outputs the ids of the set of students the teacher can see
 * 
 */

public class Challenge1_1 {

	final static String SECTION_URL = "https://gist.github.com/jonahkagan/e983e30ffe514986fac6/raw/e61a04c543a1e896534887c215817cb919a98b08/data-sections.json";

	public static void main(String[] args) {
		JSONArray sectionArray = null;
		JSONObject sectionObj;
		JSONArray studentArray;

		// If user didn't provide teacher's ID
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

		// grab teacher ID from cmd line
		String teacherID = args[0];

		// loop through all sections
		for (int i = 0; i < sectionArray.size(); i++) {
			sectionObj = (JSONObject) sectionArray.get(i);
			String currTeacher = (String) sectionObj.get("teacher");
			if (currTeacher.equals(teacherID)) {
				// if current section's teacherID matches input,
				// print out the section's studentIDs
				studentArray = (JSONArray) sectionObj.get("students");
				for (int s = 0; s < studentArray.size(); s++) {
					System.out.println(studentArray.get(s));
				}
			}
		}
	}
}
