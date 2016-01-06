package ie.gmit.sw;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class OverlapChecker 
{
	// Maintain list of rectangles.
	// Check if rectangle overlaps any other rectangle in list of rectangles.
	
	private List<Rectangle2D> rectList;
	
	public OverlapChecker()
	{
		rectList = new ArrayList<Rectangle2D>();
	}
	
	public boolean checkOverlap(Rectangle2D r)
	{
		for(Rectangle2D item : rectList)	
		{	
			if(item.intersects(r)) // EXTREMELY SLOW
				return true;		
			
			// r will have P1 and P2 while item will have P3 and P4
			
//			if(!(r.getY() - r.getHeight() < item.getY()) ||
//				r.getY() > item.getY() - item.getHeight() ||
//				r.getX() + r.getWidth() < item.getX() ||
//				r.getX() > item.getX() + item.getWidth())
//					return false;
			
//			if(r.getY() - r.getHeight() == item.getY() &&
//				r.getY() == item.getY() - item.getHeight() &&
//				r.getX() + r.getWidth() == item.getX() &&
//				r.getX() == item.getX() + item.getWidth())
//				return false;
		}
		
		return false;
	}
	
	public void addRectangle(Rectangle2D r)
	{
		rectList.add(r);
	}
	
	public List<Rectangle2D> getList()
	{
		return rectList;
	}
}
