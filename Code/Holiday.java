import java.util.*;
import static java.util.Calendar.*;
import org.joda.time.MutableDateTime;
import org.joda.time.Days;

public class Holiday
{
  //Instance variables for a holiday
  private int noOfDays;
  private MutableDateTime startDate;
  private MutableDateTime endDate;
  
  //Holiday constructor
  public Holiday(int reqStartDay, int reqStartWeek, int reqStartYear,
       int reqEndDay, int reqEndWeek, int reqEndYear)
  {
    startDate = new MutableDateTime(reqStartYear, reqStartWeek, reqStartDay,0,0,0,0);
    endDate = new MutableDateTime(reqEndYear, reqEndWeek, reqEndDay,0,0,0,0);
    noOfDays = calcNoDays(startDate, endDate);
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
  public void setStartDate(int reqStartDay, int reqStartWeek, int reqStartYear)
  {
    this.startDate.setDate(reqStartYear, reqStartWeek, reqStartDay);
    this.noOfDays = calcNoDays(this.startDate, this.endDate);
  }//setStartDate
  
  //Sets the end date and re calculates no of days
  public void setEndDate(int reqEndDay, int reqEndWeek, int reqEndYear)
  {
    this.endDate.setDate(reqEndYear, reqEndWeek, reqEndDay);
    this.noOfDays = calcNoDays(this.startDate, this.endDate);
  }//setStartDate
  
}//Holiday
