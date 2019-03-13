package verchaska.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author jdava
 *
 */
public class InputClass {

	public int getNaturalNumberInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = 0;
		String dummyNumber = br.readLine();
		while (!new InputClass().isCorrectNaturalNumberInput(dummyNumber)) {
			dummyNumber = br.readLine();
		}
		number = Integer.parseInt(dummyNumber);
		return number;

	}

	private boolean isCorrectNaturalNumberInput(String dummyNumber) {
		try {
			int number = Integer.parseInt(dummyNumber);
			if (number <= 0) {
				throw new NumberFormatException();
			}

		} catch (Exception e) {
			System.out.println("invalid input enter again");
			return false;
		}
		return true;

	}

	public int getWholeNumberInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = 0;
		String dummyNumber = br.readLine();
		while (!new InputClass().isCorrectWholeNumberInput(dummyNumber)) {
			dummyNumber = br.readLine();
		}
		number = Integer.parseInt(dummyNumber);
		return number;

	}

	private boolean isCorrectWholeNumberInput(String dummyNumber) {
		try {
			int number = Integer.parseInt(dummyNumber);
			if (number < 0) {
				throw new NumberFormatException();
			}

		} catch (Exception e) {
			System.out.println("invalid input enter again");
			return false;
		}
		return true;
	}

	public int getIntegerInput() throws IOException {
		int number = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String dummyNumber = br.readLine();
		while (!new InputClass().isCorrectIntegerInput(dummyNumber)) {
			dummyNumber = br.readLine();
		}
		number = Integer.parseInt(dummyNumber);
		return number;

	}

	private boolean isCorrectIntegerInput(String dummyNumber) {
		try {
			Integer.parseInt(dummyNumber);

		} catch (Exception e) {
			System.out.println("invalid input enter again");
			return false;
		}
		return true;
	}

	public String getStringInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		return input;
	}

	public int getNaturalNumberInputInRange(int upperLimit) throws IOException {
		int input = new InputClass().getNaturalNumberInput();
		while (input >= upperLimit) {
			System.out.println("invalid input enter again");
			input = new InputClass().getNaturalNumberInput();
		}
		return input;
	}

	public int getNaturalNumberInputInRange(int lowerLimit, int upperLimit) throws IOException {
		int input = new InputClass().getNaturalNumberInput();
		while (input <= lowerLimit || input >= upperLimit) {
			System.out.println("invalid input enter again");
			input = new InputClass().getNaturalNumberInput();

		}
		return input;
	}

	public int getWholeNumberInputInRange(int upperLimit) throws IOException {
		int input = new InputClass().getWholeNumberInput();
		while (input >= upperLimit) {
			System.out.println("invalid input enter again");
			input = new InputClass().getWholeNumberInput();
		}
		return input;
	}

	public int getIntegerNumberInputInRange(int lowerLimit, int upperLimit) throws IOException {
		int input = new InputClass().getIntegerInput();
		while (input <= lowerLimit || input >= upperLimit) {
			System.out.println("invalid input enter again");
			input = new InputClass().getIntegerInput();

		}
		return input;

	}

	public float getFloatInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		float input = 0;
		String dummyInput = br.readLine();
		while (!new InputClass().isCorrectFloatNumber(dummyInput)) {
			dummyInput = br.readLine();
		}
		input = Float.parseFloat(dummyInput);
		return input;
	}

	private boolean isCorrectFloatNumber(String dummyInput) {
		try {
			Float.parseFloat(dummyInput);
		} catch (Exception e) {
			System.out.println("invalid input enter again");
			return false;
		}
		return true;
	}

	public long getLongInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long input = 0;
		String dummyInput = br.readLine();
		while (!new InputClass().isCorrectLongNumber(dummyInput)) {
			dummyInput = br.readLine();
		}
		input = Long.parseLong(dummyInput);
		return input;
	}

	private boolean isCorrectLongNumber(String dummyInput) {
		try {
			Long.parseLong(dummyInput);
		} catch (Exception e) {
			System.out.println("invalid input enter again");
			return false;
		}
		return true;
	}

	public float getFloatInputInRange(float lowerLimit, float upperLimit) throws IOException {
		float input = new InputClass().getFloatInput();
		while (input <= lowerLimit || input >= upperLimit) {
			System.out.println("invalid input enter again");
			input = new InputClass().getFloatInput();
		}
		return input;
	}

	public long getLongInputInRange(long lowerLimit, long upperLimit) throws IOException {
		long input = new InputClass().getLongInput();
		while (input <= lowerLimit || input >= upperLimit) {
			System.out.println("invalid input enter again");
			input = new InputClass().getLongInput();
		}
		return input;

	}

}
