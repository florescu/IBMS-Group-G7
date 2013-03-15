import java.util.*;
import static java.util.Calendar.*;


public class Roster
{
	private Driver availableDrivers[]; //An array of the available drivers
	private OnRouteDriver onRouteDrivers[]; //An array of the drivers on route
	private Bus availableBus[]; //An array for the available buses 
	private Bus onRouteBus[]; //An array for the buses on route
	private Route	theRoutes[];
	private GregorianCalendar startDate; //The date to generate roster for
	public final int MIN_IN_DAY = 1440;
	
	/**
	 * @param availableDrivers
	 * @param onRouteDrivers
	 * @param availableBus
	 * @param onRouteBus
	 * @param theDate
	 */
	public Roster(Driver[] availableDrivers, OnRouteDriver[] onRouteDrivers,
			Bus[] availableBus, Bus[] onRouteBus, GregorianCalendar theDate)
	{
		this.availableDrivers = availableDrivers;
		this.onRouteDrivers = onRouteDrivers;
		this.availableBus = availableBus;
		this.onRouteBus = onRouteBus;
		this.startDate = theDate;
	}//Constructors

	public void generateRoster()
	{
		for(int days = 0; days < 7; days++)
		{
			for(int min = 160; min < MIN_IN_DAY+160; min ++)
			{
				for(int route = 0; route < theRoutes.length; route++)
				{
					for(int services = 0; services < theRoutes[route].getNoOfServices(); services++)
					{
						if(theRoutes[route].getService(services).getStartTime() == min)
						{
							//get most appropriate driver
							//get most appropriate bus
						}//if a service is going to start at min
						if(theRoutes[route].getService(services).getStartTime() == min)
					}//loop over the services
				}//loop over routes	
			}//loop over minutes of day
		}//loop over days
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
	public GregorianCalendar getTheDate()
	{
		return startDate;
	}

	/**
	 * @param theDate the theDate to set
	 */
	public void setTheDate(GregorianCalendar theDate)
	{
		this.startDate = theDate;
	}
	
	
}//Roster
