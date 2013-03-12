public class Driver
{
	private int id; //The drivers id
	private String name; //The drivers name
	private int minWorkedWeek; //The minutes they have worked this week
	private int minWorkedDay; //The minutes they have worked today

	/**
	 * @param id
	 * @param minWorkedDay
	 */
	public Driver(int id, int minWorkedDay)
	{
		this.id = id;
		this.name = DriverInfo.getName(id);
		this.minWorkedWeek = DriverInfo.getHoursThisWeek(id);
		this.minWorkedDay = minWorkedDay;
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
	 * @return the minWorkedWeek
	 */
	public int getMinWorkedWeek()
	{
		return minWorkedWeek;
	}

	/**
	 * @param minWorkedWeek the minWorkedWeek to set
	 */
	public void setMinWorkedWeek(int minWorkedWeek)
	{
		this.minWorkedWeek = minWorkedWeek;
	}

	/**
	 * @return the minWorkedDay
	 */
	public int getMinWorkedDay()
	{
		return minWorkedDay;
	}

	/**
	 * @param minWorkedDay the minWorkedDay to set
	 */
	public void setMinWorkedDay(int minWorkedDay)
	{
		this.minWorkedDay = minWorkedDay;
	}
	
	
	
	
}//Driver
