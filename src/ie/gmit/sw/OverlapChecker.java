package ie.gmit.sw;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class OverlapChecker 
{	
	private List<Rectangle2D> rectList;
	
	public OverlapChecker()
	{
		rectList = new ArrayList<Rectangle2D>();
	}
	
	public boolean checkOverlap(Rectangle2D r)
	{
		for(Rectangle2D item : rectList)			
			if(item.intersects(r))
				return true;		
		
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
