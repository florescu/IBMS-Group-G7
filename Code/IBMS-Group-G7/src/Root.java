
public class Root
{
	private int rootNumber; //The root number
	private Service services[]; //An array of all the services for that day
	
	/**
	 * @param rootNumber
	 * @param services
	 */
	public Root(int rootNumber, Service[] services)
	{
		this.rootNumber = rootNumber;
		this.services = services;
	}

	/**
	 * @return the rootNumber
	 */
	public int getRootNumber()
	{
		return rootNumber;
	}

	/**
	 * @param rootNumber the rootNumber to set
	 */
	public void setRootNumber(int rootNumber)
	{
		this.rootNumber = rootNumber;
	}

	/**
	 * @return the services
	 */
	public Service[] getServices()
	{
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(Service[] services)
	{
		this.services = services;
	}	
}
