package verchaska.practise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidation {
	
	public boolean validate(String input, String initialFormat) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(initialFormat);
			format.setLenient(false);
			format.parse(input);
			return true;
		} catch (ParseException e) {
			System.out.println("invalid input");
			return false;

		}
	}

	public boolean validate(Date input, String newFormat) {
		try {
			SimpleDateFormat userFormat = new SimpleDateFormat(newFormat);
			String newOutput = userFormat.format(input);
			System.out.println("new output is\n" + newOutput);
			return true;
		} catch (Exception e) {
			System.out.println("invalid format");
			return false;
		}
	}

	public String changeCasing(String format) {
		StringBuilder changedCasing = new StringBuilder(format);
		for (int i = 0; i < changedCasing.length(); i++) {
			if (changedCasing.charAt(i) == 'm')
				changedCasing.setCharAt(i, 'M');
			if (changedCasing.charAt(i) == 'h')
				changedCasing.setCharAt(i, 'H');
		}
		return changedCasing.toString();
	}

}
