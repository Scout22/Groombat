import static org.junit.Assert.*;

import org.junit.Test;

public class WallTest {

	@Test
	public void testIsCollideRobot() {
		Wall w=new Wall(10,0,10,10);
		Robot robot = new Robot(0,0,9.5,2,Math.toRadians(0),0.5,2,0.2);
		assertTrue(w.isCollideRobot(robot));
		Robot robot2 = new Robot(0,0,9.5,2,Math.toRadians(0),0.45,2,0.2);
		assertFalse(w.isCollideRobot(robot2));
	}

}
