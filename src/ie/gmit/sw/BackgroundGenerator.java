package ie.gmit.sw;

import java.awt.Color;
import java.awt.Graphics;

public class BackgroundGenerator 
{
	public void generateBackground(int width, int height, Graphics graphics)
	{
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, width, height);		
	}
}
