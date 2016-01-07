package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class StopWordsParser implements Parsable
{
	private List<String> stopWords;	
	
	public StopWordsParser()
	{
		stopWords = new LinkedList<String>();
	}
	
	public void createFile(String file) throws Exception
	{
		System.out.println("Creating stopWord list...");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		
		while((line = br.readLine()) != null)		
			stopWords.add(line);		
		
		System.out.println("StopWord list created");
		br.close();
	}
	
	public List<String> getList()
	{
		return stopWords;
	}
}
