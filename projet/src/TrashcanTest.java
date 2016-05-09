import static org.junit.Assert.*;

import org.junit.Test;

public class TrashcanTest {

	@Test
	public void testIsCollideRobot() {
		Trashcan w=new Trashcan(10,10,0.5);
		Robot robot = new Robot(0,0,10,9,Math.toRadians(0),0.5,2,0.2);
		assertTrue(w.isCollideRobot(robot));
		Robot robot2 = new Robot(0,0,10,9,Math.toRadians(0),0.45,2,0.2);
		assertFalse(w.isCollideRobot(robot2));
	}

}
