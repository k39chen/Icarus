package gliderGame;

import global.Global;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Vector;

import common.ActiveObject;
import common.Animation;
import common.Circle;
import common.Spritesheet;

public class Glider extends ActiveObject
{
	public double distance;
	public double speed;
	
	private Animation animation;
	
	public Glider()
	{
		super();
		
		distance = 0;
		speed = 1.01;
		
		Vector<Integer> vec = new Vector<Integer>();
		vec.add(0);vec.add(1);vec.add(2);
		
		animation = new Animation(new Spritesheet("assets/quicksprite.png", 3, 1), vec, 150);
		animation.play();
		
		width = animation.spritesheet.spritesheet.get(0).getWidth();
		height = animation.spritesheet.spritesheet.get(0).getHeight();
		
		motion.setPosition((Global.ScreenWidth() - width)/2 - Global.ScaleValue(300), (Global.ScreenHeight() - height)/2);
		motion.setVelocity(0, Global.ScaleValueD(5));
		motion.setAcceleration(0, 0);
		motion.setFriction(1, 1);
	
		motion.setBoundary(new Rectangle(0, 0, Global.ScreenWidth(), Global.ScreenHeight()));
		
		collision.SetCircle(new Circle(0, 0, width/2));
	}
	
	public void update()
	{	
		if (alive)
		{	
			distance += speed;
			super.update();
		}
		else
		{
			motion.stop();
			animation.stop();
		}
	}
	
	public void moveUp()
	{
		motion.reverseY();
	}
	
	public void moveDown()
	{
		motion.reverseY();
	}
	
	public void paint(Graphics g)
	{	
		animation.getFrame().paint(g, motion.pos.x, motion.pos.y);
		
		super.paint(g);
	}
}