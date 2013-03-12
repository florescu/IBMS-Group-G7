public class Bus
{
	private int id; //The buses id
	private boolean isOnRoute; //Whether the bus is currently on route
	
	/**
	 * @param id
	 * @param isOnRoute
	 */
	public Bus(int id, boolean isOnRoute)
	{
		this.id = id;
		this.isOnRoute = isOnRoute;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the isOnRoute
	 */
	public boolean isOnRoute()
	{
		return isOnRoute;
	}

	/**
	 * @param isOnRoute the isOnRoute to set
	 */
	public void setOnRoute(boolean isOnRoute)
	{
		this.isOnRoute = isOnRoute;
	}
	
	
	
}//Bus
