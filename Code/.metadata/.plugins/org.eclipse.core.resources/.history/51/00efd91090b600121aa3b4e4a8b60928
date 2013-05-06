import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


public class Journey
{

	/**
	 * @param startStop
	 * @param endStop
	 * @param startTime
	 */
	public Journey()
	{
		Stop s0 = new Stop("Stockport, Bus Station");
		Stop s2 = new Stop("Stockport, Dialstone Lane/Hillcrest Road");
		Stop s3 = new Stop("Stockport, Lower Bents Lane/Stockport Road");
		Stop s4 = new Stop("Stockport, Asda/Sainsbury's");
		Stop s5 = new Stop("Marple, Offerton Fold");
		Stop s6 = new Stop("Marple, Navigation Hotel");
		Stop s8 = new Stop("Marple, Norfolk Arms");
		Stop s9 = new Stop("Strines, Royal Oak");
		Stop s10 = new Stop("New Mills, Bus Station");
		Stop s12 = new Stop("Low Leighton, Ollerset View Hospital");
		Stop s14 = new Stop("Birch Vale, Grouse Hotel");
		Stop s15 = new Stop("Hayfield, Bus Station");
		Stop s16 = new Stop("Glossop, Little Hayfield");
		Stop s17 = new Stop("Glossop, Grouse Inn");
		Stop s18 = new Stop("Glossop, Henry Street");
		Stop s19 = new Stop("Romiley, Corcoran Drive");
		Stop s20 = new Stop("Romiley, Train Station");

		//Stockport, Bus Station
		s0.setAdj(new Edge[]{ new Edge(s5, 9),
													new Edge(s2, 12),
													new Edge(s4, 5)});
		
		
		//Stockport, Dialstone Lane/Hillcrest Road
		s2.setAdj(new Edge[]{ new Edge(s0, 11),
													new Edge(s6, 12)});
		
		//Stockport, Lower Bents Lane/Stockport Road
		s3.setAdj(new Edge[]{ new Edge(s0, 14),
													new Edge(s20, 8)});
		
		//Stockport, Asda/Sainsbury's
		s4.setAdj(new Edge[]{ new Edge(s3, 10)});
		
		//Marple, Offerton Fold
		s5.setAdj(new Edge[]{ new Edge(s0, 9),
													new Edge(s6, 10)});
		
		//Marple, Navigation Hotel
		s6.setAdj(new Edge[]{ new Edge(s5, 10),
													new Edge(s2, 11),
													new Edge(s8, 4),
													new Edge(s9, 5)});
		
		//Marple, Norfolk Arms
		s8.setAdj(new Edge[]{ new Edge(s6, 5),
													new Edge(s19, 6)});
		
		//Strines, Royal Oak
		s9.setAdj(new Edge[]{ new Edge(s6, 5),
													new Edge(s10, 6)});
		
		//New Mills, Bus Station 
		s10.setAdj(new Edge[]{ new Edge(s9, 6),
													 new Edge(s12, 5)});
		
		
		//Low Leighton, Ollerset View Hospital
		s12.setAdj(new Edge[]{ new Edge(s10, 4),
													 new Edge(s14, 3)});
		
		
		//Birch Vale, Grouse Hotel
		s14.setAdj(new Edge[]{ new Edge(s12, 3),
													 new Edge(s15, 3)});
		
		//Hayfield, Bus Station
		s15.setAdj(new Edge[]{ new Edge(s14, 3),
													 new Edge(s16, 2)});
		
		//Glossop, Little Hayfield
		s16.setAdj(new Edge[]{ new Edge(s15, 2),
													 new Edge(s17, 6)});
		
		//Glossop, Grouse Inn
		s17.setAdj(new Edge[]{ new Edge(s16, 6),
													 new Edge(s18, 7)});
		
		//Glossop, Henry Street
		s18.setAdj(new Edge[]{ new Edge(s17, 7)});
		
		//Romiley, Corcoran Drive
		s19.setAdj(new Edge[]{ new Edge(s8, 6),
													 new Edge(s20, 5)});
		
		//Romiley, Train Station
		s20.setAdj(new Edge[]{ new Edge(s19, 4),
													 new Edge(s3, 7)});
		
		Stop[] stops = {s0, s2, s3, s4, s5, s6, s8, s8, s9, s10,
										s12, s14, s15, s16, s17, s18, s19, s20};
		
		calculatePaths(s0);
		
		for(Stop s : stops)
		{
			System.out.println("Distance to " + s + ": " + s.getMinDistance());
	    List<Stop> path = getShortestPathTo(s);
	    System.out.println("Path: " + path);
		}
	}
	
	public static List<Stop> getShortestPathTo(Stop target)
	{
		List<Stop> path = new ArrayList<Stop>();
		for(Stop stop = target; stop != null; stop = stop.getPrevious())
		{
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
					stopQueue.remove(stop);
					stop.setMinDistance(distanceThroughU);
					stop.setPrevious(u);
					stopQueue.add(stop);
				}
			}
		}
	}
}
