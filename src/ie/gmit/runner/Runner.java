package ie.gmit.runner;

import ie.gmit.sw.*;

public class Runner 
{
	public static void main(String[] args) throws Exception 
	{
		WordCloudFactory factory = new WordCloudFactory();
		
		if(args[2].equalsIgnoreCase("true"))
			factory.makeCloud(args[0], args[1], true);
		else
			factory.makeCloud(args[0], args[1], false);
	}	
}
