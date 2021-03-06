/*
 * Authour: Joshua Abbott feb 2013
 * An application to Request Driver Holiday
 */
public class G7_IBMS_RDH
{
  //The max number of holidays days that can be taken in a year
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
    
    //Open the DB
    BusRoot.openBusDatabase();
    
    //Create new holiday object
    Holiday holidayRequest = new Holiday(srtDay,srtMonth,srtYear,
					 endDay,endMonth,endYear);
    
    //Get the number of requested days
    int requestedDays = holidayRequest.getNoOfDays();
    
    //Check if the requested days is valid
    if (requestedDays <= 0)
    {
      System.out.println("The start date must be before the end date");
      System.out.println("Please enter them again");
      return;
    }
    System.out.println(requestedDays+ " reqDays");
    
    
    //Check the driver has enough holiday
    if(hasAvalibleHoliday(driverID, requestedDays))
      System.out.println("You have enough holiday");
    else
    {
      System.out.println("This request is not valid - you don't have enough"+
	  " holiday");
      return;
    }
    
    //Check if the holiday is 7 days or further away
    if(holidayRequest.daysAway() >= 7)
      System.out.println("The start date is 7 or more days away");
    else
    {
      System.out.println("This request is not valid - it's less than 7 days"+
	  " away");
      return;
    }
    
  }//main
  
  /*
   * Checks if the requested days there is enogh holiday
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
