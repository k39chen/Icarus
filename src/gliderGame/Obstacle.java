package gliderGame;

import java.awt.Graphics;
import common.ActiveObject;
import common.Animation;

public class Obstacle extends ActiveObject
{
	public int border_offset;
	
	public static final int OBSTACLE_NORMAL = 0;
	public static final int OBSTACLE_CHARGER = 1;
	public static final int OBSTACLE_PATROL = 2;
	public static final int OBSTACLE_BOOMER = 3;
	public static final int NUM_OBSTACLES 	= 4;
	
	public static Obstacle GenerateObstacle()
	{
		//switch ((int)(Math.random() * NUM_OBSTACLES))
		switch(2)
		{
			case OBSTACLE_NORMAL:	return new Obstacle_Normal();
			case OBSTACLE_CHARGER:	return new Obstacle_Charger();
			case OBSTACLE_PATROL:	return new Obstacle_Patrol();
			case OBSTACLE_BOOMER:	return new Obstacle_Boomer();
			default:				return null;
		}
	}

	protected Animation animation;
	public int ObstacleType;
	public double scaleX;
	public double scaleY;
	
	public Obstacle()
	{
		super();
		
		scaleX = 1.0;
		scaleY = 1.0;
	}
	
	public void update(Glider glider)
	{	
		if (glider.collision.hit(this))
		{
			glider.alive = false;
			return;
		}
		
		if (motion.hitLowerBoundaryX(0))
		{
			kill();
			stop();
		}
		
		super.update();
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