
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
	
}
