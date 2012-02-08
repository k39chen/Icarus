package gliderGame;

import global.Global;

import java.awt.Color;
import java.awt.Graphics;
import java.math.BigDecimal;

import resources.Graphic;
import resources.GraphicMgr;
import resources.Label;

public class Hud 
{
	private boolean setHighScore;
	
	public Hud()
	{
		setHighScore = false;
	}
	
	public void update()
	{
	}
	
	public boolean isNewHighScore(double score)
	{
		if (score > Global.HighestScore)
		{
			Global.HighestScore = score;
			setHighScore = true;
			return true;
		}
		return false;
	}
	
	public void drawLives(Graphics g)
	{
		Graphic life_img = GraphicMgr.getGraphic("life.png");
		int numLives = Global.gliderGame.glider.lives;
		
		int xOffset = (Global.ScreenWidth() - life_img.getWidth() * numLives)/2;
		
		for (int i=0; i<numLives; i++)
		{
			life_img.paint(g, life_img.getWidth() * i + xOffset, 0, new Color(255, 255, 255, 100));
		}
	}
	
	public void paint(Graphics g)
	{	
		drawLives(g);
		
		// scale value
		BigDecimal bd = new BigDecimal(Double.toString(Global.gliderGame.glider.distance));
		bd = bd.setScale(1, BigDecimal.ROUND_HALF_DOWN);
		
		// texts
		String score_text = Double.toString(bd.doubleValue()) + "m";
		String game_over_text = "GAME OVER!";
		String high_score_text = "***NEW HIGH SCORE!***";
		
		if (Global.gliderGame.glider.alive)
		{	
			//------------------------------------------------
			// score text
			Label score_label = new Label(g, score_text, 100);
			
			int score_label_x = (Global.ScreenWidth() - score_label.getWidth()) / 2;
			int score_label_y = score_label.getHeight() + Global.ScaleValue(10);
			int score_boost_level = Global.gliderGame.STAGE_SPEED_BOOST_LEVEL;
			
			Color score_label_color;
			int opacity = (score_boost_level + 1) * 255/(GliderGame.STAGE_SPEED_BOOST_LEVELS.length + 1);
			
			score_label_color = new Color(0, 0, 0, opacity);
			
			GraphicMgr.getGraphic("speed_boost_" + score_boost_level + ".png")
				.paint(g, Global.ScaleValue(600), 0, new Color(255,255,255,opacity));
			
			score_label.paint(g,score_label_x, score_label_y, score_label_color);
		}
		else
		{
			GraphicMgr.getGraphic("overlay_lose.png").paint(g, 0, 0);
			
			//------------------------------------------------
			// game over label
			Label game_over_label = new Label(g, game_over_text, 100);
			
			game_over_label.paint(g, 
				(Global.ScreenWidth() - game_over_label.getWidth()) / 2, 
				(Global.ScreenHeight() - game_over_label.getHeight()) / 2,
				8, 8, Color.red, Color.black
			);
			
			//------------------------------------------------
			// score label
			Label score_label = new Label(g, "DISTANCE: " + score_text, 50);
			
			score_label.paint(g, 
				(Global.ScreenWidth() - score_label.getWidth())/2, 
				game_over_label.y + score_label.getHeight() + Global.ScaleValue(50),
				5, 5, Color.white, Color.black
			);
			
			//------------------------------------------------
			// high score label
			if (isNewHighScore(Global.gliderGame.glider.distance) || setHighScore)
			{
				Label high_score_label = new Label(g, high_score_text, 50);
				
				high_score_label.paint(g, 
					(Global.ScreenWidth() - high_score_label.getWidth()) / 2, 
					score_label.y + high_score_label.getHeight() + Global.ScaleValue(25),
					5, 5, Color.yellow, Color.black
				);
				
				GraphicMgr.getGraphic("win.png").paint(g, 
						Global.ScaleValue(100), Global.ScaleValue(370), 
						Global.ScaleValue(100), Global.ScaleValue(100));
			}
			else
			{
				BigDecimal bd2 = new BigDecimal(Double.toString(Global.HighestScore));
				bd2 = bd2.setScale(1, BigDecimal.ROUND_HALF_DOWN);
				
				Label high_score_label = new Label(g, "TO BEAT: " + Double.toString(bd2.doubleValue()) + "m", 50);
				
				high_score_label.paint(g,
					(Global.ScreenWidth() - high_score_label.getWidth()) / 2, 
					score_label.y + high_score_label.getHeight() + Global.ScaleValue(25),
					5, 5, Color.gray, Color.black
				);
				
				GraphicMgr.getGraphic("lose.png").paint(g, 
						Global.ScaleValue(40), Global.ScaleValue(320), 
						0.5, 0.5);
			}
		}
	}
}
