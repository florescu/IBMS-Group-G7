import java.util.ArrayList;
import java.util.Collections;
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
	Stop s12 = new Stop("Glossop, Little Hayfield");
	Stop s13 = new Stop("Glossop, Grouse Inn");
	Stop s14 = new Stop("Glossop, Henry Street");
	Stop s15 = new Stop("Romiley, Corcoran Drive");
	Stop s16 = new Stop("Romiley, Train Station");


	Stop[] stops = {s0, s1, s2, s3, s4, s5, s6, s7, s8,
			s9, s10, s11, s12, s13, s14, s15, s16};

	/**
	 * @param startStop
	 * @param endStop
	 * @param startTime
	 */
	public Journey(int startStop, int endStop)
	{
		//Stockport, Bus Station
		s0.setAdj(new Edge[]{ new Edge(s4, 9, 68),
				new Edge(s1, 12, 65),
				new Edge(s3, 5, 66)});


		//Stockport, Dialstone Lane/Hillcrest Road
		s1.setAdj(new Edge[]{ new Edge(s0, 11, 66),
				new Edge(s5, 12, 65)});

		//Stockport, Lower Bents Lane/Stockport Road
		s2.setAdj(new Edge[]{ new Edge(s0, 14, 65),
				new Edge(s16, 8, 66)});

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
				new Edge(s15, 6, 68)});

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
		s11.setAdj(new Edge[]{ new Edge(s10, 3, 67),
				new Edge(s12, 2, 68)});

		//Glossop, Little Hayfield
		s12.setAdj(new Edge[]{ new Edge(s11, 2, 67),
				new Edge(s13, 6, 68)});

		//Glossop, Grouse Inn
		s13.setAdj(new Edge[]{ new Edge(s12, 6, 67),
				new Edge(s14, 7, 68)});

		//Glossop, Henry Street
		s14.setAdj(new Edge[]{ new Edge(s13, 7, 67)});

		//Romiley, Corcoran Drive
		s15.setAdj(new Edge[]{ new Edge(s6, 6, 66),
				new Edge(s16, 5, 65)});

		//Romiley, Train Station
		s16.setAdj(new Edge[]{ new Edge(s15, 4, 66),
				new Edge(s2, 7, 65)});

		calculatePaths(this.stops[startStop]);
		
		Stop s = this.stops[endStop];

		List<Stop> path = getShortestPathTo(s);
		
		System.out.println("Path: " + path);

	}

	public static List<Stop> getShortestPathTo(Stop target)
	{
		List<Stop> path = new ArrayList<Stop>();
		for(Stop stop = target; stop != null; stop = stop.getPrevious())
		{
			System.out.println(stop.getRouteTaken());
			path.add(stop);
		}

		Collections.reverse(path);
		return path;
	}

	public static void calculatePaths(Stop startStop)
	{
		//Set the min distance of start stop to 0
		startStop.setMinDistance(0);
		//Priority queue to store stops
		PriorityQueue<Stop> stopQueue = new PriorityQueue<Stop>();
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
				//Calculate the min ditance going though u
				int distanceThroughU = u.getMinDistance() + edge.getDistance();
				//If smaller than the current min for stop then set min and previous
				if(distanceThroughU < stop.getMinDistance())
				{
					u.setRouteTaken(edge.getRoute());
					stopQueue.remove(stop);
					stop.setMinDistance(distanceThroughU);
					stop.setPrevious(u);
					stopQueue.add(stop);
				}
			}
		}
	}
}
