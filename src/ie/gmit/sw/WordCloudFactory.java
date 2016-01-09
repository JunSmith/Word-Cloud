package ie.gmit.sw;

public class WordCloudFactory 
{
	private int width = 1000;
	private int height = 1000;
	
	public void makeCloud(String stopWords) throws Exception
	{		
		Display display = new Display();
		StopWordsParser swp = new StopWordsParser();
		WordCloudGenerator wcg = new WordCloudGenerator(width, height);		

		swp.createFile(stopWords);
		boolean isFile = display.displayInput();
		if(isFile)
		{
			TextFileParser fp = new TextFileParser();
			fp.createFile(display.getData()); //To parse a file.
			wcg.createCloud(fp.getWordMap());
		}
		else
		{
			UrlParser fp = new UrlParser();
			fp.createFile(display.getData()); // To parse a URL string.
			wcg.createCloud(fp.getWordMap());
		}
	}
}
