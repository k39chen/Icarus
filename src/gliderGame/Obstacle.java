package gliderGame;

import global.Global;

import java.awt.Graphics;
import common.ActiveObject;
import common.Animation;

public class Obstacle extends ActiveObject
{	
	public static final int OBSTACLE_NORMAL 	= 0;
	public static final int OBSTACLE_CHARGER 	= 1;
	public static final int OBSTACLE_PATROL 	= 2;
	public static final int OBSTACLE_BOOMER 	= 3;
	public static final int NUM_OBSTACLES 		= 4;
	
	public static final int OBSTACLE_BOUNDARY_FLOOR		= 0;
	public static final int OBSTACLE_BOUNDARY_CEILING 	= 1;
	public static final int NUM_BOUNDARY_OBSTACLES 		= 2;
	
	public double speedX;
	public double speedY;
	
	public static Obstacle GenerateObstacle()
	{
		Global.gliderGame.obstacleTimer.setDelay((int)(Math.random() * GliderGame.OBSTACLE_SPAWN_DELAY) + GliderGame.OBSTACLE_SPAWN_DELAY_DEVIATION);
		switch ((int)(Math.random() * NUM_OBSTACLES))
		{
			case OBSTACLE_NORMAL:	return new Obstacle_Normal();
			case OBSTACLE_CHARGER:	return new Obstacle_Charger();
			case OBSTACLE_PATROL:	return new Obstacle_Patrol();
			case OBSTACLE_BOOMER:	return new Obstacle_Boomer();
			default:				return null;
		}
	}
	
	public static Obstacle GenerateObstacleFloor()
	{
		Global.gliderGame.obstacleFloorTimer.setDelay((int)(Math.random() * GliderGame.OBSTACLE_FLOOR_SPAWN_DELAY) + GliderGame.OBSTACLE_FLOOR_SPAWN_DELAY_DEVIATION);
		return new Obstacle_Floor();
	}
	public static Obstacle GenerateObstacleCeiling()
	{
		Global.gliderGame.obstacleCeilingTimer.setDelay((int)(Math.random() * GliderGame.OBSTACLE_CEILING_SPAWN_DELAY) + GliderGame.OBSTACLE_CEILING_SPAWN_DELAY_DEVIATION);
		return new Obstacle_Ceiling();
	}

	protected Animation animation;
	public int ObstacleType;
	public double scaleX;
	public double scaleY;
	
	public Obstacle()
	{
		super();
		
		speedX = 0;
		speedY = 0;
		
		scaleX = 1.0;
		scaleY = 1.0;
	}
	
	public void update(Glider glider, int lowBoundaryXOffset, boolean elastic)
	{
		if (glider.alive)
		{
			motion.vel.x = speedX * Global.gliderGame.STAGE_SPEED_BOOST * Global.gliderGame.STAGE_SPEED;
		}
		
		if (glider.canDamage && glider.collision.hit(this))
		{
			glider.damage();
			return;
		}
		
		if (motion.hitLowerBoundaryX(lowBoundaryXOffset))
		{	
			kill();
			stop();
		}
		
		super.update(elastic);
	}
	
	public void update(Glider glider, boolean elastic)
	{
		update(glider, 0, elastic);
	}
	
	public void update(Glider glider, int lowBoundaryXOffset)
	{
		update(glider, lowBoundaryXOffset, false);
	}
	
	public void update(Glider glider)
	{	
		update(glider, 0, false);
	}
	
	public void stop()
	{
		motion.stop();
	}
	
	public void paint(Graphics g)
	{
		animation.getFrame().paint(g, motion.pos.x, motion.pos.y, scaleX, scaleY);
		super.paint(g);
	}
}