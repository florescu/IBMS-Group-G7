import java.util.Date;


public class Service
{
  private int startTime; //minutes from midnight
  private int endTime; //minutes from midnight
  private String startLocation; // The start location
  private String endLocation; // The end location
  private int duration; //duration time in minutes
  private Driver driver; // the services driver
  private Bus bus; //the services bus
  	
	/**
	 * @param startTime
	 * @param endTime
	 */
	public Service(int startTime, int endTime, int startLocation, int endLocation)
	{
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = calculateDuration(startTime, endTime);
		this.startLocation = BusStopInfo.getFullName(startLocation);
		this.endLocation = BusStopInfo.getFullName(endLocation);
	}
	
	public String toString()
	{
		return "\nService start location: " + this.startLocation + " end location " + this.endLocation + "\n" +
					 "Start Time: "+ this.startTime + "\n"+
					 "End Time: "+ this.endTime + "\n"+
					 "Duration: "+ this.duration+ "\n";
	}
	
	/**
	 * @param startTime
	 * @param endTime
	 * @return the duration
	 */
	public int calculateDuration(int startTime, int endTime)
	{
	    duration = endTime - startTime;
		return duration;
	}
	
	

	/**
	 * @return the startTime
	 */
	public int getStartTime()
	{
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(int startTime)
	{
		this.startTime = startTime;
	}
	
	/**
	 * @return the startLocation
	 */
	public String getStartLocation()
	{
		return startLocation;
	}

	/**
	 * @param startLocation the startLocation to set
	 */
	public void setStartLocation(String startLocation)
	{
		this.startLocation = startLocation;
	}
	
	/**
	 * @return the endLocation
	 */
	public String getEndLocation()
	{
		return endLocation;
	}

	/**
	 * @param endLocation the endLocation to set
	 */
	public void setEndLocation(String endLocation)
	{
		this.endLocation = endLocation;
	}
	
	/**
	 * @return the endTime
	 */
	public int getEndTime()
	{
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(int endTime)
	{
		this.endTime = endTime;
	}

	/**
	 * @return the duration
	 */
	public int getDuration()
	{
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	/**
	 * @return the driver
	 */
	public Driver getDriver()
	{
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(Driver driver)
	{
		this.driver = driver;
	}

	/**
	 * @return the bus
	 */
	public Bus getBus()
	{
		return bus;
	}

	/**
	 * @param bus the bus to set
	 */
	public void setBus(Bus bus)
	{
		this.bus = bus;
	} 
	
	/**
   * Determine whether a service is cancelled. 0 if service wasn't cancelled.
   */
  public static boolean isCancelled(int service)
  {
  	if (database.busDatabase.get_int("service", service, "cancelled") == 0)
  		return false;
  	else 
  		return true;
  }
  
  /**
   * Set a service to cancelled.
   */
  public static void setCancelled(int service, Boolean value)
  {
  	database.busDatabase.set_value("service", service, "cancelled", value);
  }
  
  /**
   * Determine whether a service is delayed on a given date. null if service wasn't delayed, no of minutes otherwise.
   */
  public static int isDelayed(int service)
  {
  	return database.busDatabase.get_int("service", service, "delayedTime");
  }
	
  /**
   * Set a service to delayed by a number of minutes.
   */
  public static void setDelayedTime(int service, Object value)
  {
  	database.busDatabase.set_value("service", service, "delayedTime", value);
  }
  
  /**
   * Get the IDs of all the services in the database
   */
  public static int[] getServices()
  {
    //return database.busDatabase.select_ids("service_id", String source, String order)
  	return database.busDatabase.select_ids("service_id", "service", "service_id");
  }
  
  /**
   * Get the message of a services in the database
   */
  public static String getMessage(int service)
  {
  	return database.busDatabase.get_string("service", service, "message");
  }
  
  /**
   * Set the message of a services in the database
   */
  public static void setMessage(int service, String reason)
  {
  	database.busDatabase.set_value("service", service, "message", reason);
  }
  
  /**
   * See if a service is in databse.
   */
  public static boolean isInDatabase(int service)
  {
  	int[] serviceIDs = getServices();
  	for (int i=0; i<serviceIDs.length; i++)
  		if (service == serviceIDs[i])
  			return true;
		return false;
  }// isInDatabase
  
}
