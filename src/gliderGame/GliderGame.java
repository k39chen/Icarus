package gliderGame;

import global.Global;

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
	
	public Rectangle sky;
	public Rectangle sea;
	
	public Timer obstacleTimer;
	
	public int border_offset;
	
	public GliderGame()
	{
		hud = new Hud(this);
		
		parallaxes = new Vector<Parallax>();
		parallaxes.add(new Parallax("assets/background.png", -2, 0));
		parallaxes.add(new Parallax("assets/midground.png", -4, 0));
		parallaxes.add(new Parallax("assets/foreground.png", -6, 0));
		
		obstacles = new Vector<Obstacle>();
		obstacles.add(new Obstacle());
		
		ActionListener actionlistener = new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				obstacles.add(new Obstacle());
			}
		};
		
		obstacleTimer = new Timer(1000, actionlistener);
		obstacleTimer.start();
		
		glider = new Glider();
		
		border_offset = Global.ScaleValue(32);
		
		sky = new Rectangle(0, 0, Global.ScaleValue(Global.SCREEN_WIDTH), border_offset);
		sea = new Rectangle(0, Global.ScaleValue(Global.SCREEN_HEIGHT) - border_offset, Global.ScaleValue(Global.SCREEN_WIDTH), border_offset);
	}
	
	public void update()
	{	
		if (!glider.alive)
		{
			for (Parallax parallax : parallaxes)
			{
				parallax.stop();
			}
			
			for (Obstacle obstacle : obstacles)
			{
				obstacle.stop();
			}
		}
		
		glider.update();
		hud.update();
		
		for (Parallax parallax : parallaxes)
		{
			parallax.update();
		}
		
		for (int k=0; k<obstacles.size(); k++)
		{
			Obstacle obstacle = obstacles.get(k);
			
			if (!obstacle.alive)
			{
				obstacles.remove(k);
				k--;
				continue;
			}
			
			obstacle.update();
			
			if (glider.collision.hit(obstacle))
			{
				glider.alive = false;
			}
		}
		
		if (glider.collision.hit(sea) || glider.collision.hit(sky))
		{
			glider.alive = false;
		}
	}
	
	public void paint(Graphics g)
	{
		for (Parallax parallax : parallaxes)
		{
			parallax.paint(g);
		}
		
		for (Obstacle obstacle : obstacles)
		{
			obstacle.paint(g);
		} 
		
		/*
		g.setColor(Color.blue);
		g.fillRect(sky.x, sky.y, sky.width, sky.height);
		g.fillRect(sea.x, sea.y, sea.width, sea.height);
		*/
		glider.paint(g);
		
		if (!glider.alive)
		{
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.drawString("YOU LOSE!", Global.ScaleValue(200), Global.ScaleValue(200));
		}
		
		hud.paint(g);
	}
}