import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;


public class Journey
{

	Stop s0 = new Stop("Stockport, Bus Station");
	Stop s1 = new Stop("Stockport, Dialstone Lane/Hillcrest Road");
	Stop s2 = new Stop("Stockport, Lower Bents Lane/Stockport Road");
	Stop s3 = new Stop("Stockport, Asda/Sainsbury's");
	Stop s4 = new Stop("Marple, Offerton Fold");
	Stop s5 = new Stop("Marple, Navigation Hotel");
	Stop s6 = new Stop("Marple, Norfolk Arms");
	Stop s7 = new Stop("Strines, Royal Oak");
	Stop s8 = new Stop("New Mills, Bus Station");
	Stop s9 = new Stop("Low Leighton, Ollerset View Hospital");
	Stop s10 = new Stop("Birch Vale, Grouse Hotel");
	Stop s11 = new Stop("Hayfield, Bus Station");
	Stop s12 = new Stop("Romiley, Corcoran Drive");
	Stop s13 = new Stop("Romiley, Train Station");

	private static int startTime;
	private static boolean routesChecked = true;
	private List<Stop> path;

	Stop[] stops = {s0, s1, s2, s3, s4, s5, s6, s7, s8,
			s9, s10, s11, s12, s13};

	/**
	 * @param startStop
	 * @param endStop
	 * @param startTime
	 */
	public Journey(int startStop, int endStop, int reqStartTime)
	{

		startTime = reqStartTime;

		//Stockport, Bus Station
		s0.setAdj(new Edge[]{ new Edge(s4, 9, 68),
				new Edge(s1, 12, 65),
				new Edge(s3, 5, 66)});


		//Stockport, Dialstone Lane/Hillcrest Road
		s1.setAdj(new Edge[]{ new Edge(s0, 11, 66),
				new Edge(s5, 12, 65)});

		//Stockport, Lower Bents Lane/Stockport Road
		s2.setAdj(new Edge[]{ new Edge(s0, 14, 65),
				new Edge(s13, 8, 66)});

		//Stockport, Asda/Sainsbury's
		s3.setAdj(new Edge[]{ new Edge(s2, 10, 66)});

		//Marple, Offerton Fold
		s4.setAdj(new Edge[]{ new Edge(s0, 9, 67),
				new Edge(s5, 10, 68)});

		//Marple, Navigation Hotel
		s5.setAdj(new Edge[]{ new Edge(s4, 10, 67),
				new Edge(s1, 11, 66),
				new Edge(s6, 4, 65),
				new Edge(s7, 5, 68)});

		//Marple, Norfolk Arms
		s6.setAdj(new Edge[]{ new Edge(s5, 5, 67),
				new Edge(s12, 6, 68)});

		//Strines, Royal Oak
		s7.setAdj(new Edge[]{ new Edge(s5, 5, 67),
				new Edge(s8, 6, 68)});

		//New Mills, Bus Station 
		s8.setAdj(new Edge[]{ new Edge(s7, 6, 67),
				new Edge(s9, 5, 68)});


		//Low Leighton, Ollerset View Hospital
		s9.setAdj(new Edge[]{ new Edge(s8, 4, 67),
				new Edge(s10, 3, 68)});


		//Birch Vale, Grouse Hotel
		s10.setAdj(new Edge[]{ new Edge(s9, 3, 67),
				new Edge(s11, 3, 68)});

		//Hayfield, Bus Station
		s11.setAdj(new Edge[]{ new Edge(s10, 3, 67)});

		//Romiley, Corcoran Drive
		s12.setAdj(new Edge[]{ new Edge(s6, 6, 66),
				new Edge(s13, 5, 65)});

		//Romiley, Train Station
		s13.setAdj(new Edge[]{ new Edge(s12, 4, 66),
				new Edge(s2, 7, 65)});

		System.out.println(getBusCode(this.stops[startStop].getName(), this.stops[startStop].getAdj()[0].getRoute())[0]);
		int calTime = BusStopInfo.displayNextBus(getBusCode(this.stops[startStop].getName(), this.stops[startStop].getAdj()[0].getRoute())[0], startTime)[1];
		System.out.println("First bus: "+calTime);
		this.stops[startStop].setMinDistance(calTime);
		try
		{
			calculatePaths(this.stops[startStop]);

			Stop s = this.stops[endStop];
			//List<Stop> path;

			do
			{
				this.path = getShortestPathTo(s);
			}while(!routesChecked);


			System.out.println("Get the "+calculateTime(path.get(0).getMinDistance())+" "+getRouteName(path.get(0).getRouteTaken())+" service from "+path.get(0).getName());
			for(int i =1; i < path.size(); i++)
			{
				if(i+1 < path.size())
				{
					if(path.get(i).getRouteTaken() != path.get(i+1).getRouteTaken())
					{
						System.out.println("Change at "+path.get(i).getName());
						System.out.println("then get the "+calculateTime(path.get(i).getMinDistance())+" "+getRouteName(path.get(i).getRouteTaken())+" service from "+path.get(i).getName());
					}
				}
				System.out.println("Get the "+calculateTime(path.get(0).getMinDistance())+" "+getRouteName(path.get(0).getRouteTaken())+" service from "+path.get(0).getName());
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("There are no buses at you required time");
		}

	}

	private String getRouteName(int routeTaken)
	{
		if(routeTaken == 65)
			return "383";
		else if(routeTaken == 66)
			return "384";
		else if(routeTaken == 66)
			return "358 out";
		return "358 in";
		
			
	}

	public static List<Stop> getShortestPathTo(Stop target)
	{
		List<Stop> path = new ArrayList<Stop>();
		for(Stop stop = target; stop != null; stop = stop.getPrevious())
		{
			/*
			routesChecked = true;
			try{
				if(currentRoute != stop.getRouteTaken() && !stop.isRouteChecked())
				{
					//get the next bus at stop and add time to stop distance and set to checked
					int calTime = BusStopInfo.displayNextBus(getBusCode(stop.getName(), stop.getRouteTaken())[0], startTime+stop.getMinDistance())[1];
					stop.setMinDistance(calTime);
					System.out.println("Current route: " + currentRoute);
					System.out.println("Changed route: "+ stop.getRouteTaken());
					currentRoute = stop.getRouteTaken();
					stop.setRouteChecked(true);
					routesChecked = false;
					System.out.println("Route change");
					break;
				}
			}
			catch(InvalidQueryException e)
			{
				throw e;
			}*/
			path.add(stop);
		}

		Collections.reverse(path);
		return path;
	}

	public static String calculateTime(int timeInMins)
	{
		int hours = timeInMins/60;
		int minutes = timeInMins%60;
		if(minutes > 9)
		{
			return hours+":"+minutes;
		}
		return hours+":0"+minutes;
	}

	private static int[] getBusCode(String name, int route)
	{
		int[] bus = {0,0};
		int j= 0;
		int[] buses = BusStopInfo.getBusStops(route);
		for(int i = 0; i < buses.length; i++)
		{
			if(BusStopInfo.getFullName(buses[i]).equals(name))
			{
				bus[j] = buses[i];
				j++;
			}
		}
		if(bus[1] == 0)
		{
			int tmp = bus[0];
			int [] other = {tmp};
			return other;
		}
		return bus;
	}

	public static void calculatePaths(Stop startStop)
	{
		//Set the min distance of start stop to 0
		//startStop.setMinDistance(0);
		//Priority queue to store stops
		PriorityQueue<Stop> stopQueue = new PriorityQueue<Stop>();
		startStop.setRouteTaken(startStop.getAdj()[0].getRoute());
		//Add the startStop to the queue
		stopQueue.add(startStop);
		//While the stopQueue is not empty
		while(!stopQueue.isEmpty())
		{
			//Remove the stop from the front of the queue 
			Stop u = stopQueue.poll();
			//For all edges of the stop work out minDistance
			for(Edge edge: u.getAdj())
			{
				Stop stop = edge.getTarget();
				int edgeDistance = findDistanceBetween(u, edge);
				edge.setDistance(edgeDistance);
				//Calculate the min ditance going though u
				int distanceThroughU = u.getMinDistance() + edge.getDistance();
				//If smaller than the current min for stop then set min and previous
				if(distanceThroughU < stop.getMinDistance())
				{
					stop.setRouteTaken(edge.getRoute());
					stopQueue.remove(stop);
					stop.setMinDistance(distanceThroughU);
					stop.setPrevious(u);
					stopQueue.add(stop);
				}
			}
		}
	}

	private static int findDistanceBetween(Stop stop, Edge edge)
	{
		int difference = 0;
		try
		{
			int startTime = stop.getMinDistance();
			int service = BusStopInfo.displayNextBus(getBusCode(stop.getName(), edge.getRoute())[0], stop.getMinDistance())[0];
			difference = BusStopInfo.getTime(service, getBusCode(edge.getTarget().getName(), edge.getRoute())[0]) - startTime;
			if(difference < 0 )
				difference = BusStopInfo.getTime(service, getBusCode(edge.getTarget().getName(), edge.getRoute())[1]) - startTime;
		}catch(InvalidQueryException e)
		{
			return 99999;
		}
		return difference;
	}
}
