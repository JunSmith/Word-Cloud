package ie.gmit.sw;

public class WordCloudFactory 
{
	private int width = 1000;
	private int height = 1000;
	
	public void makeCloud(String input, String stopWords, boolean isFile) throws Exception
	{		
		StopWordsParser swp = new StopWordsParser();
		WordCloudGenerator wcg = new WordCloudGenerator(width, height);	
		
		swp.createFile(stopWords);
		ParsableHashMap parsable;
		
		if(isFile)		
			parsable = new TextFileParser();		
		else		
			parsable = new UrlParser();
		parsable.createFile(input);
		wcg.createCloud(parsable.getHashMap());		
	}
}
