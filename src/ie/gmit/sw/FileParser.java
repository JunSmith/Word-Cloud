package ie.gmit.sw;

import java.io.*;
import java.util.*;

public class FileParser 
{
	// This class will read the file to convert into a map(word, frequency);
	// Will likely need a method with parameter of file location and return hashmap as above
	
	private HashMap<String, Integer> wordMap;
	private List<String> stopWords;
	
	public FileParser()
	{
		wordMap = new HashMap<String, Integer>();
		stopWords = new LinkedList<String>();
	}
	
	public void createStopList(String file) throws Exception
	{
		System.out.println("Creating stopWord list...");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		
		while((line = br.readLine()) != null)		
			stopWords.add(line);		
		
		System.out.println("StopWord list created");
		br.close();
	}
	
	public void parseFile(String file) throws FileNotFoundException, IOException
	{
		System.out.println("Creating map of words from file");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		List<String> wordList = new ArrayList<String>();
		
		while((line = br.readLine()) != null)
		{
			line = line.replaceAll("[^a-zA-Z]", " ").toLowerCase();
			String[] lineItems = line.split(" ");
			List<String> temp = Arrays.asList(lineItems);
			wordList.addAll(temp);
		}
		
		br.close();
		
		System.out.println("Word map created");		
		fillMap(wordList);
	}
	
	public void parseURL(String url)
	{
		System.out.println("Creating map of words from URL");
		String[] words = url.split("\\W");
		
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
	
	public HashMap<String, Integer> getWordMap()
	{
		return wordMap;
	}
}
