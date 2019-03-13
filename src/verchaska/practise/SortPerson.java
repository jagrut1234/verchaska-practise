package verchaska.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import verchaska.practise.beans.PersonBean;

public class SortPerson {
	public static void main(String args[]) throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<PersonBean> arrayOfPersonObjects = new ArrayList<>();
		new SortPerson().performOperations(arrayOfPersonObjects);
		br.close();

	}

	private void performOperations(ArrayList<PersonBean> arrayOfPersonObjects) throws IOException, ParseException {
		int choiceOfOperation = 1;
		String sortingOrder = null;

		while (choiceOfOperation != 5) {
			System.out.println(
					"1)Add new Person Details.\n2)Sort Person List by name.\n3)Sort Person list by salary.\n4)Sort Person List by date of joining.\n5)Exit.\nEnter Your Choice.");
			choiceOfOperation = new InputClass().getNaturalNumberInput();
			while (choiceOfOperation > 5) {
				System.out.println("Invalid input Enter again");
				choiceOfOperation = new InputClass().getNaturalNumberInput();
			}

			switch (choiceOfOperation) {
			case 1:
				new SortPerson().addPerson(arrayOfPersonObjects);
				new SortPerson().displayArrayListData(arrayOfPersonObjects);
				break;
			case 2:
				sortingOrder = new SortPerson().getSortingOrder();
				new SortPerson().sortPersonByName(arrayOfPersonObjects, sortingOrder);
				new SortPerson().displayArrayListData(arrayOfPersonObjects);
				break;
			case 3:
				sortingOrder = new SortPerson().getSortingOrder();
				new SortPerson().sortPersonBySalary(arrayOfPersonObjects, sortingOrder);
				new SortPerson().displayArrayListData(arrayOfPersonObjects);
				break;
			case 4:
				sortingOrder = new SortPerson().getSortingOrder();
				new SortPerson().sortPersonByDateOfJoining(arrayOfPersonObjects, sortingOrder);
				new SortPerson().displayArrayListData(arrayOfPersonObjects);
				break;
			default:
				System.out.println("Thank you for visiting...");
				break;
			}
		}
	}

	private void displayArrayListData(ArrayList<PersonBean> arrayOfPersonObjects) {

		if (arrayOfPersonObjects.size() == 0) {
			System.out.println("\nNo entries present in the list\n");
		} else {
			System.out.println("\nEntries in list\n");
			for (int i = 0; i < arrayOfPersonObjects.size(); i++) {
				System.out.println(arrayOfPersonObjects.get(i));
			}
			System.out.println("_______________________________x__________________________________");
		}
	}

	private String getSortingOrder() throws IOException {
		System.out.println("1)Sort in ascending order.\n2)Sort in descending order.\nEnter your choice.");
		int choiceOfOrder = new InputClass().getNaturalNumberInput();
		while (choiceOfOrder > 2) {
			System.out.println("invalid input Enter again");
			choiceOfOrder = new InputClass().getNaturalNumberInput();
		}

		if (choiceOfOrder == 1)
			return "ascending";
		else
			return "descending";

	}

	private void sortPersonByDateOfJoining(ArrayList<PersonBean> arrayOfPersonObjects, String sortingOrder) {
		Collections.sort(arrayOfPersonObjects, new Comparator<PersonBean>() {
			public int compare(PersonBean o1, PersonBean o2) {
				int difference = o1.getDateOfJoining().compareTo(o2.getDateOfJoining());
				return "ascending".equalsIgnoreCase(sortingOrder) ? difference : -difference;
			}

		});

	}

	private void sortPersonBySalary(ArrayList<PersonBean> arrayOfPersonObjects, String sortingOrder) {
		Collections.sort(arrayOfPersonObjects, new Comparator<PersonBean>() {
			public int compare(PersonBean o1, PersonBean o2) {
				int difference = o1.getSalary() - o2.getSalary();
				return "ascending".equalsIgnoreCase(sortingOrder) ? difference : -difference;
			}

		});

	}

	private void sortPersonByName(ArrayList<PersonBean> arrayOfPersonObjects, String sortingOrder) {
		Collections.sort(arrayOfPersonObjects, new Comparator<PersonBean>() {
			public int compare(PersonBean o1, PersonBean o2) {
				int difference = o1.getName().compareTo(o2.getName());
				return "ascending".equalsIgnoreCase(sortingOrder) ? difference : -difference;
			}

		});

	}

	private void addPerson(ArrayList<PersonBean> arrayOfPersonObjects) throws IOException, ParseException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("how many entries do you want to make:");
		int noOfEntries = new InputClass().getNaturalNumberInput();
		String nameOfPerson = null;
		int salaryOfPerson = 0;
		Date dateOfJoingOfPerson = null;

		while (noOfEntries != 0) {
			System.out.println("Enter name of person:");
			nameOfPerson = br.readLine();
			System.out.println("Enter salary of person:");
			salaryOfPerson = new InputClass().getNaturalNumberInput();
			System.out.println("Enter date of joining of person in dd/mm/yyyy format:");
			dateOfJoingOfPerson = new DateInputClass().getDDMMYYYYInput();
			arrayOfPersonObjects.add(new PersonBean(nameOfPerson, salaryOfPerson, dateOfJoingOfPerson));
			noOfEntries--;
		}
	}

}
