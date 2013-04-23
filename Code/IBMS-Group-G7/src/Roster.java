import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class Roster
{
	private Driver drivers[]; //An array of the available drivers
	private Bus buses[]; //An array for the available buses 
	private Route	theRoutes[];
	private int averageTimePerDriver;
	private GregorianCalendar currentDay; //The date to generate roster for
	private String weekString;
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

		this.currentDay = currentDay;
		this.weekString = currentDay.get(Calendar.DAY_OF_MONTH)+"_"+currentDay.get(Calendar.MONTH)+"_"+currentDay.get(Calendar.YEAR);
		
		boolean success = (new File("reports/"+weekString)).mkdirs();
		if (!success)
		{
		   System.out.println("Cannot make directory "+ weekString); 
		}
		success = (new File("reports/"+weekString+"/drivers").mkdirs());
		if (!success)
		{
		   System.out.println("Cannot make directory drivers"); 
		}
		//Getting all the available drivers
		int[] driverIDs = DriverInfo.getDrivers();

		for (int i = 0; i < driverIDs.length; i++)
		{
			Driver newDriver = new Driver(driverIDs[i]);
			DriverInfo.setHoursThisWeek(newDriver.getId(), 0);
			try
			{
				File file = new File("reports/"+weekString+"/drivers/"+newDriver.getId());
				success = (file.createNewFile());
				FileOutputStream fop = new FileOutputStream(file);
				String content = newDriver.getName()+" - " +weekString+"\n\n";
				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				// get the content in bytes
				byte[] contentInBytes = content.getBytes();

				fop.write(contentInBytes);
				fop.flush();
				fop.close();
			} catch (IOException e)
			{
				System.out.println("Cannot make file "+newDriver.getId());
				e.printStackTrace();
			}
			
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
			}
			currentDay.add(Calendar.DAY_OF_YEAR, 1);
		}
		int average = totalDuration / 70;
		average=905;
		this.averageTimePerDriver = average;
	}

	public void generateRoster()
	{
		double currentProgress = 0;
		double progress = (double)7/(double)MIN_IN_DAY;
		for(int days = 0; days < 7; days++)
		{
			startDayDriverBus();
			createRoutes();
			for(int min = 160; min < MIN_IN_DAY+160; min ++)
			{
				currentProgress = currentProgress+progress;
				if(currentProgress >= 1)
				{
					System.out.print("*");
					currentProgress =currentProgress-1;
				}
				for(int route = 0; route < theRoutes.length; route++)
				{
					for(int services = 0; services < theRoutes[route].getNoOfServices(); services++)
					{
						if(theRoutes[route].getService(services).getStartTime() == min)
						{
							//get most appropriate driver
							Driver bestDriver = getBestDriver(theRoutes[route].getService(services));
							appendDriverReport(bestDriver.getId(), theRoutes[route].getService(services).toString());
							time++;
							theRoutes[route].getService(services).setDriver(bestDriver);
							bestDriver.setOnRoute(true);

							//get most appropriate bus
							Bus bestBus = getBestBus(theRoutes[route].getService(services));
							theRoutes[route].getService(services).setBus(bestBus);
							bestBus.setIsOnRoute(true);
						}
						if(theRoutes[route].getService(services).getEndTime() == min)
						{
							//set driver to available
							Driver endDriver = theRoutes[route].getService(services).getDriver();
							endDriver.setOnRoute(false);
							endDriver.setLocation(null);
							int hoursSoFar = DriverInfo.getHoursThisWeek(endDriver.getId());
							hoursSoFar += theRoutes[route].getService(services).getDuration();
							DriverInfo.setHoursThisWeek(endDriver.getId(), hoursSoFar);
							//set bus to available
							Bus endBus = theRoutes[route].getService(services).getBus();
							endBus.setIsOnRoute(false);
							endBus.setLocation(null);
						}
					}
				}	
			}

			currentDay.add(Calendar.DAY_OF_YEAR, 1);
		}//loop over days
		System.out.println("");
		int tot=0;
		int driverIDs[] = DriverInfo.getDrivers();
		drivers = new Driver[driverIDs.length];
		for (int i = 0; i<driverIDs.length; i++)
		{
			drivers[i] = new Driver(driverIDs[i]);
			tot += DriverInfo.getHoursThisWeek(drivers[i].getId());
		}
		generateReport();
		//printDay();
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
				}
			}//if
		if(noOfPosDrivers == 0)
		{
			bestMins = 12312321;	
			for (int i = drivers.length-1; i >= 0; i--)  
				if ((!drivers[i].isOnRoute()) && (drivers[i].getMinWorkedDay() < 300) &&  
						(DriverInfo.getHoursThisWeek(drivers[i].getId()) < averageTimePerDriver) &&
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
					}
				}//if
		}
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
		for(int driver = 0; driver < drivers.length; driver++)
		{
			drivers[driver].setMinWorkedDay(0);
			drivers[driver].setOnRoute(false);
		}
		for(int bus = 0; bus < buses.length; bus++)
		{
			buses[bus].setIsOnRoute(false);
		}
	}

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

	public void appendDriverReport(int id, String content)
	{
		FileOutputStream fop = null;
		File file;

		try {

			file = new File("reports/"+weekString ,"drivers/"+id);
			fop = new FileOutputStream(file, true);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();


		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void generateReport()
	{
		FileOutputStream fop = null;
		File file;
		String content = "Week "+weekString +"\n";

		try {

			file = new File("reports/"+weekString ,"all_drivers.txt");
			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			
			for (int i=0; i<drivers.length; i++)
			{
				content = drivers[i].toString();
				contentInBytes = content.getBytes();
				fop.write(contentInBytes);
				fop.flush();
			}
			
			
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void printDay()
	{
		System.out.println("Day: " + currentDay.get(Calendar.DAY_OF_MONTH)+"/"+currentDay.get(Calendar.MONTH)+"/"+currentDay.get(Calendar.YEAR));
		for (int i=0; i<drivers.length; i++)
			System.out.println(drivers[i]);
	}

}//Roster
