package global;

import java.net.URL;

public class Global 
{
	// application settings
	public static final int SCREEN_WIDTH 		= 960;
	public static final int SCREEN_HEIGHT 		= 640;
	public static final int FRAMES_PER_SECOND 	= 60;
	
	public static boolean SHOW_BOUNDING_BOXES	= true;
	
	public static URL CODE_BASE;
	
	public static final double SCALE			= 0.5;
	
	public static final int ScaleValue(int x)
	{
		return DoubleToInt(SCALE * x);
	}
	
	public static final int DoubleToInt(double x)
	{
		return (int)x;
	}
}
