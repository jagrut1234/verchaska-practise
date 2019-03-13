package verchaska.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ExistsingNonExisting {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		System.out.println("Enter size of list 1:");
		int listOneSize = new InputClass(). getNaturalNumberInput();
		for (int i = 0; i < listOneSize; i++) {
			System.out.println("add element no " + (i + 1));
			list1.add(br.readLine());
		}
		System.out.println("Enter size of list 2:");
		int listTwoSize = new InputClass(). getNaturalNumberInput();
		for (int i = 0; i < listTwoSize; i++) {
			System.out.println("add element no " + (i + 1));
			list2.add(br.readLine());
		}

		new ExistsingNonExisting().checkExistingNonExisting(list1, list2);
	}

	private void checkExistingNonExisting(ArrayList<String> list1, ArrayList<String> list2) {
		ArrayList<String> existing = new ArrayList<String>();
		ArrayList<String> nonExisting = new ArrayList<String>();

		for (int i = 0; i < list1.size(); i++) {
			if (list2.contains(list1.get(i))) {
				if (!existing.contains(list1.get(i))) {
					existing.add(list1.get(i));
				}
			} else {
				if (!nonExisting.contains(list1.get(i))) {
					nonExisting.add(list1.get(i));
				}
			}
		}
		
		for (int i = 0; i < list2.size(); i++) {
			if (list1.contains(list2.get(i))) {
				if (!existing.contains(list2.get(i))) {
					existing.add(list2.get(i));
				}
			} else {
				if (!nonExisting.contains(list2.get(i))) {
					nonExisting.add(list2.get(i));
				}
			}
		}
		System.out.println("elements existing in both sets are "+existing);
		System.out.println("elements unique to both sets are "+nonExisting);
	}

}
