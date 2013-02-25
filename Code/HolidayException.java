public class HolidayException extends RuntimeException
{
   public HolidayException()
  {
     super(); 
  }
  public HolidayException(String message)
  {
     super(message); 
  }
  public HolidayException(String message, Throwable cause)
  {
     super(message, cause); 
  }
  public HolidayException(Throwable cause)
  {
     super(cause); 
  }
  
}//HolidayException
