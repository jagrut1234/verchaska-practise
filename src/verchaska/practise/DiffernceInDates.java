package verchaska.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiffernceInDates {
	public static void main(String args[]) throws IOException, ParseException {
		boolean isCorrectDate = false;
		String startDate = null;
		String endDate = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (!isCorrectDate) {
			System.out.println("enter date in format dd/mm/yyyy:");
			startDate = br.readLine();
			isCorrectDate = new DateValidation().validate(startDate, "dd/MM/yyyy");
		}
		isCorrectDate = false;

		while (!isCorrectDate) {
			System.out.println("enter date in format dd/mm/yyyy:");
			endDate = br.readLine();
			isCorrectDate = new DateValidation().validate(endDate, "dd/MM/yyyy");
		}

		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date parsedStartDate = dateformat.parse(startDate);
		Date parsedEndDate = dateformat.parse(endDate);
		int differenceInDays = (int) ((Math.abs(parsedStartDate.getTime() - parsedEndDate.getTime()))
				/ (1000 * 60 * 60 * 24));
		System.out.println("difference in days between start date and end date is " + differenceInDays);

		br.close();
	}

}
