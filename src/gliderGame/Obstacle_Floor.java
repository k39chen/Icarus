package gliderGame;

import global.Global;
import java.awt.Rectangle;
import java.util.Vector;

import common.Animation;
import common.Circle;
import common.Spritesheet;

public class Obstacle_Floor extends Obstacle
{
	public Obstacle_Floor()
	{
		super();
		
		ObstacleType = OBSTACLE_NORMAL;
		
		Vector<Integer> vec = new Vector<Integer>();
		vec.add(0);vec.add(1);
		
		animation = new Animation(new Spritesheet("obstacle_floor.png", 3, 1), vec, 150);
		animation.play();
		
		width = Global.ScaleValue(animation.spritesheet.subWidth);
		height = Global.ScaleValue(animation.spritesheet.subHeight);
		
		speedX = Global.ScaleValue(-5); 
		
		motion.setPosition(Global.ScreenWidth(), Global.ScreenHeight() - GliderGame.FloorOffset() - height);
		motion.setVelocity(speedX, 0);
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