package common;

import java.awt.geom.Ellipse2D;

public class Circle extends Ellipse2D.Double
{
	private static final long serialVersionUID = 1L;
	public int x;
	public int y;
	public int radius;
	
	public Circle(int rX, int rY, int rRadius)
	{
		x = rX;
		y = rY;
		radius = rRadius;
		width = radius * 2;
		height = radius * 2;
	}
}