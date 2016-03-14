

import static org.junit.Assert.*;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.junit.Test;

public class CollisionTest {

	@Test
	public void testCircleLine() {
		fail("Not yet implemented");
	}

	@Test
	public void testCircleCircle() {
		fail("Not yet implemented");
	}

	@Test
	public void testArcCircle() {
		fail("Not yet implemented");
	}

	@Test
	public void testArcLine() {
		fail("Not yet implemented");
	}

	@Test
	public void testLeftNormal() {
		fail("Not yet implemented");
	}

	@Test
	public void testDotProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testNormalise() {
		fail("Not yet implemented");
	}

	@Test
	public void testProjection() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsLeft() {
		Line2D.Double l1=new Line2D.Double(0,0,0,10);
		Point2D.Double p1=new Point2D.Double(20,5);
		Point2D.Double p2=new Point2D.Double(-20,5);
		Line2D.Double l2=new Line2D.Double(0,0,10,10);
		assertFalse(Collision.isLeft(l1,p1));
		assertTrue(Collision.isLeft(l1,p2));
		assertFalse(Collision.isLeft(l2,p1));
		assertTrue(Collision.isLeft(l2,p2));
		Line2D.Double l3=new Line2D.Double(0,10,10,10);
		Point2D.Double p3=new Point2D.Double(5,5);
		Point2D.Double p4=new Point2D.Double(5,15);
		assertFalse(Collision.isLeft(l3,p3));
		assertTrue(Collision.isLeft(l3,p4));
	}

}
