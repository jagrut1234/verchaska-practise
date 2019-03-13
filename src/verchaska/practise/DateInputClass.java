package verchaska.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInputClass {

	public Date getDDMMYYYYInput() throws IOException, ParseException {
		String date = null;
		boolean isCorrectDate = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (!isCorrectDate) {

			date = br.readLine();
			isCorrectDate = new DateInputClass().validate(date, "dd/MM/yyyy");
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		return dateFormat.parse(date);
	}

	private boolean validate(String date, String dateFormat) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			format.setLenient(false);
			format.parse(date);
			return true;
		} catch (ParseException e) {
			System.out.println("invalid input enter again");
			return false;

		}
	}

	public Date getMMDDYYYYInput() throws IOException, ParseException {
		String date = null;
		boolean isCorrectDate = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (!isCorrectDate) {

			date = br.readLine();
			isCorrectDate = new DateInputClass().validate(date, "MM/dd/yyyy");
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		return dateFormat.parse(date);
	}

	public Date getDateInput(String format) throws IOException, ParseException {
		String date = null;
		boolean isCorrectDate = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (!isCorrectDate) {

			date = br.readLine();
			isCorrectDate = new DateInputClass().validate(date, format);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		
		return dateFormat.parse(date);
	}

}
