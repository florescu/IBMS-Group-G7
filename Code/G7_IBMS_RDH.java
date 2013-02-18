/*
 * An application to Request Driver Holiday
 */
public class G7_IBMS_RDH
{
  public static final int MAX_HOLIDAY = 25;
  
  public static void main(String[] args)
  {
    database.openBusDatabase();
    int driverID = 2064;
    Holiday holidayRequest = new Holiday(20,2,2013,19,2,2013);
    int requestedDays = holidayRequest.getNoOfDays();
    while (requestedDays <= 0)
    {
      System.out.println("The start date must be before the end date");
      System.out.println("Please enter them again");
      holidayRequest.setStartDate(20,2,2013);
      holidayRequest.setEndDate(27,2,2013);
      requestedDays = holidayRequest.getNoOfDays();
    }
    System.out.println(requestedDays+ " reqDays");
    if(hasAvalibleHoliday(driverID, requestedDays))
      System.out.println("This request is valid");
    else
      System.out.println("This request is not valid");
    
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
