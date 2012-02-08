package resources;

import global.Global;

import java.awt.Font;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FontMgr 
{
	private static final String path = "/assets/fonts/";
	
	// Prepare a static "cache" mapping font names to Font objects.
	private static String[] names = 
	{
		"droid.ttf",
		"ninjapenguin.ttf",
		"biotype.ttf"
	};

	private static Map<String, Font> cache = new ConcurrentHashMap<String, Font>(names.length);
	static 
	{
		for (String name : names) 
		{
			cache.put(name, getFont(name));
		}
	}
	
	public static Font getFont(String name, int size)
	{
		return getFont(name).deriveFont((float)(Global.ScaleValue(size)));
	}

	public static Font getFont(String name) 
	{
		Font font = null;
		if (cache != null) 
		{
			if ((font = cache.get(name)) != null) 
			{
				return font;
			}
		}
		
		String fName = path + name;
		try 
		{
			InputStream is = FontMgr.class.getResourceAsStream(fName);
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			System.err.println(fName + " not loaded.  Using serif font.");
			font = new Font("serif", Font.PLAIN, 24);
		}
		return font;
	}
}