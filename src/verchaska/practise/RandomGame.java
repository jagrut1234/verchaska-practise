package verchaska.practise;

import java.io.IOException;
import java.util.Random;

public class RandomGame {

	public static void main(String args[]) throws IOException {
		Random rand = new Random();
		int randomNumber = rand.nextInt(50);
		System.out.println("generated random number is " + randomNumber);

		int guessedNumber = 0;
		while (true) {
			System.out.println("guess the number");
			guessedNumber = new InputClass().getWholeNumberInput();

			if (guessedNumber == randomNumber) {
				System.out.println("congratulations guessed output is correct");
				break;
			}

			if (guessedNumber > randomNumber) {
				System.out.println("output is greater than correct value");
			} else {
				System.out.println("output is lesser than correct value");
			}

		}

	}

}
