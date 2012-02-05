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
		
		animation = new Animation(new Spritesheet("assets/obstacle_boomer.png", 1, 1), vec, 150);
		animation.play();
		
		width = animation.spritesheet.subWidth;
		height = animation.spritesheet.subHeight;
		
		border_offset = Global.ScaleValue(32);
	
		motion.setPosition(Global.ScreenWidth(), (int)(Math.random() * (Global.ScreenHeight() - border_offset - height)));
		motion.setVelocity(Global.ScaleValue(-5), 0);
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
				vec.add(0);
				
				animation = new Animation(new Spritesheet("assets/obstacle_boomer_2.png", 1, 1), vec, 100);
				animation.play();
				
				width *= 2.5;
				height *= 2.5;
				collision.circle.radius *= 2.5;
				collision.circle.width *= 2.5;
				collision.circle.height *= 2.5;
				
				motion.pos.y -= collision.circle.radius/2;
			}
		}
		
		if (motion.hitLowerBoundaryX(-collision.circle.radius))
		{
			alive = false;
			stop();
		}
		motion.move();
		
		super.update(glider);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
	}
}