import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Arrays;


public class TestDriverClass
{
	public static void main(String[] args)
	{
		String routeName = "358out";
		database.openBusDatabase();
  	int[] driverIDs = DriverInfo.getDrivers();
  	Driver[] drivers = new Driver[driverIDs.length];
  	for(int i = 0; i < driverIDs.length; i++)
  	{
  		drivers[i] = new Driver(driverIDs[i]);
  	}//for
  	
  	for(int i = 0; i < driverIDs.length; i++)
  	{
  		System.out.println(drivers[i]);
  	}//for
  	
  	Random randomGenerator = new Random();
  	for(int i = 0; i < driverIDs.length; i++)
  	{
  		for(int j = 0; j < 5; j++)
  		{
  			drivers[i].addMinWorkedDay(randomGenerator.nextInt(120)+180);
  		}//for
  	}//for
  	
  	for(int i = 0; i < driverIDs.length; i++)
  	{
  		System.out.println(drivers[i]);
  	}//for
  	
  	//Prints out bus stops for a route
		System.out.println("");
		int busStops[] = BusStopInfo.getBusStops(BusStopInfo.findRoute(routeName));
		System.out.println(routeName);
		for(int i = 0; i < busStops.length; i++)
		{
			System.out.println(BusStopInfo.getFullName(busStops[i]) + " " + busStops[i]);
		}
		
		GregorianCalendar currentDay = new GregorianCalendar(2013,03,15);
	
		Route route1 = new Route(routeName, currentDay);
		Service theServices[] = route1.getServices();
		for(int i = 0; i < theServices.length; i++)
		{
			System.out.println(theServices[i]);
		}
		System.out.println("averageTimePerDriver "+route1.averageTimePerDriver(50));
	
    Roster roster = new Roster(currentDay);
    roster.generateRoster();
    
		System.out.println("Best driver is " + roster.getBestDriver(theServices[1]));
		System.out.println("Best bus is " + roster.getBestBus(theServices[1]));
		
		
			
			
			
		
		/*
		
		for(int i = 0; i < TimetableInfo.getNumberOfServices(BusStopInfo.findRoute(routeName)); i++)
		{
		int services[] = TimetableInfo.getServiceTimes(BusStopInfo.findRoute(routeName), TimetableInfo.timetableKind.valueOf("weekday"),i);
		System.out.println("");
		System.out.println(services.length);
		for(int j = 0; j < services.length; j++)
		{
				if (services[j] < 150)
				{
					services[j] += 1440;
			    
			  }
		}
		Arrays.sort(services);
		for(int j = 0; j < services.length; j++)
		{
		System.out.println(services[j]);
		}
			//System.out.println(services[services.length-1]+"\t"+services[0]);
		}
		*/
	}//main
	
	
}//TestDriverClass
