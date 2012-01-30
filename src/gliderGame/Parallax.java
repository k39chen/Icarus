package gliderGame;

import global.Global;

import java.awt.Graphics;
import resources.GraphicsCache;

import common.ActiveObject;

public class Parallax extends ActiveObject
{	
	public int speedX;
	public int speedY;
	
	public Parallax(String url, int speed_x, int speed_y)
	{
		super();
		
		speedX = speed_x;
		speedY = speed_y;
		
		img = GraphicsCache.GetInstance().loadGraphic(url);
		
		width = img.getWidth();
		height = img.getHeight();
		
		motion.setPosition(0, Global.ScaleValue(Global.SCREEN_HEIGHT) - height);
		motion.setVelocity(Global.ScaleValue(speedX), Global.ScaleValue(speedY));
	}
	
	public void update()
	{	
		if(motion.pos.x + width <= 0)
		{
			motion.pos.x = 0;
		}
		
		motion.move();
	}
	
	public void stop()
	{
		motion.stop();
	}
	
	public void paint(Graphics g)
	{
		for (int i=0 ;; i++)
		{
			
			if(motion.pos.x + width * i < Global.ScaleValue(Global.SCREEN_WIDTH))
			{
				img.paint(g, motion.pos.x + width * i, motion.pos.y);
			}
			else
			{
				break;
			}
		}
		
		super.paint(g);
	}
}
