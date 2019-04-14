import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Scanner;

public class Main extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 2L;

    public static final int WIDTH = 1000, HEIGHT = 1050;
    private Thread thread;
    private boolean running = false;
    
    Scanner sc = new Scanner(System.in);
    Plot plot = new Plot();
    
    public Main()
    {
        this.addKeyListener(new KeyInput());
        
    	Plot.window = 1.5;
    	Plot.xCenter = -.5;
    	Plot.yCenter = 0;
    	
    	new Window(WIDTH, HEIGHT, "Mandelbrot", this);
    	
    }
    
    
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //the tick method
    public void run()
    {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                //System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
    }
    
    public void tick() 
    {
    	
    }
    
    //renders visuals
    public void render() 
    {
    	BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        //actual code starts here
        
        plot.render(g);
        
        //ends code
        g.dispose();
        bs.show();
    }
    
    public static void main(String[] args) 
    {
    	new Main();
    }
    
}
