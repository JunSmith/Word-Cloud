package ie.gmit.sw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class WordCloudGenerator
{	
	private FontManager fontManager;
	private OverlapChecker ovc;
	private BufferedImage image;
	private Graphics2D graphics;
	private int imageW;
	private int imageH;	
	private int stringW;
	private int stringH;	
	private int direction; // Value ranges from 0 to 3, each being a cardinal direction (down, right, up, left)
	
	public WordCloudGenerator(int width, int height)
	{
		super();
		image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		BackgroundGenerator bgg = new BackgroundGenerator();		
		graphics = (Graphics2D) image.getGraphics();
		bgg.generateBackground(width, height, graphics);
		this.imageW = width;
		this.imageH = height;
		this.stringW = width/2;
		this.stringH = height/2;
		ovc = new OverlapChecker();
		fontManager = new FontManager();
	}
	
	public void drawWord(Font font, Color color, String word, int direction)
	{
		// Method executed on every String entry in created list of words to be inserted into image.
		// the while loop will keep executing until the retyLimit counter runs out, where the string will not be drawn. 
		// This is caused by the algorithm being unable to find a suitable position.
		// If it is able to find a space, the rectangle used to try and find an empty space is saved in a list of taken rectangle areas.
		
		Rectangle2D rect;
		int retryLimit = 200;
		
		graphics.setFont(font);
		graphics.setColor(color);
		
		rect = getStringBounds(graphics, word, stringW, stringH);
		while(ovc.checkOverlap(rect))
		{
			retryLimit--;
			move(direction, rect);
			rect = getStringBounds(graphics, word, stringW, stringH);
			if(retryLimit < 0)
				break;
		}
		
		if(retryLimit > 0)
		{
			ovc.addRectangle(rect);	
			graphics.drawString(word, stringW, stringH);
		}
	}
	
	private void move(int direction, Rectangle2D rect)
	{		
		// Method to move the rect by a single pixel, direction determined by current value of direction int.
		// If the word would render outside of image bounds, resetPosition is executed.
		
		switch(direction)
		{
			case 0:
				if(!(rect.getY() < 0))
					stringH--;				
				else
					resetPosition();
				break;
				
			case 1:
				if(!(rect.getX() + rect.getWidth() > imageW))		
					stringW++;				
				else
					resetPosition();
				break;
				
			case 2:
				if(!(rect.getY() + rect.getHeight() > imageH))		
					stringH++;				
				else
					resetPosition();
				break;
				
			case 3:
				if(!(rect.getX() < 0))				
					stringW--;				
				else
					resetPosition();
				break;	
		}		
	}
	
	private Rectangle getStringBounds(Graphics2D g, String word, int x, int y)
	{
		// Gets accurate area of rectangle.
		
		FontRenderContext frc = g.getFontRenderContext();
		GlyphVector gv = g.getFont().createGlyphVector(frc, word);
		return gv.getPixelBounds(null, x, y);
	}
	
	public void createCloud(HashMap<String, Integer> words) throws IOException
	{		
		// De facto "main" method of this class. 
		// Loops through map of words to try and place each in image.
		
		int i = 0;
		direction = 0;
		
		for(Map.Entry<String, Integer> word : words.entrySet())
		{
			fontManager.setFontSize(word.getValue());
			fontManager.setFont();
			drawWord(fontManager.getFont(), fontManager.getRandomColor(), word.getKey(), direction);
			
			if(i % 2 == 0)
			changeDirection();
		}		
		finalizeDrawing();
	}
	
	private void changeDirection()
	{
		// Increments value of direction unless it's 3, where it's set to 0.
		
		if(direction > 2)
			direction = 0;
		else
			direction++;
	}
	
	private void resetPosition()
	{
		// Resets position of rectangle to center of image to run through placement algorithm again.
		
		stringW = imageW/2;
		stringH = imageH/2;
		changeDirection();
	}
	
	public void finalizeDrawing() throws IOException
	{		
		// Saves all drawn strings to image.
		
		graphics.dispose();
		ImageIO.write(image, "png", new File("cloud.png"));
		System.out.println("Finished drawing cloud");
	}
}
