package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TextFileParser implements ParsableHashMap
{
	private HashMap<String, Integer> wordMap;
	
	public TextFileParser()
	{
		wordMap = new HashMap<String, Integer>();
	}
	
	public void createFile(String file) throws Exception 
	{
		System.out.println("Creating map of words from file");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		StopWordsParser swp = new StopWordsParser();
		String line;
		List<String> stopList = swp.getList();
		List<String> wordList = new ArrayList<String>();
		
		while((line = br.readLine()) != null)
		{
			line = line.replaceAll("[^a-zA-Z]", " ").toLowerCase();
			String[] lineItems = line.split(" ");
			List<String> temp = Arrays.asList(lineItems);
			
			wordList.addAll(temp);
		}
		
		br.close();		
		
		for(String item : wordList)		
			if(stopList.contains(item))
				wordList.remove(item);
		
		
		System.out.println("Word map created");		
		fillMap(wordList);
	}
	
	private void fillMap(List<String> wordList)
	{
		for(String word : wordList)				
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
	
	public HashMap<String, Integer> getHashMap()
	{
		return wordMap;
	}
}
