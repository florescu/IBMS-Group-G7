import java.util.Arrays;

public class Route
{
	private int routeNumber; //The route number
	private Service services[]; //An array of all the services for that day
	private int timeSpent;
	private int totalDuration;
	  
	/**
	 * @param routeNumber
	 */
	public Route(String routeName)
	{
		this.routeNumber = BusStopInfo.findRoute(""+routeName);
		this.services = services;
		int noOfSer = TimetableInfo.getNumberOfServices(this.routeNumber);
		this.services = new Service[noOfSer];
		for(int i = 0; i < noOfSer; i++)
		{
			int servicesDB[] = TimetableInfo.getServiceTimes(this.routeNumber, TimetableInfo.timetableKind.valueOf("weekday"),i);
			for(int j = 0; j < servicesDB.length; j++)
			{
					if (servicesDB[j] < 150)
						servicesDB[j] += 1440;
			}
			Arrays.sort(servicesDB);
			System.out.println(servicesDB[0]+"\t"+ servicesDB[servicesDB.length -1]);
			this.services[i] = new Service(servicesDB[0], servicesDB[servicesDB.length-1]);
			this.totalDuration = this.totalDuration + this.services[i].getDuration();
		}
	}
	
	/**
	 * @param duration
	 * @param noOfServices
	 * @param noOfDrivers
	 * @return the average time per driver per day.
	 */
	public int averageTimePerDriver(int noOfDrivers)
	{
	    timeSpent = this.totalDuration / noOfDrivers; 
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
