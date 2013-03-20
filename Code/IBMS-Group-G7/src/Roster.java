import java.util.*;
import java.math.*;


public class Roster
{
	private Driver drivers[]; //An array of the available drivers
	private Bus buses[]; //An array for the available buses 
	private Route	theRoutes[];
	private int averageTimePerDriver;
	private GregorianCalendar currentDay; //The date to generate roster for
	public final int MIN_IN_DAY = 1440;
  private int time = 0;

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
		System.out.println("buses length: "+buses.length);

		this.currentDay = currentDay;

		//Getting all the available drivers
		int[] driverIDs = DriverInfo.getDrivers();

		for (int i = 0; i < driverIDs.length; i++)
		{
			Driver newDriver = new Driver(driverIDs[i]);
			//newDriver.setMinWorkedWeek(0);
			//newDriver.saveMinWorkedWeek();
			DriverInfo.setHoursThisWeek(newDriver.getId(), 0);
		}

		int driversLength = 0;
		for(int i = 0; i < driverIDs.length; i++)
			if (DriverInfo.isAvailable(driverIDs[i]))
				driversLength++;
		this.drivers = new Driver[driversLength];
		for(int i = 0; i < driversLength; i++) 
		{
			drivers[i] = new Driver(driverIDs[i]);
		}

		calculateAverage();
	}

	public void createRoutes()
	{
		//Setting the current day
		this.theRoutes = new Route[4];
		this.theRoutes[0] = new Route("358out", currentDay);
		this.theRoutes[1] = new Route("358back", currentDay);
		this.theRoutes[2] = new Route("383", currentDay);
		this.theRoutes[3] = new Route("384", currentDay);
	}

	public void calculateAverage()
	{
		int totalDuration = 0;
		for(int days = 0; days < 7; days++)
		{
			createRoutes();
			for(int route = 0; route < theRoutes.length; route++)
			{
				totalDuration += theRoutes[route].getTotalDuration();
			}//loop over routes	
			currentDay.add(Calendar.DAY_OF_YEAR, 1);
		}//loop over days
		int average = totalDuration / 70;
		System.out.println("Average: " + average);
		this.averageTimePerDriver = average;
	}

	public void generateRoster()
	{
		for(int days = 0; days < 7; days++)
		{
			startDayDriverBus();
			createRoutes();
			for(int min = 160; min < MIN_IN_DAY+160; min ++)
			{
				for(int route = 0; route < theRoutes.length; route++)
				{
					for(int services = 0; services < theRoutes[route].getNoOfServices(); services++)
					{
						if(theRoutes[route].getService(services).getStartTime() == min)
						{
							//get most appropriate driver
							Driver bestDriver = getBestDriver(theRoutes[route].getService(services));
							time++;
							System.out.println("Best driver: " + bestDriver + " time " + time);
							theRoutes[route].getService(services).setDriver(bestDriver);
							bestDriver.setOnRoute(true);

							//get most appropriate bus
							Bus bestBus = getBestBus(theRoutes[route].getService(services));
							theRoutes[route].getService(services).setBus(bestBus);
							bestBus.setIsOnRoute(true);
						}//if a service is going to start at min
						if(theRoutes[route].getService(services).getEndTime() == min)
						{
							//set driver to available
							Driver endDriver = theRoutes[route].getService(services).getDriver();
							endDriver.setOnRoute(false);
							endDriver.setLocation(null);
							//endDriver.addMinWorkedDay(theRoutes[route].getService(services).getDuration());
							int hoursSoFar = DriverInfo.getHoursThisWeek(endDriver.getId());
							hoursSoFar += theRoutes[route].getService(services).getDuration();
							System.out.println("End driver" + endDriver + "Hours so far: " + hoursSoFar);
							DriverInfo.setHoursThisWeek(endDriver.getId(), hoursSoFar);
							System.out.println("New balance: " + DriverInfo.getHoursThisWeek(endDriver.getId()));
							//DriverInfo.setHoursThisWeek(endDriver.getId(), hoursSoFar + theRoutes[route].getService(services).getDuration());
							//set bus to available
							Bus endBus = theRoutes[route].getService(services).getBus();
							endBus.setIsOnRoute(false);
							endBus.setLocation(null);
						}//if the service is going to end at min
					}//loop over the services
				}//loop over routes	
			}//loop over minutes of day
			//for (int i = 0; i<drivers.length; i++)
				//drivers[i].saveMinWorkedWeek();
				
			currentDay.add(Calendar.DAY_OF_YEAR, 1);
		}//loop over days
		int tot=0;
		int driverIDs[] = DriverInfo.getDrivers();
		drivers = new Driver[driverIDs.length];
		for (int i = 0; i<driverIDs.length; i++)
		{
			drivers[i] = new Driver(driverIDs[i]);
			//tot += drivers[i].getMinWorkedWeek();
			tot += DriverInfo.getHoursThisWeek(drivers[i].getId());
		}
		System.out.println("tot: "+tot);
		printDay();
	}//generateRoster

	/**
	 * @return the best fit driver
	 */
	public Driver getBestDriver(Service service)
	{
		int count = 0;
		Driver fitDriver = null;

		for (int i = 0; i < drivers.length; i++)
			if ((!drivers[i].isOnRoute()))
				count++;

		int noOfPosDrivers = 0;
		int bestMins = 534232324;		
		for (int i = 0; i < drivers.length; i++)  
			if ((!drivers[i].isOnRoute()) && (drivers[i].getMinWorkedDay() < 300) &&  
					(DriverInfo.getHoursThisWeek(drivers[i].getId()) < averageTimePerDriver) &&
					((drivers[i].getLocation()==null) || 
					(drivers[i].getLocation().equals(service.getStartLocation()))))
			{
				int bestMinutes = averageTimePerDriver-DriverInfo.getHoursThisWeek(drivers[i].getId())-service.getDuration();
				if (bestMinutes > 0 && bestMinutes < bestMins)
				{
					bestMins = bestMinutes;
					fitDriver = drivers[i];
					noOfPosDrivers++;
					//System.out.println("min " + bestMinutes);
				}
			}//if
		if(noOfPosDrivers == 0)
		{
			bestMins = 12312321;	
			for (int i = drivers.length-1; i >= 0; i--)  
				if ((!drivers[i].isOnRoute()) &&  (DriverInfo.getHoursThisWeek(drivers[i].getId()) < 300) &&
						((drivers[i].getLocation()==null) || 
						(drivers[i].getLocation().equals(service.getStartLocation()))))
				{
					int bestMinutes = DriverInfo.getHoursThisWeek(drivers[i].getId());
					if (bestMinutes == 0)
					{
						fitDriver = drivers[i];
						break;
					}
					if (bestMins > bestMinutes)
					{
						bestMins = bestMinutes;
						fitDriver = drivers[i];
						return drivers[i];
						//System.out.println("min " + bestMinutes);
					}
				}//if
		}

		/*for (int i = 0; i < drivers.length; i++)  
			if ((!drivers[i].isOnRoute()) &&
					((drivers[i].getLocation()==null) || 
							(drivers[i].getLocation().equals(service.getStartLocation()))))
			{
				if ((drivers[i].getMinWorkedWeek() >= averageTimePerDriver)
				   && (drivers[drivers.length-i-1].getMinWorkedWeek() < averageTimePerDriver))
				{
						fitDriver = drivers[drivers.length-i-1];
						//System.out.println("min " + bestMinutes);
				}
				else 
					fitDriver = drivers[i];
			}//if*/
			/*for (int i = 0; i < drivers.length; i++)  
			    if ((!drivers[i].isOnRoute()) && (drivers[i].getMinWorkedDay()<300) &&
			    		(DriverInfo.getHoursThisWeek(drivers[i].getId()) < averageTimePerDriver) &&
				    ((drivers[i].getLocation()==null) || 
				    (drivers[i].getLocation().equals(service.getStartLocation()))))
				{    
				    return drivers[i];
		        }*/
		 
		
		//System.out.println("For service " + service + " best driver is: " + fitDriver);
		return fitDriver;
	}

	/**
	 * @return the best fit bus
	 */
	public Bus getBestBus(Service service)
	{
		Bus fitBus = null;

		for (int i = 0; i < buses.length; i++)
			if ((!buses[i].getIsOnRoute()) && ((buses[i].getLocation()==null) || 
					(buses[i].getLocation().equals(service.getStartLocation()))))
			{	
				fitBus = buses[i];
			}
		return fitBus;
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

	public void printDay()
	{
		System.out.println("Day: " + currentDay.get(Calendar.DAY_OF_MONTH)+"/"+currentDay.get(Calendar.MONTH)+"/"+currentDay.get(Calendar.YEAR));
		for (int i=0; i<drivers.length; i++)
			System.out.println(drivers[i]);
	}

}//Roster
