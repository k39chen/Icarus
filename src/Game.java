import gliderGame.GliderGame;
import global.Global;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class Game extends Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener
{
	// double-buffering
	private Image dbImage;
	private Graphics dbGraphics;
	
	// application classes
	GliderGame gliderGame;
	
	@Override
	public void init() 
	{
		setSize(Global.ScaleValue(Global.SCREEN_WIDTH), Global.ScaleValue(Global.SCREEN_HEIGHT));
		
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		Global.CODE_BASE = getCodeBase();
	}
	
	@Override
	public void start() 
	{
		Thread thread = new Thread(this);
		thread.start();

		gliderGame = new GliderGame();
	}
	
	@Override
	public void run() 
	{
		while (true)
		{	
			if (gliderGame != null)
			{
				gliderGame.update();
			}
			
			repaint();
			
			try 
			{
				Thread.sleep(1000/Global.FRAMES_PER_SECOND);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void paint(Graphics g) 
	{
		gliderGame.paint(g);
	}
	
	@Override
	public void stop()
	{
	}
	
	@Override
	public void destroy() 
	{
	}
	
	@Override
	public void update(Graphics g) 
	{
		if (dbImage == null)
		{
			dbImage = createImage(Global.ScaleValue(Global.SCREEN_WIDTH), Global.ScaleValue(Global.SCREEN_HEIGHT));
			dbGraphics = dbImage.getGraphics();
		}
		
		dbGraphics.setColor(getBackground());
		dbGraphics.fillRect(0, 0, Global.ScaleValue(Global.SCREEN_WIDTH), Global.ScaleValue(Global.SCREEN_HEIGHT));
		
		dbGraphics.setColor(getForeground());
		paint(dbGraphics);
		
		g.drawImage(dbImage, 0, 0, this);
	}

	/*********************************************
	 * HANDLE KEYBOARD INPUT
	 *********************************************/
	public void keyPressed(KeyEvent e) 
	{
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			break;
		case KeyEvent.VK_RIGHT:
			break;
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_DOWN:
			break;
		case KeyEvent.VK_SPACE:
			gliderGame = new GliderGame();
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}
	
	/*********************************************
	 * HANDLE MOUSE INPUT
	 *********************************************/
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		e.consume();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		e.consume();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{	
		gliderGame.glider.moveUp();
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		gliderGame.glider.moveDown();
	}
}