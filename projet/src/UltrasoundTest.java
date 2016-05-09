import static org.junit.Assert.*;

import org.junit.Test;

public class UltrasoundTest {

	@Test
	public void testIsTriggered() {
		Ultrasound us=new Ultrasound(0,180,1);
		Map map=new Map();
		map.addObstacle(new Trashcan(1,1.5,1));
		Robot robot = new Robot(0,0,1,0,Math.toRadians(0),0.5,2,0.2);
		us.updateState(map, robot);
		assertTrue(us.isTriggered());
		Robot robot2 = new Robot(0,0,1,0,Math.toRadians(180),0.5,2,0.2);
		us.updateState(map, robot2);
		assertFalse(us.isTriggered());
	}

}
