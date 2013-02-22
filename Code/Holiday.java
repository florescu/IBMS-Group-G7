/*
 * Authour: Joshua Abbott feb 2013
 *  A class to represent a Holiday
 */ 
import java.util.*;
import static java.util.Calendar.*;
import org.joda.time.MutableDateTime;
import org.joda.time.Days;
import org.joda.time.IllegalFieldValueException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Holiday
{
  //Instance variables for a holiday
  private int noOfDays;
  private MutableDateTime startDate;
  private MutableDateTime endDate;
  
  //Holiday constructor
  public Holiday(int reqStartDay, int reqStartMonth, int reqStartYear,
       int reqEndDay, int reqEndMonth, int reqEndYear)
  {
    try{
    startDate = new MutableDateTime(reqStartYear, reqStartMonth, reqStartDay,0,0,0,0);
    endDate = new MutableDateTime(reqEndYear, reqEndMonth, reqEndDay,0,0,0,0);
    noOfDays = calcNoDays(startDate, endDate);
    }
    catch(IllegalFieldValueException e)
    {
      JLabel jLabelError = new JLabel("Error: Please enter a valid date!");
      jLabelError.setText("Error: Please enter a valid date!");
      jLabelError.setVisible(true);
    }
  }//constructor
  
  //Calculates the no of days
  private int calcNoDays(MutableDateTime startDate, MutableDateTime endDate)
  {
    return Days.daysBetween(startDate, endDate).getDays();
  }//calcNoOfDays
  
  //returns noOfDays
  public int getNoOfDays()
  {
    return noOfDays;
  }//getNoOfDays
  
  //returns startDate
  public MutableDateTime getStartDate()
  {
    return startDate;
  }//getStartDate
  
  //returns endDate
  public MutableDateTime getEndDate()
  {
    return endDate;
  }//getEndDate
  
  //Sets the start date and re calculates no of days
  public void setStartDate(int reqStartDay, int reqStartMonth, int reqStartYear)
  {
    this.startDate.setDate(reqStartYear, reqStartMonth, reqStartDay);
    this.noOfDays = calcNoDays(this.startDate, this.endDate);
  }//setStartDate
  
  //Sets the end date and re calculates no of days
  public void setEndDate(int reqEndDay, int reqEndMonth, int reqEndYear)
  {
    this.endDate.setDate(reqEndYear, reqEndMonth, reqEndDay);
    this.noOfDays = calcNoDays(this.startDate, this.endDate);
  }//setStartDate
  
  //returns the number of days untill the start date
  public int daysAway()
  {
    return Days.daysBetween(MutableDateTime.now(), this.startDate).getDays(); 
  }//daysAway
  
}//Holiday
