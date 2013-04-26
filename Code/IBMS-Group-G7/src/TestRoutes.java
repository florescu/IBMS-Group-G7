import java.util.GregorianCalendar;


public class TestRoutes
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		database.openBusDatabase();
		int routes[] = BusStopInfo.getRoutes();

		for(int i = 0; i < routes.length; i++)
		{
			System.out.println(routes[i]+ "\t");
			int busStops[] = BusStopInfo.getBusStops(routes[i]);
			for(int j = 0; j < busStops.length; j++)
			{
				System.out.println(BusStopInfo.getFullName(busStops[j])+", "+busStops[j]+", ");
			}
			System.out.println("");
		}

		GregorianCalendar cal = new GregorianCalendar(2013,3,11);
		int services[] = TimetableInfo.getServices(routes[0], TimetableInfo.timetableKind(cal.getTime()));

		System.out.println(routes[0]+ "\t");
		int busStops[] = BusStopInfo.getBusStops(routes[0]);
		for(int j = 0; j < busStops.length; j++)
		{
			if(BusStopInfo.isTimingPoint(busStops[j]))
			{
				System.out.println(BusStopInfo.getFullName(busStops[j])+", "+busStops[j]+", ");
			}
		}
		System.out.println("");
		for(int i = 0; i < services.length; i++)
		{
			System.out.println();
			int serviceTimes[] = TimetableInfo.getServiceTimes(routes[0],i);
			for(int j = 0; j < serviceTimes.length; j ++)
			{
				System.out.print(serviceTimes[j]);
				int stop = TimetableInfo.getTimingPoint(services[i], serviceTimes[j]);
				System.out.println(BusStopInfo.getFullName(stop));
			}
			System.out.println();
		}

	}

}
