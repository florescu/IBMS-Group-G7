import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class JourneyTest
{

	@Before
	public void setUp()
	{
		database.openBusDatabase();
	}
	
	@Test
	public void testJourneyValid0()
	{
		Journey j = new Journey(0,1,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid1()
	{
		Journey j = new Journey(0,2,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid2()
	{
		Journey j = new Journey(0,3,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid3()
	{
		Journey j = new Journey(0,4,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid4()
	{
		Journey j = new Journey(0,5,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid5()
	{
		Journey j = new Journey(0,6,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid6()
	{
		Journey j = new Journey(0,7,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid7()
	{
		Journey j = new Journey(0,8,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid8()
	{
		Journey j = new Journey(0,9,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid9()
	{
		Journey j = new Journey(0,10,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid10()
	{
		Journey j = new Journey(0,11,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid11()
	{
		Journey j = new Journey(0,12,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid12()
	{
		Journey j = new Journey(0,13,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid13()
	{
		Journey j = new Journey(0,0,500);
		assertFalse(j.getMessage()==null);
	}
	
	@Test
	public void testJourneyValid14()
	{		
    try {
    	Journey j = new Journey(1,6,1425);
    	assertFalse(1==1);
  } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
      assertTrue(1==1);
  }
	}
	
	@Test
	public void testJourneyValid15()
	{		
    try {
    	Journey j = new Journey(1,7,1425);
    	assertFalse(1==1);
  } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
      assertTrue(1==1);
  }
	}
	
	@Test
	public void testJourneyValid16()
	{		
    try {
    	Journey j = new Journey(1,8,1425);
    	assertFalse(1==1);
  } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
      assertTrue(1==1);
  }
	}
	
	@Test
	public void testJourneyValid17()
	{		
    try {
    	Journey j = new Journey(1,9,1425);
    	assertFalse(1==1);
  } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
      assertTrue(1==1);
  }
	}
	
	@Test
	public void testJourneyValid18()
	{		
    try {
    	Journey j = new Journey(1,10,1425);
    	assertFalse(1==1);
  } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
      assertTrue(1==1);
  }
	}
	
	@Test
	public void testJourneyValid19()
	{		
    try {
    	Journey j = new Journey(1,11,1425);
    	assertFalse(1==1);
  } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
      assertTrue(1==1);
  }
	}

}
