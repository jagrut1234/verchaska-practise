package verchaska.practise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RepeatedCharactersPractise {

	public static void main(String[] args) throws IOException {
		String maxRepeatedAlphabet = null;
		String input = new InputClass().getStringInput();
		input = input.toLowerCase();
		HashMap<String, Integer> freqOfStringAlphabets = new RepeatedCharactersPractise()
				.generateElementFrequency(input);
		HashMap<String, Integer> sortedFreqOfStringAlphabets = new RepeatedCharactersPractise()
				.getSortedHashMapValues(freqOfStringAlphabets);
		System.out.println(sortedFreqOfStringAlphabets);
		maxRepeatedAlphabet = new RepeatedCharactersPractise().getChracterAtTop(sortedFreqOfStringAlphabets);
		if (new RepeatedCharactersPractise().checkForSameMaxFrequency(sortedFreqOfStringAlphabets,
				sortedFreqOfStringAlphabets.get(maxRepeatedAlphabet)) == 1) {
			System.out.println("max repeated alphabet is "+maxRepeatedAlphabet);
		} else {
			ArrayList<String> alphabetsWithMaxFrequency=new RepeatedCharactersPractise().getAlphabetsWithMaxFrequency(maxRepeatedAlphabet,sortedFreqOfStringAlphabets);
			Collections.sort(alphabetsWithMaxFrequency);
			System.out.println("max repeated alphabet is "+alphabetsWithMaxFrequency.get(0));
		}

	}

	private ArrayList<String> getAlphabetsWithMaxFrequency(String maxRepeatedAlphabet,
			HashMap<String, Integer> sortedFreqOfStringAlphabets) {
		
		ArrayList<String> alphabetsWithMaxFrequency=new ArrayList<>();
		for(Entry<String,Integer> iterator:sortedFreqOfStringAlphabets.entrySet())
		{
			if(iterator.getValue()==sortedFreqOfStringAlphabets.get(maxRepeatedAlphabet))
			{
				alphabetsWithMaxFrequency.add(iterator.getKey());
			}
		}
		return alphabetsWithMaxFrequency;
	}

	private int checkForSameMaxFrequency(HashMap<String, Integer> sortedFreqOfStringAlphabets, Integer maxFrequency) {
		int counter = 0;
		for (Entry<String, Integer> iterator : sortedFreqOfStringAlphabets.entrySet()) {
			if (iterator.getValue() == maxFrequency)
				counter++;
		}

		return counter;
	}

	private String getChracterAtTop(HashMap<String, Integer> sortedFreqOfStringAlphabets) {
		String maxRepeatedAlphabet = null;
		for (Entry<String, Integer> iterator : sortedFreqOfStringAlphabets.entrySet()) {
			maxRepeatedAlphabet = iterator.getKey();
			break;
		}
		return maxRepeatedAlphabet;
	}

	private HashMap<String, Integer> getSortedHashMapValues(HashMap<String, Integer> freqOfStringAlphabets) {
		List<Map.Entry<String, Integer>> listOfValues = new LinkedList<Map.Entry<String, Integer>>(
				freqOfStringAlphabets.entrySet());

		Collections.sort(listOfValues, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return -((o1.getValue())-(o2.getValue()));
			}
		});

		HashMap<String, Integer> sortedHashMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> iterator : listOfValues) {
			sortedHashMap.put(iterator.getKey(), iterator.getValue());
		}
		return sortedHashMap;

	}

	private HashMap<String, Integer> generateElementFrequency(String inputString) {
		HashMap<String, Integer> frequency = new HashMap<String, Integer>();
		for (int i = 0; i < inputString.length(); i++) {
			if (!frequency.containsKey(inputString.substring(i, i + 1))) {
				frequency.put(inputString.substring(i, i + 1), 1);
			} else {
				frequency.put(inputString.substring(i, i + 1), frequency.get(inputString.substring(i, i + 1)) + 1);
			}
		}

		return frequency;
	}

}
