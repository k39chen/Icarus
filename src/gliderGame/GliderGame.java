package gliderGame;

import global.Global;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Timer;

public class GliderGame 
{
	public Hud hud;
	
	public Vector<Parallax> parallaxes;
	
	public Vector<Obstacle> obstacles;
	
	public Glider glider;
	
	public Rectangle floor;
	public Rectangle ceiling;
	
	public Timer obstacleTimer;
	
	public int border_offset;
	
	public GliderGame()
	{	
		hud = new Hud(this);
		
		Vector<Integer> vec = new Vector<Integer>();
		vec.add(1);vec.add(2);vec.add(1);vec.add(0);
		
		parallaxes = new Vector<Parallax>();
		parallaxes.add(new Parallax("assets/background.png", -2, 0));
		parallaxes.add(new Parallax("assets/midground.png", -4, 0));
		parallaxes.add(new Parallax("assets/foreground.png", -6, 0));
		
		obstacles = new Vector<Obstacle>();
		obstacles.add(Obstacle.GenerateObstacle());
		
		ActionListener actionlistener = new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				obstacles.add(Obstacle.GenerateObstacle());
			}
		};
		
		obstacleTimer = new Timer(800, actionlistener);
		obstacleTimer.start();
		
		glider = new Glider();
		
		border_offset = Global.ScaleValue(32);
		
		ceiling = new Rectangle(0, 0, Global.ScreenWidth(), 1);
		floor = new Rectangle(0, Global.ScreenHeight() - border_offset, Global.ScreenWidth(), border_offset);
	}
	
	public void update()
	{	
		if (!glider.alive)
		{
			for (Parallax parallax : parallaxes)
			{
				parallax.stop();
			}
			
			for (int i=0; i<obstacles.size(); i++)
			{
				obstacles.get(i).stop();
				obstacles.get(i).animation.stop();
			}
		}
		
		glider.update();
		hud.update();
		
		for (Parallax parallax : parallaxes)
		{
			parallax.update();
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
		for (Parallax parallax : parallaxes)
		{
			parallax.paint(g);
		}
		
		for (int i=0; i<obstacles.size(); i++)
		{
			obstacles.get(i).paint(g);
		}
	
		glider.paint(g);
		
		if (!glider.alive)
		{
			g.setColor(Color.green);
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.drawString("YOU LOSE!", Global.ScaleValue(200), Global.ScaleValue(200));
		}
		
		hud.paint(g);
	}
}