
import java.util.*;
import static java.util.Calendar.*;

public class SetDriversRestDays{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
    		int day = Integer.parseInt(args[0]);
    		int month = Integer.parseInt(args[1]) - 1;
    		int year = Integer.parseInt(args[2]);
        database.openBusDatabase();
        int[] driverIDs = DriverInfo.getDrivers();
        GregorianCalendar baseDate = new GregorianCalendar(year,month,day);
        int dayOfWeek = 0;
       
        for (int i=0; i<driverIDs.length; i++)
        {
        	System.out.println("Driver " + i);
        	GregorianCalendar currentWeek = new GregorianCalendar(baseDate.get(Calendar.YEAR),baseDate.get(Calendar.MONTH),baseDate.get(Calendar.DAY_OF_MONTH));
        	for (int j = 0; j < 52; j++)
        	{
        	
        		//GregorianCalendar currentWeek = new GregorianCalendar(baseDate.get(Calendar.YEAR),baseDate.get(Calendar.MONTH),baseDate.get(Calendar.DAY_OF_MONTH));
        		GregorianCalendar dayOne = new GregorianCalendar(currentWeek.get(Calendar.YEAR),currentWeek.get(Calendar.MONTH),currentWeek.get(Calendar.DAY_OF_MONTH));
        		dayOne.add(DAY_OF_YEAR,dayOfWeek);
        		GregorianCalendar dayTwo = new GregorianCalendar(currentWeek.get(Calendar.YEAR),currentWeek.get(Calendar.MONTH),currentWeek.get(Calendar.DAY_OF_MONTH));
        		dayTwo.add(DAY_OF_YEAR,(dayOfWeek+1)%7);
        		
        		
        		
          	System.out.println(dayOne.get(Calendar.DAY_OF_MONTH)+"/"+dayOne.get(Calendar.MONTH)+"/"+dayOne.get(Calendar.YEAR)+"\t"+dayTwo.get(Calendar.DAY_OF_MONTH)+"/"+dayTwo.get(Calendar.MONTH)+"/"+dayTwo.get(Calendar.YEAR));
          	currentWeek.add(WEEK_OF_YEAR, 1);
          	DriverInfo.setAvailable(driverIDs[i],dayOne.getTime(),false);
          	DriverInfo.setAvailable(driverIDs[i],dayTwo.getTime(),false);
          }
          dayOfWeek = (dayOfWeek+1)%7;
        }
    }

}
