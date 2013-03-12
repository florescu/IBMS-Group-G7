import java.util.Date;

public class Roster
{
	private Driver availableDrivers[]; //An array of the available drivers
	private OnRouteDriver onRouteDrivers[]; //An array of the drivers on route
	private Bus availableBus[]; //An array for the available buses 
	private Bus onRouteBus[]; //An array for the buses on route
	private Date theDate; //The date to generate roster for
	public final int MIN_IN_DAY = 1440;
	
	/**
	 * @param availableDrivers
	 * @param onRouteDrivers
	 * @param availableBus
	 * @param onRouteBus
	 * @param theDate
	 */
	public Roster(Driver[] availableDrivers, OnRouteDriver[] onRouteDrivers,
			Bus[] availableBus, Bus[] onRouteBus, Date theDate)
	{
		this.availableDrivers = availableDrivers;
		this.onRouteDrivers = onRouteDrivers;
		this.availableBus = availableBus;
		this.onRouteBus = onRouteBus;
		this.theDate = theDate;
	}//Constructors

	public void generateRoster()
	{
		
	}//generateRoster
	
	
	
	
	/**
	 * @return the availableDrivers
	 */
	public Driver[] getAvailableDrivers()
	{
		return availableDrivers;
	}

	/**
	 * @param availableDrivers the availableDrivers to set
	 */
	public void setAvailableDrivers(Driver[] availableDrivers)
	{
		this.availableDrivers = availableDrivers;
	}

	/**
	 * @return the onRouteDrivers
	 */
	public OnRouteDriver[] getOnRouteDrivers()
	{
		return onRouteDrivers;
	}

	/**
	 * @param onRouteDrivers the onRouteDrivers to set
	 */
	public void setOnRouteDrivers(OnRouteDriver[] onRouteDrivers)
	{
		this.onRouteDrivers = onRouteDrivers;
	}

	/**
	 * @return the availableBus
	 */
	public Bus[] getAvailableBus()
	{
		return availableBus;
	}

	/**
	 * @param availableBus the availableBus to set
	 */
	public void setAvailableBus(Bus[] availableBus)
	{
		this.availableBus = availableBus;
	}

	/**
	 * @return the onRouteBus
	 */
	public Bus[] getOnRouteBus()
	{
		return onRouteBus;
	}

	/**
	 * @param onRouteBus the onRouteBus to set
	 */
	public void setOnRouteBus(Bus[] onRouteBus)
	{
		this.onRouteBus = onRouteBus;
	}

	/**
	 * @return the theDate
	 */
	public Date getTheDate()
	{
		return theDate;
	}

	/**
	 * @param theDate the theDate to set
	 */
	public void setTheDate(Date theDate)
	{
		this.theDate = theDate;
	}
	
	
}//Roster
