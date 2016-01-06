package ie.gmit.sw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class FontManager 
{
	private Font font;
	private int fontSize;
	
	public void setFont(String name, int style, int size)
	{
		font = new Font(name, style, size);
	}

	public void setFont() 
	{
		setFont(randomFont(), Font.PLAIN, getFontSize());		
	}
	
	public void setFontSize(int size)
	{
		fontSize = (int)(10 + size);
		if(fontSize > 40)
			fontSize = 40;
	}
	
	public int getFontSize()
	{
		return fontSize;
	}
	
	public Font getFont()
	{
		return font;
	}

	public Color getRandomColor()
	{		
		return new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
	}
	
	private String randomFont()
	{
		Random random = new Random();
		
		switch(random.nextInt(3))
		{
		case 0:
			return Font.SANS_SERIF;
			
		case 1:
			return Font.MONOSPACED;
			
		case 2:
			return Font.SERIF;
			
		default:
			return Font.SANS_SERIF;
		}		
	}
}
