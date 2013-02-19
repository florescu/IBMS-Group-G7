/*
 * An application to Request Driver Holiday
 */
public class G7_IBMS_RDH
{
  public static final int MAX_HOLIDAY = 25;
  
  public static void main(String[] args)
  {
    int driverID = Integer.parseInt(args[0]);
    int srtDay = Integer.parseInt(args[1]);
    int srtMonth = Integer.parseInt(args[2]);
    int srtYear = Integer.parseInt(args[3]);
    int endDay = Integer.parseInt(args[4]);
    int endMonth = Integer.parseInt(args[5]);
    int endYear = Integer.parseInt(args[6]);
    
    BusRoot.openBusDatabase();
    Holiday holidayRequest = new Holiday(srtDay,srtMonth,srtYear,
					 endDay,endMonth,endYear);
    int requestedDays = holidayRequest.getNoOfDays();
    while (requestedDays <= 0)
    {
      System.out.println("The start date must be before the end date");
      System.out.println("Please enter them again");
      return;
    }
    System.out.println(requestedDays+ " reqDays");
    if(hasAvalibleHoliday(driverID, requestedDays))
      System.out.println("You have enough holiday");
    else
      System.out.println("This request is not valid - you don't have enough"+
	  " holiday");
    
    if(holidayRequest.daysAway() >= 7)
      System.out.println("The start date is 7 or more days away");
    else
      System.out.println("This request is not valid - it's less than 7 days"+
	  " away");
    
  }//main
  
  /*
   * Checks that 
   */
  
  private static boolean hasAvalibleHoliday(int driverID, int requestedDays)
  {
    int avalibleDays = MAX_HOLIDAY - DriverInfo.getHolidaysTaken(driverID);
    if((avalibleDays - requestedDays) < 0)
      return false;
    else 
      return true;
  }//isValidRequest
}//class
