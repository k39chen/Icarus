package gliderGame;

import global.Global;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

import common.Animation;
import common.Circle;
import common.Spritesheet;

public class Obstacle_Charger extends Obstacle
{
	public boolean alerted;
	
	private Animation exclamationAnimation;
	
	public Obstacle_Charger()
	{
		super();
		
		alerted = false;
		
		ObstacleType = OBSTACLE_CHARGER;
		
		Vector<Integer> vec1 = new Vector<Integer>();
		vec1.add(0);
		
		animation = new Animation(new Spritesheet("assets/obstacle_charger.png", 1, 1), vec1, 150);
		animation.play();
		
		Vector<Integer> vec2 = new Vector<Integer>();
		vec2.add(0);
		
		exclamationAnimation = new Animation(new Spritesheet("assets/exclamation.png", 1, 1), vec2, 150);
		exclamationAnimation.play();
		
		width = animation.spritesheet.subWidth;
		height = animation.spritesheet.subHeight;
		
		border_offset = Global.ScaleValue(32);
	
		motion.setPosition(Global.ScreenWidth(), (int)(Math.random() * (Global.ScreenHeight() - border_offset - height)));
		motion.setVelocity(Global.ScaleValue(-10), 0);
		motion.setAcceleration(0, 0);
		motion.setFriction(1, 1);
		
		motion.setBoundary(new Rectangle(-width, 0, Global.ScreenWidth(), Global.ScreenHeight()));
		
		collision.SetCircle(new Circle(0, 0, width/2));
	}
	
	@Override
	public void update(Glider glider)
	{	
		if (motion.pos.y >= glider.motion.pos.y &&
			motion.pos.y <= glider.motion.pos.y + glider.height)
		{
			if (!alerted)
			{
				alerted = true;
				motion.vel.x = Global.ScaleValueD(-15);
			}
		}
		super.update(glider);
	}
	
	@Override
	public void paint(Graphics g)
	{	
		super.paint(g);
		
		if (alerted)
		{
			exclamationAnimation.getFrame().paint(g, motion.pos.x + Global.ScaleValue(15), motion.pos.y - Global.ScaleValue(30));
		}
	}
}