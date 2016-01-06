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
		
		rect = getStringBounds(graphics, word, stringW, stringH); //This NEEDS to be here
		while(ovc.checkOverlap(rect))
		{
			move(direction, rect);
			rect = getStringBounds(graphics, word, stringW, stringH);
		}
		ovc.addRectangle(rect);

		graphics.drawString(word, stringW, stringH);
		
//		if((stringW + graphics.getFontMetrics().stringWidth(word)) > imageW)
//		{
//			stringW = 0;
//		}
//		else
//		{
//			stringW += graphics.getFontMetrics().stringWidth(word);
//		}
//		
//		if((stringH + graphics.getFontMetrics().getHeight()) > imageH)
//		{
//			stringH = 0;
//		}
//		else
//		{
//			stringH += graphics.getFontMetrics().getHeight();
//		}
	}
	
	private void move(int direction, Rectangle2D rect)
	{
		// Need to stop words hitting edges - use imageH and imageW.
		
		switch(direction)
		{
			case 0:
				if(!(stringH + rect.getHeight() > imageH))
					stringH++;
				break;
				
			case 1:
				if(!(stringW + rect.getWidth() > imageW))
					stringW++;
				break;
				
			case 2:
				if(!(stringH - rect.getHeight() < 0))
					stringH--;
				break;
				
			case 3:
				if(!(stringW - rect.getWidth() < 0))
					stringW--;
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
		int direction = 0;
		
		for(Map.Entry<String, Integer> word : words.entrySet())
		{
			fontManager.setFontSize(word.getValue());
			fontManager.setFont();
			drawWord(fontManager.getFont(), fontManager.getRandomColor(), word.getKey(), direction);
			
			if(direction == 3)
				direction = 0;
			else
				direction++;
			
			if(i > 50)
				break;
			i++;
		}
		
		finalizeDrawing();
	}
	
	public void finalizeDrawing() throws IOException
	{
		graphics.dispose();
		ImageIO.write(image, "png", new File("cloud.png"));
		System.out.println("Finished drawing cloud");
	}	
}
