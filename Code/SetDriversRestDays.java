
import java.util.*;
import static java.util.Calendar.*;
import org.joda.time.MutableDateTime;
import org.joda.time.Days;
import org.joda.time.IllegalFieldValueException;

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
          System.out.println(" "+ DriverInfo.isAvailable(driverIDs[i], baseDate.getTime()));
        }
    }

}
