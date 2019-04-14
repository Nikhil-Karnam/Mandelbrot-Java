import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
    private boolean[] keyDown = new boolean[6];
    
	public KeyInput()
	{
		for(int i = 0; i < keyDown.length; i++) 
		{
			keyDown[i] = false;
		}
		
    }
	
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP){Plot.yCenter += .05; keyDown[0] = true;}
        if(key == KeyEvent.VK_DOWN){Plot.yCenter -= .05; keyDown[1] = true;}
        if(key == KeyEvent.VK_RIGHT){Plot.xCenter += .05; keyDown[2] = true;}
        if(key == KeyEvent.VK_LEFT){Plot.xCenter -= .05; keyDown[3] = true;}
        if(key == KeyEvent.VK_Z){Plot.window -= .05; keyDown[4] = true;}
        if(key == KeyEvent.VK_X){Plot.window += .05; keyDown[5] = true;}
        
        if(key == KeyEvent.VK_ESCAPE)
        {
        	System.exit(1);
    	}
    }
    
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_UP){keyDown[0] = false;}
        if(key == KeyEvent.VK_DOWN){keyDown[1] = false;}
        if(key == KeyEvent.VK_RIGHT){keyDown[2] = false;}
        if(key == KeyEvent.VK_LEFT){keyDown[3] = false;}
        if(key == KeyEvent.VK_Z){keyDown[4] = false;}
        if(key == KeyEvent.VK_X){keyDown[5] = false;}
    }
    
}