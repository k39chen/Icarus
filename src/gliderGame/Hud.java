package gliderGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.math.BigDecimal;

public class Hud 
{
	private GliderGame gameInstance;
	
	public Hud(GliderGame gliderGame)
	{
		gameInstance = gliderGame;
	}
	
	public void update()
	{
	}
	
	public void paint(Graphics g)
	{
		// scale value
		BigDecimal bd = new BigDecimal(Double.toString(gameInstance.glider.distance));
		bd = bd.setScale(1, BigDecimal.ROUND_HALF_DOWN);
		
		// display metrics
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Distance: " + Double.toString(bd.doubleValue()) + "m", 5, 60);
		g.drawString("Speed: " + Double.toString(gameInstance.glider.speed) + "m/frame", 5, 90);
	}
}
