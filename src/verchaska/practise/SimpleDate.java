package verchaska.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDate {
	public static void main(String args[]) throws IOException, ParseException {
		boolean isCorrectDate = false;
		boolean isCorrectDateFormat = false;
		String newDateFormat = null;
		String date = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (!isCorrectDate) {
			System.out.println("enter date in format dd/mm/yyyy:");
			date = br.readLine();
			isCorrectDate = new DateValidation().validate(date, "dd/MM/yyyy");
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date parsedDate = dateFormat.parse(date);

		while (!isCorrectDateFormat) {
			System.out.println("Enter new Date format");
			newDateFormat = br.readLine();
			newDateFormat = newDateFormat.toLowerCase();
			newDateFormat = new DateValidation().changeCasing(newDateFormat);
			isCorrectDateFormat = new DateValidation().validate(parsedDate, newDateFormat);

		}
		br.close();

	}

	

}
