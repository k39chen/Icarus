package global;

import gliderGame.GliderGame;

import java.applet.Applet;
import java.net.URL;

public class Global 
{
	// application settings
	public static final int SCREEN_WIDTH 		= 960;
	public static final int SCREEN_HEIGHT 		= 640;
	public static final int FRAMES_PER_SECOND 	= 60;
	
	public static final double SCALE			= 1.0;
	public static boolean SHOW_BOUNDING_BOXES	= true;
	public static URL CODE_BASE;
	
	public static final String DEFAULT_FONT		= "biotype.ttf";
	public static final int DEFAULT_FONT_SIZE	= 15;
	
	public static double HighestScore 			= 0;
	
	public static GliderGame gliderGame;
	
	public static Applet GameApplet;
	
	public static final int ScreenWidth() 				{return ScaleValue(SCREEN_WIDTH);}
	public static final int ScreenHeight() 				{return ScaleValue(SCREEN_HEIGHT);}
	public static final int ScaleValue(int x) 			{return DoubleToInt(SCALE * x);}
	public static final double ScaleValueD(double x) 	{return SCALE * x;}
	public static final int DoubleToInt(double x) 		{return (int)x;}
}