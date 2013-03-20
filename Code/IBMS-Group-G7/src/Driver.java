public class Driver
{
	private int id; //The drivers id
	private String name; //The drivers name
	private int minWorkedWeek; //The minutes they have worked this week
	private int minWorkedDay; //The minutes they have worked today
	private boolean isOnRoute;
	private String location; //The location of the driver

	/**
	 * @param id
	 * @param minWorkedDay
	 */
	public Driver(int id)
	{
		this.id = id;
		this.name = DriverInfo.getName(id);
		this.isOnRoute = false;
		this.location = null;
		this.minWorkedWeek = DriverInfo.getHoursThisWeek(id);
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

	public void addMinWorkedDay(int mins)
	{
		this.minWorkedDay = this.minWorkedDay + mins;
		this.minWorkedWeek = this.minWorkedWeek + mins;
	}
	
	public void saveMinWorkedWeek()
	{
    DriverInfo.setHoursThisWeek(this.id, this.minWorkedWeek);
	}
	
	/**
	 * @return the driver info
	 */
	public String toString()
	{
		return "Driver Id: " + this.id + "\n"+
		       "Name: "+ this.name + "\n"+
		       "Minutes worked this week: "+ DriverInfo.getHoursThisWeek(this.id) + "\n";
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
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * @return the location
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * @param name the new location
	 */
	public void setLocation(String newLocation)
	{
		this.location = newLocation;
	}

	/**
	 * @return the minWorkedWeek
	 */
	public int getMinWorkedWeek()
	{
		return minWorkedWeek;
	}

	/**
	 * @param minWorkedWeek the minWorkedWeek to set
	 */
	public void setMinWorkedWeek(int minWorkedWeek)
	{
		this.minWorkedWeek = minWorkedWeek;
	}

	/**
	 * @return the minWorkedDay
	 */
	public int getMinWorkedDay()
	{
		return minWorkedDay;
	}

	/**
	 * @param minWorkedDay the minWorkedDay to set
	 */
	public void setMinWorkedDay(int minWorkedDay)
	{
		this.minWorkedDay = minWorkedDay;
	}
	
	
	
	
}//Driver
