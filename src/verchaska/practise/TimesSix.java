package verchaska.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TimesSix {
	 static ArrayList<Integer> arr = new ArrayList<>();
	BufferedReader br = null;

	public static void main(String args[]) throws IOException {
		new TimesSix().setArraySize();


	}

	void setArraySize() throws IOException {

		int arraySize = 0;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter size of array");
			arraySize = Integer.parseInt(br.readLine());
			if(arraySize<0)
				throw new Exception();
			new TimesSix().appendArrayElements(arraySize, arr);
		} catch (Exception e) {
			System.out.println("invalid input");
			setArraySize();
		}
		

	}

	void appendArrayElements(int arraySize, ArrayList<Integer> arr) throws IOException {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("add array elements in integer form:");

			for (int i = 0; i < arraySize; i++) {
				arr.add(Integer.parseInt(br.readLine()));
			}
			
			new TimesSix().instancesOfSix(arr);
		} catch (Exception e) {
			System.out.println("invalid input");
			arr.clear();
			appendArrayElements(arraySize, arr);
			
		} 
		
		
	}

	void instancesOfSix(ArrayList<Integer> arr) {
		int repeatingSix = 0;
		if (arr.size() == 1)
			System.out.println("0");
		else {
			for (int i = 0; i < arr.size() - 1; i++) {
				if (arr.get(i) == 6 && (arr.get(i + 1) == 6 || arr.get(i + 1) == 7))
					repeatingSix++;
			}
		}
		System.out.println(repeatingSix);
	}
	
}
