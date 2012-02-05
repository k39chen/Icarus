package gliderGame;

import global.Global;
import java.awt.Rectangle;
import java.util.Vector;

import common.Animation;
import common.Circle;
import common.Spritesheet;

public class Obstacle_Normal extends Obstacle
{
	public Obstacle_Normal()
	{
		super();
		
		ObstacleType = OBSTACLE_NORMAL;
		
		Vector<Integer> vec = new Vector<Integer>();
		vec.add(0);vec.add(1);
		
		animation = new Animation(new Spritesheet("assets/obstacle_normal.png", 2, 1), vec, 150);
		animation.play();
		
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
		super.update(glider);
	}
}