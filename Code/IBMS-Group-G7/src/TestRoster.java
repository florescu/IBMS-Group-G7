import java.util.GregorianCalendar;


public class TestRoster
{
	public static void main(String[] args)
	{
		database.openBusDatabase();
		try
		{
			GregorianCalendar cal = new GregorianCalendar(2013,3,15);
			Roster theRoster = new Roster(cal);
			theRoster.generateRoster();
		}
		catch(InvalidQueryException e)
		{
			System.out.println(e.getMessage());
		}
	}

}
