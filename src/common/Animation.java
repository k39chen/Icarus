package common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Timer;

import resources.Graphic;

public class Animation 
{
	public Spritesheet spritesheet; 
	public int delay;
	
	public boolean started;
	
	private Timer mTimer;
	private int mIndex;
	
	private Vector<Integer> mFrames;
	
	public Animation(Spritesheet rSpritesheet, int rDelay)
	{
		spritesheet = rSpritesheet;
		delay = rDelay;
		
		mFrames = new Vector<Integer>(spritesheet.numX * spritesheet.numY);
		
		for (int i=0; i<mFrames.size(); i++)
		{
			mFrames.set(i, i); 
		}
		
		init();
	}
	
	public Animation(Spritesheet rSpritesheet, Vector<Integer> rFrames, int rDelay)
	{	
		spritesheet = rSpritesheet;
		delay = rDelay;
		mFrames = rFrames;
		
		init();
	}
	
	public void init()
	{
		started = false;
		
		ActionListener actionlistener = new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				mIndex = (mIndex + 1) % mFrames.size();
			}
		};
		mTimer = new Timer(delay, actionlistener);
		
		mIndex = 0;
	}

	public Graphic getFrame()
	{
		return spritesheet.getSprite(mFrames.get(mIndex));
	}
	
	public void play()
	{
		mTimer.start();
		started = true;
	}
	
	public void setDelay(int newDelay)
	{
		delay = newDelay;
	}
	
	public void pause()
	{
		mTimer.stop();
		started = false;
	}
	
	public void stop()
	{
		mTimer.stop();
		started = false;
		mIndex = 0;
	}
}
