
public class Route
{
	private int routeNumber; //The route number
	private Service services[]; //An array of all the services for that day
	private int timeSpent;
	  
	/**
	 * @param routeNumber
	 * @param services
	 */
	public Route(int routeNumber, Service[] services)
	{
		this.routeNumber = routeNumber;
		this.services = services;
	}
	
	/**
	 * @param duration
	 * @param noOfServices
	 * @param noOfDrivers
	 * @return the average time per driver per day.
	 */
	public int averageTimePerDriver(int duration, int noOfServices, int noOfDrivers)
	{
	    timeSpent = duration * noOfServices / noOfDrivers; 
		return timeSpent;
	}

	/**
	 * @return the routeNumber
	 */
	public int getrouteNumber()
	{
		return routeNumber;
	}

	/**
	 * @param routeNumber the routeNumber to set
	 */
	public void setrouteNumber(int routeNumber)
	{
		this.routeNumber = routeNumber;
	}

	/**
	 * @return the services
	 */
	public Service[] getServices()
	{
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(Service[] services)
	{
		this.services = services;
	}	
	
}
