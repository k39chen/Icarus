package common;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class Motion
{
	public Point pos;
	public Point2D.Double vel;
	public Point2D.Double acc;
	public Point2D.Double frc;
	
	public Rectangle boundary;
	
	public Motion()
	{	
		// default motion vectors
		setPosition(0, 0);
		setVelocity(0, 0);
		setAcceleration(0, 0);
		setFriction(1, 1);
	}
	
	public boolean hitLowerBoundaryX(int xOffset)
	{
		if (boundary == null) return false;
		return pos.x - xOffset < boundary.x;
	}
	
	public boolean hitUpperBoundaryX(int xOffset)
	{
		if (boundary == null) return false;
		return pos.x + xOffset > boundary.x + boundary.width;
	}
	
	public boolean hitUpperBoundaryY(int yOffset)
	{
		if (boundary == null) return false;
		return pos.y - yOffset < boundary.y;
	}
	
	public boolean hitLowerBoundaryY(int yOffset)
	{
		if (boundary == null) return false;
		return pos.y + yOffset > boundary.y + boundary.height;
	}
	
	public boolean hitBoundaryX(int xOffset)
	{
		if (boundary == null) return false;
		return (hitUpperBoundaryX(xOffset) || hitLowerBoundaryX(xOffset));
	}
	
	public boolean hitBoundaryY(int yOffset)
	{
		if (boundary == null) return false;
		return (hitUpperBoundaryY(yOffset) || hitLowerBoundaryY(yOffset));
	}
	
	public boolean hitBoundary(int xOffset, int yOffset)
	{
		return (hitBoundaryX(xOffset) || hitBoundaryY(yOffset));
	}
	
	public void reverseX()
	{
		vel.x *= -1;
	}
	
	public void reverseY()
	{
		vel.y *= -1;
	}
	
	public void reverse()
	{
		reverseX();
		reverseY();
	}
	
	public void stop()
	{
		stopX();
		stopY();
	}
	
	public void stopX()
	{
		vel.x = 0;
		acc.x = 0;
	}
	
	public void stopY()
	{
		vel.y = 0;
		acc.y = 0;
	}
	
	public void applyFriction()
	{
		applyFrictionX();
		applyFrictionY();
	}
	
	public void applyFrictionX()
	{
		vel.x *= frc.x;
	}
	
	public void applyFrictionY()
	{
		vel.y *= frc.y;
	}
	
	public void move()
	{
		move(false);
	}
	
	// generic move call
	public void move(boolean elastic)
	{
		if (!elastic)
		{
			// don't bother processing if there is no velocity vector;
			// then snap vectors to 0 to reset
			if (Math.floor(vel.x) == 0)
			{
				stopX();
			}
			
			if (Math.floor(vel.y) == 0)
			{
				stopY();
			}
		}
			
		// calculate position
		vel.x += acc.x;
		pos.x += vel.x;
		
		vel.y += acc.y;
		pos.y += vel.y;
		
		//System.out.printf("(%d,%f,%f)\n", pos.x, vel.x, acc.x);
	}
	
	public void setPosition(int x, int y)
	{
		if (pos != null)
		{
			pos.x = x;
			pos.y = y;
		}
		else
		{
			pos = new Point(x, y);
		}
	}
	
	public void setVelocity(double x, double y)
	{
		if (vel != null)
		{
			vel.x = x;
			vel.y = y;
		}
		else
		{
			vel = new Point2D.Double(x, y);
		}
	}
	
	public void setAcceleration(double x, double y)
	{
		if (acc != null)
		{
			acc.x = x;
			acc.y = y;
		}
		else
		{
			acc = new Point2D.Double(x, y);
		}
	}
	
	public void setFriction(double x, double y)
	{
		if (frc != null)
		{
			frc.x = x;
			frc.y = y;
		}
		else
		{
			frc = new Point2D.Double(x, y);
		}
	}
	
	public void setBoundary(Rectangle newBoundary)
	{
		boundary = newBoundary;
	}
}