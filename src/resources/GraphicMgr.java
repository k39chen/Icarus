package resources;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GraphicMgr 
{
	private static final String path = "assets/img/";
	
	// Prepare a static "cache" mapping font names to Graphic objects.
	private static String[] names = 
	{
		"exclamation.png",
		"life.png",
		"lose.png",
		"obstacle_boomer.png",
		"obstacle_boomer_2.png",
		"obstacle_ceiling.png",
		"obstacle_charger.png",
		"obstacle_floor.png",
		"obstacle_normal.png",
		"obstacle_patrol.png",
		"overlay_lose.png",
		"parallax_1.png",
		"parallax_2.png",
		"parallax_3.png",
		"parallax_bg_1.png",
		"parallax_bg_2.png",
		"parallax_bg_3.png",
		"parallax_fg_1.png",
		"parallax_fg_2.png",
		"parallax_fg_3.png",
		"placeholder.png",
		"player.png",
		"speed_boost_0.png",
		"speed_boost_1.png",
		"speed_boost_2.png",
		"speed_boost_3.png",
		"speed_boost_4.png",
		"win.png"
	};

	private static Map<String, Graphic> cache = new ConcurrentHashMap<String, Graphic>(names.length);
	static 
	{
		for (String name : names) 
		{
			cache.put(name, getGraphic(name));
		}
	}

	public static Graphic getGraphic(String name) 
	{
		Graphic graphic = null;
		if (cache != null) 
		{
			if ((graphic = cache.get(name)) != null) 
			{
				return graphic;
			}
		}
		
		String fName = path + name;
		
		graphic = new Graphic(fName);
		if (!graphic.loadData())
		{
			graphic = getGraphic("placeholder.png");
		}
		
		return graphic;
	}
}