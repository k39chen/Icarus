package gliderGame;

import global.Global;

import java.awt.Graphics;
import java.awt.Rectangle;

import resources.GraphicsCache;

import common.ActiveObject;

public class Glider extends ActiveObject
{
	public double distance;
	public double speed;
	
	public Glider()
	{
		super();
		
		distance = 0;
		speed = 1.01;
		
		img = GraphicsCache.GetInstance().loadGraphic("assets/glider.png");
		
		width = img.getWidth();
		height = img.getHeight();
		
		motion.setPosition((Global.ScreenWidth() - width)/2, (Global.ScreenHeight() - height)/2);
		motion.setVelocity(0, Global.ScaleValue(4));
		motion.setAcceleration(0, 0);
		motion.setFriction(1, 1);
	
		motion.setBoundary(new Rectangle(0, 0, Global.ScreenWidth(), Global.ScreenHeight()));
		
		collision.SetRectangle(new Rectangle(0, 0, width, height));
	}
	
	public void update()
	{	
		if (alive)
		{	
			distance += speed;
			super.update();
		}
		else
		{
			motion.stop();
		}
	}
	
	public void moveUp()
	{
		motion.reverseY();
	}
	
	public void moveDown()
	{
		motion.reverseY();
	}
	
	public void paint(Graphics g)
	{	
		img.paint(g, motion.pos.x, motion.pos.y);
		
		super.paint(g);
	}
}