package gliderGame;

import global.Global;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

public class GliderGame 
{
	public Hud hud;
	
	public Vector<Parallax> parallaxes;
	
	public Glider glider;
	
	public Rectangle sky;
	public Rectangle sea;
	
	public int border_offset;
	
	public GliderGame()
	{
		hud = new Hud(this);
		
		parallaxes = new Vector<Parallax>();
		parallaxes.add(new Parallax("assets/background.png", -1, 0));
		parallaxes.add(new Parallax("assets/midground.png", -3, 0));
		parallaxes.add(new Parallax("assets/foreground.png", -6, 0));
		
		glider = new Glider();
		
		border_offset = Global.ScaleValue(32);
		
		sky = new Rectangle(0, 0, Global.ScaleValue(Global.SCREEN_WIDTH), border_offset);
		sea = new Rectangle(0, Global.ScaleValue(Global.SCREEN_HEIGHT) - border_offset, Global.ScaleValue(Global.SCREEN_WIDTH), border_offset);
	}
	
	public void update()
	{
		if (glider.alive)
		{
			hud.update();
			
			for (int i=0; i<parallaxes.size(); i++)
			{
				parallaxes.get(i).update();
			}
			
			glider.update();
			
			if (glider.collision.hit(sea) || glider.collision.hit(sky))
			{
				glider.alive = false;
			}
		}
		else
		{
			for (int i=0; i<parallaxes.size(); i++)
			{
				parallaxes.get(i).stop();
			}
		}
	}
	
	public void paint(Graphics g)
	{
		for (int i=0; i<parallaxes.size(); i++)
		{
			parallaxes.get(i).paint(g);
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