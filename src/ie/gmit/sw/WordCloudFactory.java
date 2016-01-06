package ie.gmit.sw;

public class WordCloudFactory 
{
	private int width = 1000;
	private int height = 1000;
	
	public void makeCloud(String data, String stopWords, boolean isFile) throws Exception
	{
		
		FileParser fp = new FileParser();
		WordCloudGenerator wcg = new WordCloudGenerator(width, height);		

		fp.createStopList(stopWords);
		
		if(isFile)
			fp.parseFile(data); //To parse a file.
		else
			fp.parseURL(data); // To parse a URL string.
		
		wcg.createCloud(fp.getWordMap());
	}
}
