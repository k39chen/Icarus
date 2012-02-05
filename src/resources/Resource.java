package resources;

public class Resource 
{
	protected String r_url;
	protected String r_name;
	protected Object r_data;
	
	public Resource(Object data)
	{
		r_data = data;
	}
	
	public Resource(String rUrl)
	{
		r_url = rUrl;
		r_data = null;
	}
	
	public String getName() 
	{
		return r_name;
	}
	
	public Object getData() 
	{
		return r_data;
	}
	
	public void setName(String name) 
	{
		this.r_name = name;
	}
	
	public void setData(Object data) 
	{
		this.r_data = data;
	}
}