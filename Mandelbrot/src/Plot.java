import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Plot {
	
	public static double xCenter;
	public static double yCenter;
	public static double window;
	
	public void render(Graphics g) 
	{
		graph(g);
		setGrid(g);
	}
	
	/*
	 *This method is so that I can estimate convergence to any number of iterations without
	 *throwing a stack overflow error in the convergence method of Calculate class
	*/
	public boolean converges(double x, double y)
	{		
		for(int block = 0; block < 1; block++)
		{
			if(!Calculate.convergence(Calculate.blockReal, Calculate.blockComplex, x, y))
			{	
				Calculate.blockReal = 0;
		        Calculate.blockComplex = 0;
		        
		        //diverges
				return false;
			}
		}
		
		//converges
		return true;
	}
	
	public void graph(Graphics g)
	{
		double a = 0;
		double b = 0;
		
		for(double x = -window+xCenter; x < window+xCenter; x += window/500) 
		{
			for(double y = -window+yCenter; y < window+yCenter; y += window/500)
			{				
				if(converges(x, y))
				{
					a = (x-xCenter) * 500/window + 500;
					b = (y-yCenter) * -500/window + 500;
							
					g.setColor(Color.white);
					
			        g.fillRect((int)a, (int)b, 2, 2);
			        
			        Calculate.blockReal = 0;
			        Calculate.blockComplex = 0;
				}
				else 
				{
					a = (x-xCenter) * 500/window + 500;
					b = (y-yCenter) * -500/window + 500;
					
					Color color = new Color(Calculate.c, Calculate.c, Calculate.c);
					g.setColor(color);
					
					g.fillRect((int)a, (int)b, 2, 2);
			        
				}
			}
		}
		
		
	}
	
	//sets grid
	public void setGrid(Graphics g) 
    {
    	 g.setColor(Color.black);
	      
	        for(int i = 0; i < 1000; i++) 
	        {
	 	        Rectangle r = new Rectangle(i,500,10,2);
	 	        i = i+20;
	 	        g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
	        }
	        
	        for(int i = 0; i < 1000; i++)
	        { 
	 	        Rectangle r = new Rectangle(500,i,2,10);
	 	        i = i+20;
	 	        g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
	        }
    }
	
}
