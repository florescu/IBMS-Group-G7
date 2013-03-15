
public class Service
{
  private int startTime; //minutes from midnight
  private int endTime; //minutes from midnight
  private int duration; //duration time in minutes
  private Driver driver; // the services driver
  private Bus bus; //the services bus
  	
	/**
	 * @param startTime
	 * @param endTime
	 */
	public Service(int startTime, int endTime)
	{
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = calculateDuration(startTime, endTime);
	}
	
	public String toString()
	{
		return "Start Time: "+ this.startTime + "\n"+
					 "End Time: "+ this.endTime + "\n"+
					 "Duration: "+ this.duration;
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
}
