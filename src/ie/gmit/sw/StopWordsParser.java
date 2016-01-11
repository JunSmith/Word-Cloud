package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class StopWordsParser implements ParsableList
{
	private List<String> stopWords;	
	
	public StopWordsParser()
	{
		stopWords = new LinkedList<String>();
	}
	
	public void createFile(String file) throws Exception
	{
		// Reads and converts file of stopWords into list.
		
		System.out.println("Creating stopWord list...");
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file))))
		{
			String line;
			
			while((line = br.readLine()) != null)		
				stopWords.add(line);		
			
			System.out.println("StopWord list created");
		}
		
		catch(Exception e)
		{
			System.out.println("Stopwords file not found. Make sure it is spelled "
					+ "correctly and is in the same directory as the .jar");
		}
	}
	
	public List<String> getList()
	{
		return stopWords;
	}
}
