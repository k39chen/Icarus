package gliderGame;

import global.Global;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import resources.Graphic;
import resources.GraphicsCache;

import common.ActiveObject;

public class Glider extends ActiveObject
{
	public double distance;
	public double speed;
	
	public int width;
	public int height;
	
	boolean alive;
	
	Graphic img;
	
	public Glider()
	{
		super();
		
		distance = 0;
		speed = 1.01;
	
		alive = true;
		
		img = GraphicsCache.GetInstance().loadGraphic("assets/glider.png");
		
		width = Global.ScaleValue(img.getWidth());
		height = Global.ScaleValue(img.getHeight());
		
		motion.setPosition(
				(Global.ScaleValue(Global.SCREEN_WIDTH) - width)/2, 
				(Global.ScaleValue(Global.SCREEN_HEIGHT) - height)/2);
		
		motion.setVelocity(0, Global.ScaleValue(4));
		motion.setAcceleration(0, 0);
		motion.setFriction(1, 1);
	
		motion.setBoundary(new Rectangle(0, 0, 
				Global.ScaleValue(Global.SCREEN_WIDTH), 
				Global.ScaleValue(Global.SCREEN_HEIGHT)));
		
		collision.SetRectangle(new Rectangle((int)(-width / (Global.SCALE * 2)), (int)(-height / (Global.SCALE * 2)), width, height));
	}
	
	public void update()
	{	
		if (alive)
		{	
			distance += speed;
			
			motion.move();
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
		img.paint(g, motion.pos.x - img.getWidth()/2, motion.pos.y - img.getHeight()/2);
		
		super.paint(g);
	}
}