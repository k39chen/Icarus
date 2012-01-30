package common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Collision
{	
	public Point point;
	public Rectangle rectangle;
	public Ellipse2D.Double circle;
	
	private boolean collisionShapeSet;
	
	public ActiveObject object;
	
	public Collision(ActiveObject rObject)
	{
		object = rObject;
		
		UnsetCollisionShape();
	}
	
	public void SetPoint(Point pt)
	{
		if (!collisionShapeSet)
		{
			point = pt;
			collisionShapeSet = true;
		}
	}
	
	public void SetRectangle(Rectangle rect)
	{
		if (!collisionShapeSet)
		{
			rectangle = rect;
			collisionShapeSet = true;
		}
	}
	
	public void SetCircle(Ellipse2D.Double circ)
	{
		if (!collisionShapeSet)
		{
			circle = circ;
			collisionShapeSet = true;
		}
	}
	
	public void UnsetCollisionShape()
	{
		point = null;
		rectangle = null;
		circle = null;
		
		collisionShapeSet = false;
	}
	
	public boolean hit(Point target)
	{
		if (collisionShapeSet)
		{
			if (point != null)
			{
				Point realPoint = new Point(
						point.x + object.motion.pos.x, 
						point.y + object.motion.pos.y);
				
				return 	(realPoint.x == target.x) && 
						(realPoint.y == target.y);
			}
			if (rectangle != null)
			{
				Rectangle realRectangle = new Rectangle(
						rectangle.x + object.motion.pos.x, 
						rectangle.y + object.motion.pos.y, 
						rectangle.width, rectangle.height);
				
				return 	(target.x >= realRectangle.x) &&
						(target.x <= realRectangle.x + realRectangle.width) &&
						(target.y >= realRectangle.y) &&
						(target.y <= realRectangle.y + realRectangle.height);
			}
			if (circle != null)
			{
				Ellipse2D.Double realCircle = new Ellipse2D.Double(
						circle.x + object.motion.pos.x,
						circle.y + object.motion.pos.y,
						circle.width, circle.height);
			}
		}
		
		return false;
	}
	
	public boolean hit(Rectangle target)
	{
		if (collisionShapeSet)
		{
			if (point != null)
			{
				Point realPoint = new Point(
						point.x + object.motion.pos.x, 
						point.y + object.motion.pos.y);
				
				return 	(realPoint.x >= target.x) &&
						(realPoint.x <= target.x + target.width) &&
						(realPoint.y >= target.y) &&
						(realPoint.y <= target.y + target.height);
			}
			if (rectangle != null)
			{
				Rectangle realRectangle = new Rectangle(
						rectangle.x + object.motion.pos.x, 
						rectangle.y + object.motion.pos.y, 
						rectangle.width, rectangle.height);
				
				return realRectangle.intersects(target);
			}
			if (circle != null)
			{
				Ellipse2D.Double realCircle = new Ellipse2D.Double(
						circle.x + object.motion.pos.x,
						circle.y + object.motion.pos.y,
						circle.width, circle.height);
			}
		}
			
		return false;
	}
	
	public boolean hit(Ellipse2D.Double target)
	{
		if (collisionShapeSet)
		{
			if (point != null)
			{
				Point realPoint = new Point(
						point.x + object.motion.pos.x, 
						point.y + object.motion.pos.y);
			}
			if (rectangle != null)
			{
				Rectangle realRectangle = new Rectangle(
						rectangle.x + object.motion.pos.x, 
						rectangle.y + object.motion.pos.y, 
						rectangle.width, rectangle.height);
			}
			if (circle != null)
			{
				Ellipse2D.Double realCircle = new Ellipse2D.Double(
						circle.x + object.motion.pos.x,
						circle.y + object.motion.pos.y,
						circle.width, circle.height);
			}
		}
		
		return false;
	}
	
	public void paint(Graphics g)
	{
		if (collisionShapeSet)
		{
			g.setColor(Color.blue);
			
			if (point != null)
			{
				g.drawRect(object.motion.pos.x + point.x, object.motion.pos.y + point.y, 1, 1);
			}
			if (rectangle != null)
			{
				g.drawRect(object.motion.pos.x + rectangle.x, object.motion.pos.y + rectangle.y, rectangle.width, rectangle.height);
			}
			if (circle != null)
			{
				g.drawOval(object.motion.pos.x + (int)circle.x, object.motion.pos.y + (int)circle.y, (int)circle.width, (int)circle.height);
			}
		}
	}
}