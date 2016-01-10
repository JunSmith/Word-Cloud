package ie.gmit.sw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UrlParser implements ParsableHashMap
{
	private HashMap<String, Integer> wordMap;
	
	public UrlParser()
	{
		wordMap = new HashMap<String, Integer>();
	}
	
	public void createFile(String file) 
	{
		System.out.println("Creating map of words from URL");
		String[] words = file.split("\\W");
		
		List<String> wordList = new ArrayList<String>();
		
		wordList = Arrays.asList(words);
		System.out.println("Word map created");
		fillMap(wordList);
	}
	private void fillMap(List<String> wordList) 
	{
		for(String word : wordList)		
		{
			if(word.length() > 1)
			{
				if(!wordMap.containsKey(word))				
					wordMap.put(word, 1);				
				
				else
				{
					int count = wordMap.get(word) + 1;
					wordMap.put(word, count);
				}			
			}	
		}
	}

	public HashMap<String, Integer> getHashMap()
	{
		return wordMap;
	}

}
