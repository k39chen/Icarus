package gliderGame;

import global.Global;

import java.awt.Graphics;
import java.awt.Rectangle;

import resources.GraphicsCache;
import common.ActiveObject;

public class Obstacle extends ActiveObject
{
	public int border_offset;
	
	public Obstacle()
	{
		super();
		
		img = GraphicsCache.GetInstance().loadGraphic("assets/rock.png");
		
		width = img.getWidth();
		height = img.getHeight();
		
		border_offset = Global.ScaleValue(32);
	
		motion.setPosition(Global.ScreenWidth(), (int)(Math.random() * (Global.ScreenHeight() - border_offset - height)));
		motion.setVelocity(Global.ScaleValue(-6), 0);
		motion.setAcceleration(0, 0);
		motion.setFriction(1, 1);
		
		motion.setBoundary(new Rectangle(-width, 0, Global.ScreenWidth(), Global.ScreenHeight()));
		
		collision.SetRectangle(new Rectangle(0, 0, width, height));
		
	}
	
	public void update()
	{	
		if (motion.hitLowerBoundaryX(0))
		{
			alive = false;
			stop();
		}
		
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