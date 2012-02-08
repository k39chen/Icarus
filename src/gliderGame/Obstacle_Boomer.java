package gliderGame;

import global.Global;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

import common.Animation;
import common.Circle;
import common.Spritesheet;

public class Obstacle_Boomer extends Obstacle
{
	public boolean expanded = false; 
	
	public Obstacle_Boomer()
	{
		super();
		
		scaleX = 1.0;
		scaleY = 1.0;
		
		ObstacleType = OBSTACLE_BOOMER;
		
		Vector<Integer> vec = new Vector<Integer>();
		vec.add(0);
		
		animation = new Animation(new Spritesheet("obstacle_boomer.png", 1, 1), vec, 150);
		animation.play();
		
		width = Global.ScaleValue(animation.spritesheet.subWidth);
		height = Global.ScaleValue(animation.spritesheet.subHeight);
		
		speedX = Global.ScaleValue(-5);
		
		motion.setPosition(Global.ScreenWidth(), (int)(Math.random() * (Global.ScreenHeight() - GliderGame.FloorOffset() - height)));
		motion.setVelocity(speedX, 0);
		motion.setAcceleration(0, 0);
		motion.setFriction(1, 1);
		
		motion.setBoundary(new Rectangle(-width, 0, Global.ScreenWidth(), Global.ScreenHeight()));
		
		collision.SetCircle(new Circle(0, 0, width/2));
	}
	
	@Override
	public void update(Glider glider)
	{	
		if (motion.pos.x <= Global.ScreenWidth()/2 + width)
		{
			if (!expanded)
			{
				expanded = true;
				
				Vector<Integer> vec = new Vector<Integer>();
				vec.add(0);vec.add(1);vec.add(2);
				
				animation = new Animation(new Spritesheet("obstacle_boomer_2.png", 3, 1), vec, 100);
				
				width = Global.ScaleValue(animation.spritesheet.subWidth);
				height = Global.ScaleValue(animation.spritesheet.subHeight);
				
				collision.SetCircle(new Circle(0, 0, width/2));
				
				motion.pos.y -= collision.circle.radius/2;
				
				animation.play();
			}
		}
		
		super.update(glider, -collision.circle.radius * 2);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
	}
}