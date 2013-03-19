import java.util.Arrays;

public class Route
{
	private int routeNumber; //The route number
	private Service services[]; //An array of all the services for that day
	private int timeSpentDriver;
	private int timeSpentBus;
	private int totalDuration;
	private int noOfServices; //The number of services on a route
	  
	/**
	 * @param routeNumber
	 */
	public Route(String routeName)
	{
		this.routeNumber = BusStopInfo.findRoute(""+routeName);
		//this.services = services;
		this.noOfServices = TimetableInfo.getNumberOfServices(this.routeNumber);
		this.services = new Service[this.noOfServices];
		for(int i = 0; i < this.noOfServices; i++)
		{
			int servicesDB[] = TimetableInfo.getServiceTimes(this.routeNumber, TimetableInfo.timetableKind.valueOf("weekday"),i);
			for(int j = 0; j < servicesDB.length; j++)
			{
					if (servicesDB[j] < 150)
						servicesDB[j] += 1440;
			}
			Arrays.sort(servicesDB);
			int timingPoints[] = TimetableInfo.getTimingPoints(this.routeNumber);
			System.out.println("Start: "+BusStopInfo.getFullName(timingPoints[0])+ "\tEnd: " + BusStopInfo.getFullName(timingPoints[timingPoints.length-1]));
			this.services[i] = new Service(servicesDB[0], servicesDB[servicesDB.length-1], timingPoints[0], timingPoints[timingPoints.length-1]);
			this.totalDuration = this.totalDuration + this.services[i].getDuration();
		}
	}
	
	/**
	 * @param noOfDrivers
	 * @return the average time per driver per day.
	 */
	public int averageTimePerDriver(int noOfDrivers)
	{
	    timeSpentDriver = this.totalDuration / noOfDrivers; 
		return timeSpentDriver;
	}
	
	/**
	 * @param noOfBuses
	 * @return the average time per bus per day.
	 */
	public int averageTimePerBus(int noOfBuses)
	{
	    timeSpentBus = this.totalDuration / noOfBuses; 
		return timeSpentBus;
	}
	
	
	/**
	 * @return the service
	 */
	public Service getService(int i)
	{
		return services[i];
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

	/**
	 * @return the noOfServices
	 */
	public int getNoOfServices()
	{
		return noOfServices;
	}

	/**
	 * @param noOfServices the noOfServices to set
	 */
	public void setNoOfServices(int noOfServices)
	{
		this.noOfServices = noOfServices;
	}	
	
}
