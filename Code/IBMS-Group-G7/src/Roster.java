import java.util.*;


public class Roster
{
	private Driver drivers[]; //An array of the available drivers
	private Bus buses[]; //An array for the available buses 
	private Route	theRoutes[];
	private Driver bestDriver;
	private Bus bestBus;
	private GregorianCalendar currentDay; //The date to generate roster for
	public final int MIN_IN_DAY = 1440;
	

	/**
	 * @param currentDay
	 */
	public Roster(GregorianCalendar currentDay)
	{
		//Getting all the buses
		int[] busIDs = BusInfo.getBuses();
		this.buses = new Bus[busIDs.length];
		for(int i = 0; i < buses.length; i++)
		{
			this.buses[i] = new Bus(busIDs[i]);
		}
		
		//Getting all the routes
		this.theRoutes = new Route[1];
		this.theRoutes[0] = new Route("358out");
		
		//Setting the current day
		this.theRoutes = new Route[2];
		this.theRoutes[0] = new Route("358out");
		this.theRoutes[1] = new Route("358back");
		this.currentDay = currentDay;
		
		//Getting all the available drivers
		int[] driverIDs = DriverInfo.getDrivers();
    int driversLength = 0;
		for(int i = 0; i < driverIDs.length; i++)
			if (DriverInfo.isAvailable(driverIDs[i]))
				driversLength++;
		this.drivers = new Driver[driversLength];
		for(int i = 0; i < driversLength; i++) 
		  drivers[i] = new Driver(driverIDs[i]);
	}

	public void generateRoster()
	{
		for(int days = 0; days < 7; days++)
		{
			startDayDriverBus();
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
						{
							//set driver to available
							//set bus to available
						}//if the service is going to end at min
					}//loop over the services
				}//loop over routes	
			}//loop over minutes of day
			currentDay.add(Calendar.DAY_OF_YEAR, 1);
		}//loop over days
	}//generateRoster
	
	/**
	 * @return the best fit driver
	 */
	public Driver getBestDriver()
	{
		int count = 0;
		Driver fitDriver = null;
		
		for (int i = 0; i < drivers.length; i++)
		  if ((!drivers[i].isOnRoute()))
				count++;
		
		for (int i = 0; i < drivers.length; i++)  
		  for (int route = 0; route < theRoutes.length; route++)
				if ((!drivers[i].isOnRoute()) && (drivers[i].getMinWorkedDay() < theRoutes[route].averageTimePerDriver(count)))
				{
					//System.out.println(theRoutes[route].averageTimePerDriver(count));
	      	fitDriver = drivers[i];
				}
		bestDriver = fitDriver;
		return bestDriver;
	}
	
	/**
	 * @return the best fit bus
	 */
	public Bus getBestBus()
	{
		Bus fitBus = null;
		
		for (int i = 0; i < buses.length; i++)
		  if ((!buses[i].getIsOnRoute()))
		  {	
				fitBus = buses[i];
		  }
		
		bestBus = fitBus;
		return bestBus;
	}
	
	public void startDayDriverBus()
	{
		int theDriverIDs[] = getAvailableDrivers(this.currentDay);
		this.drivers = new Driver[theDriverIDs.length];
		for(int i = 0; i < drivers.length; i++)
		{
			this.drivers[i] = new Driver(theDriverIDs[i]);
		}
	  //loop through drivers and add min worked day to min worked week and set min worked day to 0
		for(int driver = 0; driver < drivers.length; driver++)
		{
			//Adding the mins worked for the previous day to the week total
			drivers[driver].setMinWorkedWeek(drivers[driver].getMinWorkedWeek()+drivers[driver].getMinWorkedDay());
			drivers[driver].setMinWorkedDay(0);
			drivers[driver].setOnRoute(false);
		}
		for(int bus = 0; bus < buses.length; bus++)
		{
			buses[bus].setIsOnRoute(false);
		}
	}//Setting up the drivers and buses day
	
	public static int[] getAvailableDrivers(GregorianCalendar date)
	{
		int availableDrivers = 0;
		int driverIDs[] = DriverInfo.getDrivers();
		for(int i = 0; i < driverIDs.length; i ++)
		{
			if(DriverInfo.isAvailable(driverIDs[i], date.getTime()))
				availableDrivers++;
		}
		int availableDriverIDs[] = new int[availableDrivers];
		int j = 0;
		for(int i = 0; i < driverIDs.length; i ++)
		{
			if(DriverInfo.isAvailable(driverIDs[i], date.getTime()))
			{
				availableDriverIDs[j] = driverIDs[i];
				j++;
			}
		}
		return availableDriverIDs;
	}//getAvailableDrivers
	
	
}//Roster
