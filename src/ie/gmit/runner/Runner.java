package ie.gmit.runner;

import ie.gmit.sw.*;

public class Runner 
{
	public static void main(String[] args) throws Exception 
	{
		String stopWords = "stopwords.txt";
		
		WordCloudFactory factory = new WordCloudFactory();
		factory.makeCloud(stopWords);
	}
	
	
}
