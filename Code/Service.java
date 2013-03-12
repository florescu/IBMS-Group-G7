
public class Service
{
  private int startTime; //minutes from midnight
  private int duration; //duration time in minutes
  
	/**
	 * @param startTime
	 * @param duration
	 */
	public Service(int startTime, int duration)
	{
		this.startTime = startTime;
		this.duration = duration;
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
