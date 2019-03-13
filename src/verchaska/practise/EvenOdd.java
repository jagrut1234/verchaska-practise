package verchaska.practise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class EvenOdd {

	public static void main(String[] args) throws IOException {
		int element=0;
		ArrayList<Integer> inputValues=new ArrayList<Integer>();
		ArrayList<Integer> evenOdd=new ArrayList<Integer>();
		System.out.println("How many values do you want to enter:");
		int noOfValues=new InputClass().getNaturalNumberInput();
		
		for(int i=0;i<noOfValues;i++)
		{
			System.out.println("Enter the element:");
			element=new InputClass().getNaturalNumberInput();
			inputValues.add(element);
		}
		Collections.sort(inputValues);
		int oddIndex=0;
		for(int i=0;i<inputValues.size();i++)
		{
			
			if(inputValues.get(i)%2!=0)
			{
				evenOdd.add(oddIndex++,inputValues.get(i));
			}else
			{
				evenOdd.add(inputValues.get(i));
			}
		}
		
		System.out.println("sorted data is "+evenOdd);
		
		
	}

}
