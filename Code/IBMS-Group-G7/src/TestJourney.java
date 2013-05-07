
public class TestJourney
{
	public static void main(String[] args)
	{
		database.openBusDatabase();
		Journey j = new Journey(0,6);
		
		
		int[] result = BusStopInfo.displayNextBus(770, 700);
		
		System.out.println(result[0]);
		System.out.println(result[1]);
		
		int[] routes = BusStopInfo.getRoutes();
		for(int i = 0; i < routes.length; i++)
		{
			System.out.println(BusStopInfo.findBusStop("SKP","Bus Station"));
		}
	}

}
