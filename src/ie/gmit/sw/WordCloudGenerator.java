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
	private int direction;
	
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
		Rectangle2D rect;
		
		graphics.setFont(font);
		graphics.setColor(color);
		
		rect = getStringBounds(graphics, word, stringW, stringH);
		while(ovc.checkOverlap(rect))
		{
			move(direction, rect);
			rect = getStringBounds(graphics, word, stringW, stringH);
		}
		ovc.addRectangle(rect);

		graphics.drawString(word, stringW, stringH);
	}
	
	private void move(int direction, Rectangle2D rect)
	{		
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
		FontRenderContext frc = g.getFontRenderContext();
		GlyphVector gv = g.getFont().createGlyphVector(frc, word);
		return gv.getPixelBounds(null, x, y);
	}
	
	public void createCloud(HashMap<String, Integer> words) throws IOException
	{		
		int i = 0;
		direction = 0;
		
		for(Map.Entry<String, Integer> word : words.entrySet())
		{
			fontManager.setFontSize(word.getValue());
			fontManager.setFont();
			drawWord(fontManager.getFont(), fontManager.getRandomColor(), word.getKey(), direction);
			
			if(i % 2 == 0)
			changeDirection();
			
			if(i > 90)
				break;
			i++;
		}		
		finalizeDrawing();
	}
	
	private void changeDirection()
	{
		if(direction > 2)
			direction = 0;
		else
			direction++;
	}
	
	private void resetPosition()
	{
		stringW = imageW/2;
		stringH = imageH/2;
		changeDirection();
	}
	
	public void finalizeDrawing() throws IOException
	{
		graphics.dispose();
		ImageIO.write(image, "png", new File("cloud.png"));
		System.out.println("Finished drawing cloud");
	}	
}
