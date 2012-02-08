package resources;

import global.Global;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Label
{
	public String text;
	private Font font;
	private FontMetrics fm;
	public int size;
	
	public int x;
	public int y;
	
	public Label(Graphics g, String rText)
	{
		text = rText;
		font = FontMgr.getFont(Global.DEFAULT_FONT, Global.DEFAULT_FONT_SIZE);
		fm = g.getFontMetrics(font);
	}
	
	public Label(Graphics g, String rText, int rSize)
	{
		text = rText;
		font = FontMgr.getFont(Global.DEFAULT_FONT, rSize);
		fm = g.getFontMetrics(font);
	}
	
	public Label(Graphics g, String fontId, String rText, int rSize)
	{
		text = rText;
		font = FontMgr.getFont(fontId, rSize);
		fm = g.getFontMetrics(font);
	}
	
	public void paint(Graphics g, int x, int y)
	{
		this.x = x;
		this.y = y;
		
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(text, x, y);
	}
	
	public void paint(Graphics g, int x, int y, Color color)
	{
		this.x = x;
		this.y = y;
		
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, x, y);
	}
	
	public void paint(Graphics g, int x, int y, int xOffset, int yOffset, Color fgColor, Color bgColor)
	{
		this.x = x;
		this.y = y;
		
		g.setFont(font);
		
		g.setColor(bgColor);
		g.drawString(text, x + xOffset, y + yOffset);
		
		g.setColor(fgColor);
		g.drawString(text, x, y);
	}
	
	public void paint(Graphics g, int x, int y, int outlineThickness, Color fgColor, Color bgColor)
	{
		this.x = x;
		this.y = y;
		
		g.setFont(font);
		
		g.setColor(bgColor);
		for (int i=0; i<outlineThickness; i++) g.drawString(text, x - i, y - 0);
		for (int i=0; i<outlineThickness; i++) g.drawString(text, x + i, y - 0);
		for (int i=0; i<outlineThickness; i++) g.drawString(text, x - 0, y - i);
		for (int i=0; i<outlineThickness; i++) g.drawString(text, x - 0, y + i);
		for (int i=0; i<outlineThickness; i++) g.drawString(text, x - i, y - i);
		for (int i=0; i<outlineThickness; i++) g.drawString(text, x - i, y + i);
		for (int i=0; i<outlineThickness; i++) g.drawString(text, x + i, y - i);
		for (int i=0; i<outlineThickness; i++) g.drawString(text, x + i, y + i);
		
		g.setColor(fgColor);
		g.drawString(text, x, y);
	}
	
	public int getWidth()
	{
		return fm.stringWidth(text);
	}
	
	public int getHeight()
	{
		return fm.getHeight();
	}
}