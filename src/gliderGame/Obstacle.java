package gliderGame;

import global.Global;

import java.awt.Graphics;
import java.awt.Rectangle;

import resources.Graphic;
import resources.GraphicsCache;
import common.ActiveObject;

public class Obstacle extends ActiveObject
{
	public Graphic img;
	
	public Obstacle()
	{
		super();
		
		img = GraphicsCache.GetInstance().loadGraphic("assets/rock.png");
		
		width = img.getWidth();
		height = img.getHeight();
	
		motion.setPosition((Global.ScreenWidth() - width)/2, /*(Global.ScreenHeight() - height)/2*/0);
		motion.setVelocity(0, 0);
		motion.setAcceleration(0, 0);
		motion.setFriction(1, 1);
		
		collision.SetRectangle(new Rectangle(0, 0, width, height));
	}
	
	public void update()
	{
		super.update();
	}
	
	public void stop()
	{
		motion.stop();
	}
	
	public void paint(Graphics g)
	{
		img.paint(g, motion.pos.x, motion.pos.y);
		
		super.paint(g);
	}
}