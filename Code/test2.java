/*
 * A very simple application illustrating how to use the interface.
 * Prints the names of all the drivers in the database.
 * @author John Sargeant
 */
 
import java.util.*;
import static java.util.Calendar.*;
import org.joda.time.MutableDateTime;
import org.joda.time.Days;
import org.joda.time.IllegalFieldValueException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        database.openBusDatabase();
        int[] driverIDs = DriverInfo.getDrivers();
        //String[] driverNames = new String [driverIDs.length];
        
        //for (int i=0; i<driverIDs.length; i++)
           //if driverIDs[i].findDriver
           // System.out.println("ID: "+driverIDs[i]);
        
        /*GregorianCalendar newDate = new GregorianCalendar(2013,0,1);
        for (int j=0; j<365; j++)
        {
          System.out.println(" "+newDate.get(DAY_OF_MONTH)+ " "+newDate.get(MONTH) +" "+  DriverInfo.isAvailable(2080, newDate.getGregorianChange()));
          newDate.add(DAY_OF_YEAR,1);
        }*/
        MutableDateTime currentDay = new MutableDateTime(2013,0,1,0,0,0);
        Calendar newDate=currentDay.toCalendar(null);
        for(int i = 0; i < 365; i++)
      {
        System.out.println(" "+newDate.get(DAY_OF_MONTH)+ " "+newDate.get(MONTH) +" "+  DriverInfo.isAvailable(2080, currentDay.toDate()));
        System.out.println("Driver availability: " + DriverInfo.isAvailable(driverID, currentDay.toDate()));
        currentDay.addDays(1);
      }//for
       
    }

}
