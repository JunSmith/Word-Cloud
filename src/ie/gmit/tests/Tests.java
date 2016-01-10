package ie.gmit.tests;

import org.junit.*;

import ie.gmit.sw.*;

public class Tests 
{
	private WordCloudFactory wcg;
	
	@Before
	public void create()
	{
		wcg = new WordCloudFactory();
	}
	
	@After
	public void destroy()
	{
		wcg = null;
	}
	
	@Test
	public void validFile() throws Exception
	{
		wcg.makeCloud("./res.txt", "./stopwords.txt", true);
	}
	
	@Test
	public void invalidFile() throws Exception
	{
		wcg.makeCloud("./rest.txt", "./stopwords.txt", true);
	}
	
	@Test
	public void validUrl() throws Exception
	{
		wcg.makeCloud("http://www.hello.this.is.a.test.url.com/", "./stopwords.txt", false);
	}
	
	@Test
	public void invalidUrl() throws Exception
	{
		wcg.makeCloud("http://w.hello.this.is.a.test.url.com/", "./stopwords.txt", false);
	}
	
	@Test
	public void validStopFile() throws Exception
	{
		wcg.makeCloud("./res.txt", "./sopwords.txt", true);
	}
}
