public class Edge
{
	private final Stop target;
	private final int distance;
	
	public Edge(Stop stop, int distance)
	{
		this.target = stop;
		this.distance = distance;
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

	
}
