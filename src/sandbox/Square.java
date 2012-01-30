package sandbox;

import java.awt.Color;
import java.awt.Graphics;

import common.ActiveObject;

public class Square extends ActiveObject
{
	public int speed;
	public int width;
	
	public Square()
	{
		super();
		
		speed = 5;
		width = 20;
		
		motion.setPosition(0, 0);
		motion.setVelocity(0, 0);
		motion.setAcceleration(0, 0);
		motion.setFriction(0, 0);
	}

	public void moveLeft()
	{
		motion.pos.x -= speed;
	}
	
	public void moveRight()
	{
		motion.pos.x += speed;
	}
	
	public void moveUp()
	{
		motion.pos.y -= speed;
	}
	
	public void moveDown()
	{
		motion.pos.y += speed;
	}
	
	@Override
	public void update() 
	{
	}

	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(motion.pos.x, motion.pos.y, width, width);
	}
}
