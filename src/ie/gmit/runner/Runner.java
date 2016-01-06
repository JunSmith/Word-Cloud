package ie.gmit.runner;

import ie.gmit.sw.*;

public class Runner 
{
	public static void main(String[] args) throws Exception 
	{
		boolean isFile = true;
		String file = "./WarAndPeace-Tolstoy.txt";
		String stopwords = "stopwords.txt";
		String url = "https://www.youtube.com/watch?v=qwT6Zj0YaaA&list=WL&index=16";
		
		WordCloudFactory factory = new WordCloudFactory();
		if(isFile)
			factory.makeCloud(file, stopwords, isFile);	
		else
			factory.makeCloud(url, stopwords, isFile);		
		
		// Probably should create a javaform for inputting desired text file/url, image size.
	}
}
