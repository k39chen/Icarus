package resources;

import java.util.HashMap;
import java.util.Map;

public class GraphicsCache
{
	private static GraphicsCache instance = null;
	
	private Map<String, Graphic> gs_stack;
	
	protected GraphicsCache()
	{
		gs_stack = new HashMap<String, Graphic>();
	}
	
	public static GraphicsCache GetInstance()
	{
		if (instance == null)
		{
			instance = new GraphicsCache();
		}
		return instance;
	}
	
	public Graphic loadGraphic(String url)
	{
		if (gs_stack.containsKey(url))
		{
			return gs_stack.get(url);
		}
		
		Graphic loadedGraphic = new Graphic(url);
		
		gs_stack.put(url, loadedGraphic);
		
		return loadedGraphic;
	}
}