package gliderGame;

import global.Global;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Timer;

import common.ActiveObject;
import common.Animation;
import common.Circle;
import common.Spritesheet;

public class Glider extends ActiveObject
{
	private static int DAMAGE_TIMER_DURATION = 1000;
	
	public double distance;
	
	public int lives = 3;
	
	private Animation animation;
	
	private Timer damageTimer;
	public boolean canDamage;
	
	private AudioClip ac_damage;
	
	public Glider()
	{
		super();
		
		ac_damage = Global.GameApplet.getAudioClip(Global.CODE_BASE, "assets/sfx/sample.wav");
		ac_damage.stop();
		
		canDamage = true;
		
		distance = 0;
		
		Vector<Integer> vec = new Vector<Integer>();
		vec.add(0);vec.add(1);
		
		animation = new Animation(new Spritesheet("player.png", 2, 1), vec, 200);
		animation.play();
		
		width = animation.spritesheet.spritesheet.get(0).getWidth();
		height = animation.spritesheet.spritesheet.get(0).getHeight();
		
		motion.setPosition((Global.ScreenWidth() - width)/2 - Global.ScaleValue(300), (Global.ScreenHeight() - height)/2);
		motion.setVelocity(0, Global.ScaleValueD(5));
		motion.setAcceleration(0, 0);
		motion.setFriction(1, 1);
	
		motion.setBoundary(new Rectangle(0, 0, Global.ScreenWidth(), Global.ScreenHeight()));
		
		collision.SetCircle(new Circle(0, 0, width/2));
		
		damageTimer = new Timer(DAMAGE_TIMER_DURATION, new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				canDamage = true;
				damageTimer.stop();
			}
		});
	}
	
	public void damage()
	{
		lives--;
		
		if (lives <= 0)
		{
			kill();
			return;
		}
		
		//ac_damage.stop();
		//ac_damage.play();
		
		canDamage = false;
		
		damageTimer.start();
	}
	
	public void update()
	{	
		if (alive)
		{	
			distance += (Global.gliderGame.STAGE_SPEED * Global.gliderGame.STAGE_SPEED_BOOST);
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
		
		if (!canDamage)
		{
			animation.getFrame().paint(g, motion.pos.x, motion.pos.y, new Color(0,0,0,100));
		}
		super.paint(g);
	}
}