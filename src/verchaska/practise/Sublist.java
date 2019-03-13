package verchaska.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Sublist {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> languages = new ArrayList<String>();
		String languagesInList = null;
		System.out.println("How many languages do you want to enter:");
		int noOfLanguages = new InputClass(). getNaturalNumberInput();
		for (int i = 0; i < noOfLanguages; i++) {
			System.out.println("Enter a language:");
			languagesInList = br.readLine();
			languages.add(languagesInList);
		}

		System.out.println("Enter number of sublists to be created");
		int noOfSubLists = new Sublist().getNoOfSublists(noOfLanguages);
		int sizeOfSublist = (noOfLanguages - (noOfLanguages % noOfSubLists)) / (noOfSubLists);

		if (noOfSubLists == 1) {
			System.out.println("sublist 1 " + languages);
		} else {
			int k = 1;
			for (int i = 0; i < noOfLanguages; i = i + sizeOfSublist) {
				if (i + sizeOfSublist < (noOfLanguages - (noOfLanguages % noOfSubLists))) {
					System.out.println("sublist " + (k++) + "" + languages.subList(i, i + sizeOfSublist));
				}else
				{
					System.out.println("sublist " + (k++) + "" + languages.subList(i, noOfLanguages));
					break;
				}
			}
		}

	}

	private int getNoOfSublists(int noOfLanguages) throws IOException {
		int noOfSublists = new InputClass(). getNaturalNumberInput();
		while (noOfSublists > noOfLanguages) {
			System.out.println("no of sublists cannot be greater than no of languages enter again");
			 noOfSublists = new InputClass(). getNaturalNumberInput();
		}
		return noOfSublists;
	}

}
