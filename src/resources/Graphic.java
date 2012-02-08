package resources;

import global.Global;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Graphic extends Resource
{	
	public Graphic(BufferedImage data)
	{
		super(data);
	}
	
	public Graphic(String url)
	{
		super(url);
	}
	
	protected boolean loadData()
	{	
		try 
		{
			URL url = new URL(Global.CODE_BASE, r_url);
			
			r_data = ImageIO.read(url);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			System.err.println(r_url + " not loaded.");
			
			return false;
		}
		
		return true;
	}
	
	public int getHeight()
	{
		return (int)(Global.ScaleValue(((BufferedImage)(r_data)).getHeight()));
	}
	
	public int getWidth()
	{
		return (int)(Global.ScaleValue(((BufferedImage)(r_data)).getWidth()));
	}
	
	public void paint(Graphics g, int x, int y)
	{
		g.drawImage((BufferedImage)r_data, x, y, getWidth(), getHeight(), null);
	}
	
	public void paint(Graphics g, int x, int y, int width, int height)
	{
		g.drawImage((BufferedImage)r_data, x, y, Global.ScaleValue(width), Global.ScaleValue(height), null);
		//paint(g, x, y, Global.ScaleValue(width), Global.ScaleValue(height));
	}
	
	public void paint(Graphics g, int x, int y, double scaleX, double scaleY)
	{
		g.drawImage((BufferedImage)r_data, x, y, (int)(getWidth() * scaleX), (int)(getHeight() * scaleY), null);
	}
	
	public void paint(Graphics g, int x, int y, Color color)
	{	
		float scales[] = 
		{
			(float)((float)color.getRed() / (float)255),
			(float)((float)color.getGreen() / (float)255),
			(float)((float)color.getBlue() / (float)255),
			(float)((float)color.getAlpha() / (float)255)
		};
		
		float offsets[] = new float[4];
		RescaleOp rop = new RescaleOp(scales, offsets, null);
		
		((Graphics2D)g).drawImage((BufferedImage)r_data, rop, x, y);
	}
	
	public BufferedImage getData() 
	{
		return (BufferedImage)r_data;
	}
}