package ie.gmit.sw;

public class WordCloudFactory 
{
	private int width = 1000;
	private int height = 1000;
	
	public void makeCloud(String data, String stopWords, boolean isFile) throws Exception
	{		
		StopWordsParser swp = new StopWordsParser();
		WordCloudGenerator wcg = new WordCloudGenerator(width, height);		

		swp.createFile(stopWords);
		
		if(isFile)
		{
			TextFileParser fp = new TextFileParser();
			fp.createFile(data); //To parse a file.
			wcg.createCloud(fp.getWordMap());
		}
		else
		{
			UrlParser fp = new UrlParser();
			fp.createFile(data); // To parse a URL string.
			wcg.createCloud(fp.getWordMap());
		}
	}
}
