package resources;

import global.Global;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Graphic extends Resource
{	
	public Graphic(String url)
	{
		super(url);
		
		loadData();
	}
	
	protected void loadData()
	{	
		try 
		{
			URL url = new URL(Global.CODE_BASE, r_url);
			r_data = ImageIO.read(url);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public int getHeight()
	{
		return ((BufferedImage)(r_data)).getHeight();
	}
	
	public int getWidth()
	{
		return ((BufferedImage)(r_data)).getWidth();
	}
	
	public void paint(Graphics g, int x, int y)
	{
		g.drawImage((BufferedImage)r_data, x, y, (int)(getWidth() * Global.SCALE), (int)(getHeight() * Global.SCALE), null);
	}
	
	public void paint(Graphics g, int x, int y, int width, int height)
	{
		g.drawImage((BufferedImage)r_data, x, y, (int)(width * Global.SCALE), (int)(height * Global.SCALE), null);
		
		
		paint(g, x, y, width * Global.SCALE, height * Global.SCALE);
	}
	
	public void paint(Graphics g, int x, int y, double scaleX, double scaleY)
	{
		g.drawImage((BufferedImage)r_data, x, y, (int)(getWidth() * scaleX * Global.SCALE), (int)(getHeight() * scaleY * Global.SCALE), null);
	}
	
	public BufferedImage getData() 
	{
		return (BufferedImage)r_data;
	}
}