package verchaska.practise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SumOfUnique {

	public static void main(String[] args) throws IOException {
		int element = 0;
		System.out.println("How many numbers do you want:");
		int noOfElements = new InputClass().getNaturalNumberInput();
		ArrayList<Integer> elementsArr = new ArrayList<Integer>();
		for (int i = 0; i < noOfElements; i++) {
			System.out.println("enter the element:");
			element = new InputClass().getIntegerInput();
			elementsArr.add(element);

		}
		HashMap<Integer, Integer> elementFrequency = new SumOfUnique().generateElementFrequency(elementsArr);
		int sumOfUnique = 0;

		for (int key : elementFrequency.keySet()) {
			if (elementFrequency.get(key) == 1) {
				sumOfUnique = sumOfUnique + key;
			}
		}
		System.out.println("Sum of unique elements is " + sumOfUnique);

	}

	private HashMap<Integer, Integer> generateElementFrequency(ArrayList<Integer> elementsArr) {
		HashMap<Integer, Integer> frequency = new HashMap<Integer, Integer>();
		for (int i = 0; i < elementsArr.size(); i++) {
			if (!frequency.containsKey(elementsArr.get(i))) {
				frequency.put(elementsArr.get(i), 1);
			} else {
				frequency.put(elementsArr.get(i), frequency.get(elementsArr.get(i)) + 1);
			}
		}

		return frequency;
	}

}
