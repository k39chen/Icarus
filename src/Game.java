import gliderGame.GliderGame;
import global.Global;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

import resources.FontMgr;
import resources.GraphicMgr;

public class Game 
	extends Applet 
	implements Runnable, KeyListener, MouseListener, MouseMotionListener
{	
	/**
	 * To silence the compiler
	 */
	private static final long serialVersionUID = 1L;
	
	// double-buffering
	private Image dbImage;
	private Graphics dbGraphics;
	
	// application classes
	GliderGame gliderGame;
	
	public AudioClip sfx;
	public AudioClip song;
	
	/*
	 * TODO
	 * - Powerups (like slime volleyball Science news for kids kind of depletion mechanic)
	 */
	
	@Override
	public void init() 
	{
		Global.GameApplet = this;
		
		setSize(Global.ScaleValue(Global.SCREEN_WIDTH), Global.ScaleValue(Global.SCREEN_HEIGHT));
		
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		Global.CODE_BASE = getCodeBase();
		
		// instantiate managers
		new GraphicMgr();
		new FontMgr();
		
		//sfx = getAudioClip(this.getCodeBase(), "assets/sfx/sample.wav");
		//sfx.loop();
		
		//song = getAudioClip(this.getCodeBase(), "assets/mus/giveitup.mid");
		//song.loop();
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
			Global.gliderGame.setSpeedBoostLevel(
					(Global.gliderGame.STAGE_SPEED_BOOST_LEVEL == GliderGame.STAGE_SPEED_BOOST_LEVELS.length - 1)
						? Global.gliderGame.STAGE_SPEED_BOOST_LEVEL
						: Global.gliderGame.STAGE_SPEED_BOOST_LEVEL + 1); 
			break;
		case KeyEvent.VK_DOWN:
			Global.gliderGame.setSpeedBoostLevel(
				(Global.gliderGame.STAGE_SPEED_BOOST_LEVEL == 0)
					? Global.gliderGame.STAGE_SPEED_BOOST_LEVEL
					: Global.gliderGame.STAGE_SPEED_BOOST_LEVEL - 1); 
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
			break;
		default:
			break;
		}
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