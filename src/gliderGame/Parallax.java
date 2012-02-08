package gliderGame;

import global.Global;

import java.awt.Graphics;

import resources.GraphicMgr;

import common.ActiveObject;

public class Parallax extends ActiveObject
{	
	public int speedX;
	public int speedY;
	
	public Parallax(String id, int speed_x, int speed_y)
	{
		super();
		
		speedX = speed_x;
		speedY = speed_y;
		
		img = GraphicMgr.getGraphic(id);
		
		width = img.getWidth();
		height = img.getHeight();
		
		motion.setPosition(0, Global.ScreenHeight() - height);
		motion.setVelocity(Global.ScaleValue(speedX), Global.ScaleValue(speedY));
	}
	
	public void update()
	{	
		if (motion.vel.x != 0)
		{
			motion.vel.x = speedX * Global.gliderGame.STAGE_SPEED_BOOST * Global.gliderGame.STAGE_SPEED;
		}
		if(motion.pos.x + width <= 0)
		{
			motion.pos.x = 0;
		}
		motion.pos.x += motion.vel.x;
	}
	
	public void stop()
	{
		motion.stop();
	}

	public void paint(Graphics g)
	{
		for (int i=0 ;; i++)
		{
			if(motion.pos.x + width * i < Global.ScreenWidth())
			{
				img.paint(g, motion.pos.x + width * i, motion.pos.y);
			}
			else
			{
				break;
			}
		}
	}
}