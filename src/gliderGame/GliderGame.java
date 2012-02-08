package gliderGame;

import global.Global;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Timer;

public class GliderGame 
{
	public AudioClip song;
	
	public static int OBSTACLE_SPAWN_DELAY 			= 800;
	public static int OBSTACLE_CEILING_SPAWN_DELAY 	= 5200;
	public static int OBSTACLE_FLOOR_SPAWN_DELAY 	= 4000;
	
	public static int OBSTACLE_SPAWN_DELAY_DEVIATION 		= 500;
	public static int OBSTACLE_CEILING_SPAWN_DELAY_DEVIATION = 1000;
	public static int OBSTACLE_FLOOR_SPAWN_DELAY_DEVIATION 	= 1200;
	
	public static final int FLOOR_OFFSET = 32;
	
	public double STAGE_SPEED = 1.01;
	public static final double STAGE_SPEED_BOOST_LEVELS[] = {1.0, 1.5, 2.0, 3.0, 5.0};
	public int STAGE_SPEED_BOOST_LEVEL = 0;
	public double STAGE_SPEED_BOOST = STAGE_SPEED_BOOST_LEVELS[STAGE_SPEED_BOOST_LEVEL];
	
	
	public Hud hud;
	
	public Vector<Parallax> bgParallaxes;
	public Vector<Parallax> fgParallaxes;
	
	public Vector<Obstacle> obstacles;
	
	public Glider glider;
	
	public Rectangle floor;
	public Rectangle ceiling;
	
	public Timer obstacleTimer;
	public Timer obstacleCeilingTimer;
	public Timer obstacleFloorTimer;
	
	public GliderGame()
	{	
		Global.gliderGame = this;
		
		hud = new Hud();
		
		Vector<Integer> vec = new Vector<Integer>();
		vec.add(1);vec.add(2);vec.add(1);vec.add(0);
		
		bgParallaxes = new Vector<Parallax>();
		
		/*****/
		bgParallaxes.add(new Parallax("parallax_1.png", -2, 0));
		bgParallaxes.add(new Parallax("parallax_2.png", -4, 0));
		bgParallaxes.add(new Parallax("parallax_3.png", -6, 0));
		/*****
		bgParallaxes.add(new Parallax("parallax_bg_1.png", -2, 0));
		bgParallaxes.add(new Parallax("parallax_bg_2.png", -4, 0));
		bgParallaxes.add(new Parallax("parallax_bg_3.png", -6, 0));
		/*****/
		
		fgParallaxes = new Vector<Parallax>();
		//fgParallaxes.add(new Parallax("parallax_fg_1.png", -2, 0));
		//fgParallaxes.add(new Parallax("parallax_fg_2.png", 0, 0));
		//fgParallaxes.add(new Parallax("parallax_fg_3.png", -6, 0));
		
		obstacles = new Vector<Obstacle>();
		
		obstacleTimer = new Timer((int)(Math.random() * OBSTACLE_SPAWN_DELAY), new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				obstacles.add(Obstacle.GenerateObstacle());
			}
		});
		
		obstacleCeilingTimer = new Timer((int)(Math.random() * OBSTACLE_CEILING_SPAWN_DELAY), new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				obstacles.add(Obstacle.GenerateObstacleCeiling());
			}
		});
		
		obstacleFloorTimer = new Timer((int)(Math.random() * OBSTACLE_FLOOR_SPAWN_DELAY), new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				obstacles.add(Obstacle.GenerateObstacleFloor());
			}
		});
		
		obstacleTimer.start();
		obstacleCeilingTimer.start();
		obstacleFloorTimer.start();
		
		glider = new Glider();
		
		ceiling = new Rectangle(0, 0, Global.ScreenWidth(), 1);
		floor = new Rectangle(0, Global.ScreenHeight() - FloorOffset(), Global.ScreenWidth(), FloorOffset());
	}
	
	public void update()
	{	
		if (!glider.alive)
		{
			for (Parallax bgParallax : bgParallaxes)
			{
				bgParallax.stop();
			}
			for (Parallax fgParallax : fgParallaxes)
			{
				fgParallax.stop();
			}
			
			for (int i=0; i<obstacles.size(); i++)
			{
				obstacles.get(i).stop();
				obstacles.get(i).animation.stop();
			}
		}
		
		glider.update();
		hud.update();
		
		for (Parallax bgParallax : bgParallaxes)
		{
			bgParallax.update();
		}
		for (Parallax fgParallax : fgParallaxes)
		{
			fgParallax.update();
		}
		
		for (int i=0; i<obstacles.size(); i++)
		{
			if (!obstacles.get(i).alive)
			{
				obstacles.remove(i);
				i--;
				continue;
			}
			
			obstacles.get(i).update(glider);
		}
		
		if (glider.collision.hit(ceiling))
		{
			glider.motion.pos.y = 0;
		}
		if (glider.collision.hit(floor))
		{
			glider.motion.pos.y = floor.y - glider.height;
		}
	}
	
	public void paint(Graphics g)
	{
		for (Parallax bgParallax : bgParallaxes)
		{
			bgParallax.paint(g);
		}
		
		for (int i=0; i<obstacles.size(); i++)
		{
			obstacles.get(i).paint(g);
		}
	
		glider.paint(g);
		
		for (Parallax fgParallax : fgParallaxes)
		{
			fgParallax.paint(g);
		}
		
		hud.paint(g);
	}
	
	public static final int FloorOffset()
	{
		return Global.ScaleValue(FLOOR_OFFSET);
	}
	
	public void setSpeedBoostLevel(int speed_level)
	{
		STAGE_SPEED_BOOST_LEVEL = speed_level;
		STAGE_SPEED_BOOST = STAGE_SPEED_BOOST_LEVELS[STAGE_SPEED_BOOST_LEVEL];
	}
}