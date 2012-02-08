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
	public Circle circle;
	
	private boolean collisionShapeSet;
	
	public ActiveObject object;
	
	public Collision(ActiveObject rObject)
	{
		object = rObject;
		
		UnsetCollisionShape();
	}
	
	public void SetPoint(Point pt)
	{
		if (point != null || !collisionShapeSet)
		{
			point = pt;
			collisionShapeSet = true;
		}
	}
	
	public void SetRectangle(Rectangle rect)
	{
		if (rectangle != null || !collisionShapeSet)
		{
			rectangle = rect;
			collisionShapeSet = true;
		}
	}
	
	public void SetCircle(Circle circ)
	{
		if (circle != null || !collisionShapeSet)
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
				Point sourcePoint = new Point(
						point.x + object.motion.pos.x, 
						point.y + object.motion.pos.y);
				
				return 	(sourcePoint.x == target.x) && 
						(sourcePoint.y == target.y);
			}
			if (rectangle != null)
			{
				Rectangle sourceRectangle = new Rectangle(
						rectangle.x + object.motion.pos.x, 
						rectangle.y + object.motion.pos.y, 
						rectangle.width, rectangle.height);
				
				return 	(target.x >= sourceRectangle.x) &&
						(target.x <= sourceRectangle.x + sourceRectangle.width) &&
						(target.y >= sourceRectangle.y) &&
						(target.y <= sourceRectangle.y + sourceRectangle.height);
			}
			if (circle != null)
			{
				Circle sourceCircle = new Circle(
						(int)(circle.x + object.motion.pos.x),
						(int)(circle.y + object.motion.pos.y),
						(int)(circle.width/2));

				return sourceCircle.intersects(new Rectangle(target.x, target.y, 1, 1));
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
				Point sourcePoint = new Point(
						point.x + object.motion.pos.x, 
						point.y + object.motion.pos.y);
				
				return 	(sourcePoint.x >= target.x) &&
						(sourcePoint.x <= target.x + target.width) &&
						(sourcePoint.y >= target.y) &&
						(sourcePoint.y <= target.y + target.height);
			}
			if (rectangle != null)
			{	
				Rectangle sourceRectangle = new Rectangle(
						rectangle.x + object.motion.pos.x, 
						rectangle.y + object.motion.pos.y, 
						rectangle.width, rectangle.height);
				
				return sourceRectangle.intersects(target);
			}
			if (circle != null)
			{
				Ellipse2D.Double sourceCircle = new Ellipse2D.Double(
						(int)(circle.x + object.motion.pos.x),
						(int)(circle.y + object.motion.pos.y),
						(int)(circle.radius * 2), (int)(circle.radius * 2));
				
				return sourceCircle.intersects(target);
			}
		}
			
		return false;
	}
	
	public boolean hit(Circle target)
	{
		if (collisionShapeSet)
		{
			if (point != null)
			{
				Point sourcePoint = new Point(
						point.x + object.motion.pos.x, 
						point.y + object.motion.pos.y);
				
				int distance = (int)sourcePoint.distance(target.x, target.y);
				return distance <= target.radius;
			}
			if (rectangle != null)
			{
				Rectangle sourceRectangle = new Rectangle(
						rectangle.x + object.motion.pos.x, 
						rectangle.y + object.motion.pos.y, 
						rectangle.width, rectangle.height);
				
				return target.intersects(sourceRectangle);
			}
			if (circle != null)
			{
				Circle sourceCircle = new Circle(
						(int)(circle.x + object.motion.pos.x),
						(int)(circle.y + object.motion.pos.y),
						(int)(circle.radius));

				Point point = new Point((int)(sourceCircle.x + sourceCircle.radius), (int)(sourceCircle.y + sourceCircle.radius));
				int distance = (int)point.distance(target.x + target.radius, target.y + target.radius);
				
				return (distance <= (sourceCircle.radius + target.radius));
			}
		}
		
		return false;
	}
	
	public boolean hit(ActiveObject targetObject)
	{
		if (collisionShapeSet)
		{
			if (targetObject.collision.point != null)
			{
				Point targetPoint = new Point(
						targetObject.collision.point.x + targetObject.motion.pos.x, 
						targetObject.collision.point.y + targetObject.motion.pos.y);
				
				return hit(targetPoint);
			}
			else if (targetObject.collision.rectangle != null)
			{
				Rectangle targetRectangle = new Rectangle(
						targetObject.collision.rectangle.x + targetObject.motion.pos.x, 
						targetObject.collision.rectangle.y + targetObject.motion.pos.y, 
						targetObject.collision.rectangle.width, 
						targetObject.collision.rectangle.height);
				
				return hit(targetRectangle);
			}
			else if (targetObject.collision.circle != null)
			{
				Circle targetCircle = new Circle(
						(int)(targetObject.collision.circle.x + targetObject.motion.pos.x),
						(int)(targetObject.collision.circle.y + targetObject.motion.pos.y),
						(int)(targetObject.collision.circle.width/2));
				
				return hit(targetCircle);
			}
			else
			{
			}
		}
		
		return false;
	}
	
	public void paint(Graphics g)
	{
		if (collisionShapeSet)
		{
			g.setColor(Color.black);
			
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
				g.drawOval(object.motion.pos.x + circle.x, object.motion.pos.y + circle.y, circle.radius * 2, circle.radius * 2);
			}
		}
	}
}