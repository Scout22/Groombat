import static org.junit.Assert.*;

import org.junit.Test;

public class DirtSpotTest {

	@Test
	public void testClean() {
		DirtSpot ds=new DirtSpot(0,0,1);
		assertTrue(ds.clean(30*Math.PI*1));
		DirtSpot ds2=new DirtSpot(0,0,1);
		assertFalse(ds2.clean(15*Math.PI*1));
		assertTrue(ds2.clean(15*Math.PI*1));
		
	}

}
