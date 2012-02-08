package common;

import global.Global;

import java.awt.Graphics;
import java.util.Vector;

import resources.Graphic;
import resources.GraphicMgr;

public class Spritesheet 
{
	Graphic img;
	public int numX;
	public int numY;
	
	public int subWidth;
	public int subHeight;
	
	public Vector<Graphic> spritesheet;
	
	public Spritesheet(String id, int rNumX, int rNumY)
	{
		spritesheet = new Vector<Graphic>();
		
		img = GraphicMgr.getGraphic(id);
		numX = rNumX;
		numY = rNumY;
		
		subWidth = (int)(img.getWidth()/numX * (1 / Global.SCALE));
		subHeight = (int)(img.getHeight()/numY * (1 / Global.SCALE));
		
		for (int y=0; y<numY; y++)
		{
			for (int x=0; x<numX; x++)
			{
				spritesheet.add(new Graphic(img.getData().getSubimage(
						x * subWidth, y * subHeight,
						subWidth, subHeight)));
			}
		}
	}
	
	public Graphic getSprite(int index)
	{
		return spritesheet.get(index);
	}
	
	public void paint(Graphics g, int index, int x, int y)
	{
		spritesheet.get(index).paint(g, x, y);
	}
}