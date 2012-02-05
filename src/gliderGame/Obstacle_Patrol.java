package gliderGame;

import global.Global;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Vector;

import common.Animation;
import common.Circle;
import common.Spritesheet;

public class Obstacle_Patrol extends Obstacle
{
	public double oscillationPeak;
	public double energyLoss;
	
	public Obstacle_Patrol()
	{
		super();
		
		Random random = new Random();
		
		oscillationPeak = Global.ScaleValueD(5.0);
		energyLoss = Global.ScaleValueD(0.2);
		
		ObstacleType = OBSTACLE_PATROL;
		
		Vector<Integer> vec = new Vector<Integer>();
		vec.add(0);
		
		animation = new Animation(new Spritesheet("assets/obstacle_patrol.png", 1, 1), vec, 150);
		animation.play();
		
		width = animation.spritesheet.subWidth;
		height = animation.spritesheet.subHeight;
		
		border_offset = Global.ScaleValue(32);
	
		motion.setPosition(Global.ScreenWidth(), (int)(Math.random() * (Global.ScreenHeight() - border_offset - height)));
		motion.setVelocity(Global.ScaleValue(-7), ((random.nextInt(2) == 0)? -1 : 1) * oscillationPeak);
		motion.setAcceleration(0, energyLoss);
		motion.setFriction(1, 1);
		
		motion.setBoundary(new Rectangle(-width, 0, Global.ScreenWidth(), Global.ScreenHeight()));
		
		collision.SetCircle(new Circle(0, 0, width/2));
	}
	
	@Override
	public void update(Glider glider)
	{	
		if (glider.collision.hit(this))
		{
			glider.kill();
			return;
		}
		
		if (motion.hitLowerBoundaryX(0))
		{
			kill();
			stop();
		}
		
		if (Math.abs(motion.vel.y) > oscillationPeak)
		{	
			motion.acc.y *= -1;
		}
		
		motion.move(true);
	}
}