package sandbox;

import java.awt.Color;
import java.awt.Graphics;

import global.Global;
import common.ActiveObject;

public class Ball extends ActiveObject
{
	public int radius;
	
	public Ball()
	{
		super();
		
		radius = 20;
		
		int pos_x = (int)(Math.random() * Global.SCREEN_WIDTH) + radius;
		int pos_y = 0;
		
		double vel_x = 10 * (((int)(Math.random() * 10) < 50)? -1 : 1);
		double vel_y = 10;
		
		double acc_x = 0;
		double acc_y = Math.random() / 2;
		
		double frc_x = Math.random();
		double frc_y = Math.random() / 2;
		
		motion.setPosition(pos_x, pos_y);
		motion.setVelocity(vel_x, vel_y);
		motion.setAcceleration(acc_x, acc_y);
		motion.setFriction(frc_x, frc_y);
		
		motion.setPosition(20, 0);
		motion.setVelocity(10, 10);
		motion.setAcceleration(0, 0.5);
		motion.setFriction(0.4, 0.8);
	}
	
	@Override
	public void update() 
	{
		if (Math.abs(motion.vel.x) < 0.01)
		{
			motion.stop();
			return;
		}
		
		if (motion.hitBoundaryX(radius)) 
		{
			motion.reverseX();
		}
		
		if (motion.pos.y > motion.boundary.height - radius - 1)
		{
			motion.pos.y = motion.boundary.height - radius - 1;
			
			motion.reverseY();
			
			motion.applyFriction();
		}
		
		motion.move(true);
	}
	
	@Override
	public void paint(Graphics g) 
	{
		g.setColor(Color.green);
		g.fillOval(motion.pos.x - radius, motion.pos.y - radius, radius * 2, radius * 2);
		
		g.setColor(Color.black);
		g.drawOval(motion.pos.x - radius, motion.pos.y - radius, radius * 2, radius * 2);
	}
}