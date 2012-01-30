package common;

import global.Global;

import java.awt.Graphics;
import java.awt.Rectangle;

import resources.Graphic;

public class ActiveObject 
{	
	// motion-related physics
	public Motion motion;
	public Collision collision;
	
	public int width;
	public int height;
	
	public boolean alive;
	
	public Graphic img;
	
	public ActiveObject()
	{
		alive = true;
		
		motion = new Motion();
		
		motion.setPosition(0, 0);
		motion.setVelocity(10, 0);
		motion.setAcceleration(0, 0);
		motion.setFriction(0, 0);
		
		motion.setBoundary(new Rectangle(0, 0, Global.ScaleValue(Global.SCREEN_WIDTH), Global.ScaleValue(Global.SCREEN_HEIGHT)));
		
		collision = new Collision(this);
	}
	
	public void update()
	{	
		motion.move();
	}
	
	public void paint(Graphics g)
	{
		if (Global.SHOW_BOUNDING_BOXES)
		{
			collision.paint(g);
		}
	}
}