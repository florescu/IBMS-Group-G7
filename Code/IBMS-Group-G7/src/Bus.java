public class Bus
{
	private int id; //The buses id
	private boolean isOnRoute; //Whether the bus is currently on route
	private int minOnRouteWeek;
	private String location;
	
	/**
	 * @param id
	 */
	public Bus(int id)
	{
		this.id = id;
		this.isOnRoute = false;
	}

	/**
	 * @return the id
	 */
	public int[] getId()
	{
	   return database.busDatabase.select_ids("bus_id", "bus", "number");
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

 /**
   * @return bus is on route?
   */
  public boolean getIsOnRoute()
  {
    return isOnRoute;
  }

  /**
   * @param onRoute
   */
  public void setIsOnRoute(boolean onRoute)
  {
    this.isOnRoute = onRoute;
  }
  
  /**
   * @return bus location
   */
  public String getLocation()
  {
    return location;
  }

  /**
   * @param new location
   */
  public void setLocation(String newLocation)
  {
    this.location = newLocation;
  }

  /**
	 * @return the bus info
	 */
	public String toString()
	{
		return "Bus Id: " + this.id + "\n";
	}
	
	
}//Bus
