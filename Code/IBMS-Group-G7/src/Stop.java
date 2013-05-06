public class Stop implements Comparable<Stop>
{
	private String name;
	private Edge[] adj;
	private int minDistance = Integer.MAX_VALUE;
	private Stop previous;
	
	//Constructor
	public Stop(String name)
	{
		this.name = name;
	}
	
	//to String
	public String toString()
	{
		return this.name;
	}
	
	//Stop comparator 
	public int compareTo(Stop other)
	{
		if(this.minDistance == other.getMinDistance())
			return 0;
		else if(this.minDistance < other.getMinDistance())
			return -1;
		else
			return 1;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the adj
	 */
	public Edge[] getAdj()
	{
		return adj;
	}

	/**
	 * @param adj the adj to set
	 */
	public void setAdj(Edge[] adj)
	{
		this.adj = adj;
	}

	/**
	 * @return the minDistance
	 */
	public int getMinDistance()
	{
		return minDistance;
	}

	/**
	 * @param minDistance the minDistance to set
	 */
	public void setMinDistance(int minDistance)
	{
		this.minDistance = minDistance;
	}

	/**
	 * @return the previous
	 */
	public Stop getPrevious()
	{
		return previous;
	}

	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(Stop previous)
	{
		this.previous = previous;
	}
	
	
}
