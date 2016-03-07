import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Test;

public class TrashcanTest {

	@Test
	public void testIsCollideLine() {
		Trashcan t=new Trashcan(10,10,1);
		Point2D.Double p1=new Point2D.Double(0,0);
		Point2D.Double p2=new Point2D.Double(11,11);
		Point2D.Double p3=new Point2D.Double(0,11);
//		assertTrue(t.isCollideLine(p1, p2));
	//	assertFalse(t.isCollideLine(p1,p3));
	}

	@Test
	public void testIsCollideArc() {
		Trashcan t=new Trashcan(10,10,1);
		Point2D.Double p1=new Point2D.Double(9,11);
//		assertTrue(t.isCollideArc(p1, 1, 270, 90));
	//	assertFalse(t.isCollideArc(p1, 1, 90,90));
	}

	@Test
	public void testIsCollideCircle() {
		Trashcan t=new Trashcan(10,10,1);
		Point2D.Double p1=new Point2D.Double(9,9);
		Point2D.Double p2=new Point2D.Double(0,0);
//		assertTrue(t.isCollideCircle(p1,2));
	//	assertFalse(t.isCollideCircle(p2,1));
	}

	@Test
	public void testIsCollide() {
		fail("Not yet implemented");
	}

}
