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
		
		animation = new Animation(new Spritesheet("obstacle_patrol.png", 1, 1), vec, 150);
		animation.play();
		
		width = Global.ScaleValue(animation.spritesheet.subWidth);
		height = Global.ScaleValue(animation.spritesheet.subHeight);
		
		speedX = Global.ScaleValue(-7);
		speedY = ((random.nextInt(2) == 0)? -1 : 1) * oscillationPeak;
		
		motion.setPosition(Global.ScreenWidth(), (int)(Math.random() * (Global.ScreenHeight() - GliderGame.FloorOffset() - height)));
		motion.setVelocity(speedX, speedY);
		motion.setAcceleration(0, energyLoss);
		motion.setFriction(1, 1);
		
		motion.setBoundary(new Rectangle(-width, 0, Global.ScreenWidth(), Global.ScreenHeight()));
		
		collision.SetCircle(new Circle(0, 0, width/2));
	}
	
	@Override
	public void update(Glider glider)
	{	
		if (glider.alive)
		{
			motion.acc.y = (motion.acc.y > 0)
				? energyLoss * Global.gliderGame.STAGE_SPEED_BOOST
				: -energyLoss * Global.gliderGame.STAGE_SPEED_BOOST;
		}
		
		if (Math.abs(motion.vel.y) > oscillationPeak)
		{	
			motion.acc.y *= -1;
		}
		
		super.update(glider, true);
	}
}