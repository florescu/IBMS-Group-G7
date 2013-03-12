public class Bus
{
	private int id; //The buses id
	private boolean isAvailable; //Whether the bus is currently available
	
	/**
	 * @param id
	 * @param isAvailable
	 */
	public Bus(int id, boolean isAvailable)
	{
		this.id = id;
		this.isAvailable = isAvailable;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the isAvailable
	 */
	public boolean isAvailable()
	{
		return isAvailable;
	}

	/**
	 * @param isAvailable the isAvailable to set
	 */
	public void setAvailable(boolean isAvailable)
	{
		this.isAvailable = isAvailable;
	}
	
	
	
}//Bus
