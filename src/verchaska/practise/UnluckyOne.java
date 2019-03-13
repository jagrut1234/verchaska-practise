package verchaska.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UnluckyOne {

	static ArrayList<Integer> arr = new ArrayList<>();
	BufferedReader br = null;

	public static void main(String args[]) throws IOException {
		new UnluckyOne().setArraySize();
		
		
	}

	void setArraySize() throws IOException {

		int arraySize = 0;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter size of array");
			arraySize = Integer.parseInt(br.readLine());
			if(arraySize<0)
				throw new NumberFormatException();
			new UnluckyOne().appendArrayElements(arraySize, arr);
		} catch (NumberFormatException e) {
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
			new UnluckyOne().checkUnluckyOne(arr);
		} catch (NumberFormatException e) {
			System.out.println("invalid input");
			arr.clear();
			appendArrayElements(arraySize, arr);
		} finally {
			br.close();
		}
	}
	
	void checkUnluckyOne(ArrayList<Integer> arr) throws IOException
	{
		boolean isUnlucky=false;
		if(arr.size()<2)
			System.out.println("false");
		else
		{
			for(int i=0;i<arr.size()-1;i++)
			{
				if((arr.get(i)==1&&arr.get(i+1)==3)&&(i<2||i>=arr.size()-2))
				{
					isUnlucky=true;
					break;
				}
				
			}
	
			System.out.println(isUnlucky);
		}
		
	}

}
