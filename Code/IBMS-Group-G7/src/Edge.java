public class Edge
{
	private final Stop target;
	private int distance;
	private int route;
	
	public Edge(Stop stop, int distance, int route)
	{
		this.target = stop;
		this.distance = distance;
		this.route = route;
	}

	/**
	 * @return the target
	 */
	public Stop getTarget()
	{
		return target;
	}



	/**
	 * @return the distance
	 */
	public int getDistance()
	{
		return distance;
	}

	public void setDistance(int distance)
	{
		this.distance = distance;
	}

	public int getRoute()
	{
		return route;
	}

	public void setRoute(int route)
	{
		this.route = route;
	}

	
}
