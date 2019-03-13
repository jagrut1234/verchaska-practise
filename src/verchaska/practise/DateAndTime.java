package verchaska.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTime {
	public static void main(String args[]) throws IOException, ParseException {
		boolean isCorrectDate = false;
		boolean isCorrectTime = false;
		boolean isCorrectDateFormat = false;
		boolean isCorrectTimeFormat = false;
		String newDateFormat = null;
		String newTimeFormat = null;
		String date = null;
		String time = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (!isCorrectDate) {
			System.out.println("enter date in format dd/mm/yyyy:");
			date = br.readLine();
			isCorrectDate = new DateValidation().validate(date, "dd/MM/yyyy");
		}
		while (!isCorrectTime) {
			System.out.println("Enter time in format hh:mm:ss");
			time = br.readLine();
			isCorrectTime = new DateValidation().validate(time, "HH:mm:ss");
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date parsedDate = dateFormat.parse(date);

		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date parsedTime = timeFormat.parse(time);

		while (!isCorrectDateFormat) {
			System.out.println("Enter new Date format");
			newDateFormat = br.readLine();
			newDateFormat = newDateFormat.toLowerCase();
			newDateFormat = new DateValidation().changeCasing(newDateFormat);
			isCorrectDateFormat = new DateValidation().validate(parsedDate, newDateFormat);

		}

		while (!isCorrectTimeFormat) {
			System.out.println("Enter new Time format");
			newTimeFormat = br.readLine();
			newTimeFormat = newTimeFormat.toLowerCase();
			newTimeFormat = new DateValidation().changeCasing(newTimeFormat);
			isCorrectTimeFormat = new DateValidation().validate(parsedTime, newTimeFormat);

		}
		br.close();
	}

}
