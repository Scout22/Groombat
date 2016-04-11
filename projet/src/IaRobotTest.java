import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;


public class IaRobotTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testStart() {

		Robot groombat = new Robot(0.01,0,1,1,0,0.4,2,0.2);

		DirtSensor sens = new DirtSensor(0.1,-2*Math.PI/3,0.05,groombat);
		groombat.addSensor(sens);

		ArrayList<Robot> robs = new ArrayList<Robot>();
		robs.add(groombat);
		ArrayList<Obstacle> obs = new ArrayList<Obstacle>();

		DirtSpot dirt = new DirtSpot(1,1,0.4);
		ArrayList<DirtSpot> dirts = new ArrayList<DirtSpot>();
		dirts.add(dirt);

		Simulator sim = new Simulator(obs,dirts,robs,0.01,4,4);
		sim.timeStep();
		groombat.getIa().start();
		Assert.assertEquals(0.0, groombat.getSpeedLeft(), 0.005);
		Assert.assertEquals(0.0, groombat.getSpeedRight(), 0.005);
	}
}
