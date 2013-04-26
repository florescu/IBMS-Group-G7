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

public class TestService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        database.openBusDatabase();
        int[] serviceIDs = Service.getServices();
        for (int i=0; i<serviceIDs.length; i++)
        	System.out.println(serviceIDs[i] + " is cancelled: " + Service.isCancelled(serviceIDs[i])
        											+ " is delayed: " + Service.isDelayed(serviceIDs[i]));
        Service.setCancelled(serviceIDs[0], true);
        /*System.out.println("---------------------------------------");
        for (int i=0; i<serviceIDs.length; i++)
        	System.out.println(serviceIDs[i] + " is cancelled: " + Service.isCancelled(serviceIDs[i]));
        */
        
        //Service.setDelayedTime(serviceIDs[serviceIDs.length-1], 5);
       /* System.out.println("---------------------------------------");
        for (int i=0; i<serviceIDs.length; i++)
        	System.out.println(serviceIDs[i] + " is cancelled: " + Service.isCancelled(serviceIDs[i])
        											+ " is delayed: " + Service.isDelayed(serviceIDs[i]));
        */
        
    
    }

}
