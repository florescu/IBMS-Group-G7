import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class JUnitServiceTest
{
	@Before
	public void setup()
	{
    database.openBusDatabase();		
	}

	@Test
	public void testIsCancelled()
	{
		assertFalse(Service.isCancelled(6177));
		assertTrue(Service.isCancelled(6177));
		assertTrue(Service.isCancelled(6178));
	}

	@Test
	public void testSetCancelled()
	{
		assertFalse(Service.isCancelled(6179));
		Service.setCancelled(6179, true);
		assertTrue(Service.isCancelled(6179));
	}

	@Test
	public void testIsDelayed()
	{
		assertTrue(Service.isDelayed(6177)==0);
		assertTrue(Service.isDelayed(6180)==5);
	}

	@Test
	public void testSetDelayedTime()
	{
		assertFalse(Service.isDelayed(6181)==0); 
		Service.setDelayedTime(6181, 7);
		assertTrue(Service.isDelayed(6181)==7);
	}

	@Test
	public void testGetDelayedTime()
	{
		assert(Service.getDelayedTime(6181))==7;
		assert(Service.getDelayedTime(6182))==0;
	}

	@Test
	public void testGetServices()
	{
		assert(Service.getServices().length)!=0;
	}

	@Test
	public void testGetMessage()
	{
		assertFalse(Service.getMessage(6180).equals("nomessage"));
    assertTrue(Service.getMessage(6191).equals("nomessage"));
	}

	@Test
	public void testSetMessage()
	{
		assertEquals(Service.getMessage(6184), "nomessage");
		Service.setMessage(6184, "no good reason");
		assertEquals(Service.getMessage(6184), "nomessage");
		assertEquals(Service.getMessage(6184), "no good reason");
	}

	@Test
	public void testIsInDatabase()
	{
		assertTrue(Service.isInDatabase(6181));
		assertFalse(Service.isInDatabase(6171));
		assertTrue(Service.isInDatabase(6180));
	}

}
