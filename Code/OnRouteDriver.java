
public class OnRouteDriver extends Driver
{
	private Service onService; //The service that they are on
	private Bus onBus; //On what bus

	/**
	 * @param id
	 * @param minWorkedDay
	 * @param onService
	 * @param onBus
	 */
	public OnRouteDriver(int id,int minWorkedDay, Service onService, Bus onBus)
	{
		super(id,minWorkedDay);
		this.onService = onService;
		this.onBus = onBus;
	}

	/**
	 * @return the onBus
	 */
	public Bus getOnBus()
	{
		return onBus;
	}

	/**
	 * @param onBus the onBus to set
	 */
	public void setOnBus(Bus onBus)
	{
		this.onBus = onBus;
	}

	/**
	 * @return the onService
	 */
	public Service getOnService()
	{
		return onService;
	}

	/**
	 * @param onService the onService to set
	 */
	public void setOnService(Service onService)
	{
		this.onService = onService;
	}

	
	
}
