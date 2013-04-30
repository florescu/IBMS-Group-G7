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

public class TestDisplay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        database.openBusDatabase();
        BusStopInfo.display5buses(771, 65, 2013, 04, 30);
       
    }

}
