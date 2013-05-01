public class Journey
{
	private int startStop;
	private int endStop;
	private int via;
	private int startTime;
	
	/**
	 * @param startStop
	 * @param endStop
	 * @param startTime
	 */
	public Journey(int startStop, int endStop, int startTime)
	{
		this.startStop = startStop;
		this.endStop = endStop;
		this.startTime = startTime;
		int[] routes = BusStopInfo.getRoutes();
	}
	
	
}
