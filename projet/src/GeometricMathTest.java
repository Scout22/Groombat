import static org.junit.Assert.*;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.junit.Test;

public class GeometricMathTest {

	@Test
	public void testLeftNormal() {
		Line2D.Double l1=new Line2D.Double(10,0,10,10);
		Line2D.Double l1rn=GeometricMath.LeftNormal(l1);
		Line2D.Double expectedNormal=new Line2D.Double(0,0,-10,0);
		assertEquals(l1rn.getX1(),expectedNormal.getX1(),0.0001);
		assertEquals(l1rn.getY1(),expectedNormal.getY1(),0.0001);
		assertEquals(l1rn.getX2(),expectedNormal.getX2(),0.0001);
		assertEquals(l1rn.getY2(),expectedNormal.getY2(),0.0001);
	}

	@Test
	public void testRightNormal() {
		Line2D.Double l1=new Line2D.Double(10,0,10,10);
		Line2D.Double l1rn=GeometricMath.RightNormal(l1);
		Line2D.Double expectedNormal=new Line2D.Double(0,0,10,0);
		assertEquals(l1rn.getX1(),expectedNormal.getX1(),0.0001);
		assertEquals(l1rn.getY1(),expectedNormal.getY1(),0.0001);
		assertEquals(l1rn.getX2(),expectedNormal.getX2(),0.0001);
		assertEquals(l1rn.getY2(),expectedNormal.getY2(),0.0001);
	}

	@Test
	public void testNormalise() {
		Line2D.Double l1=new Line2D.Double(12,23,64,10);
		Line2D.Double l2=new Line2D.Double(-10,5,3,10);
		Line2D.Double l1n=GeometricMath.normalise(l1, 1);
		Line2D.Double l2n=GeometricMath.normalise(l2, 1);
		Line2D.Double l1n10=GeometricMath.normalise(l1, 10);
		Line2D.Double l2n10=GeometricMath.normalise(l2, 10);
		assertEquals(l1n.getX2()*l1n.getX2()+l1n.getY2()*l1n.getY2(),1,0.01);
		assertEquals(l2n.getX2()*l2n.getX2()+l2n.getY2()*l2n.getY2(),1,0.01);
		assertEquals(Math.sqrt(l1n10.getX2()*l1n10.getX2()+l1n10.getY2()*l1n10.getY2()),10,0.01);
		assertEquals(Math.sqrt(l2n10.getX2()*l2n10.getX2()+l2n10.getY2()*l2n10.getY2()),10,0.01);
		
	}

	@Test
	public void testIsLeft() {
		Line2D.Double l1=new Line2D.Double(0,0,0,10);
		Point2D.Double p1=new Point2D.Double(20,5);
		Point2D.Double p2=new Point2D.Double(-20,5);
		Line2D.Double l2=new Line2D.Double(0,0,10,10);
		assertFalse(GeometricMath.isLeft(l1,p1));
		assertTrue(GeometricMath.isLeft(l1,p2));
		assertFalse(GeometricMath.isLeft(l2,p1));
		assertTrue(GeometricMath.isLeft(l2,p2));
		Line2D.Double l3=new Line2D.Double(0,10,10,10);
		Point2D.Double p3=new Point2D.Double(5,5);
		Point2D.Double p4=new Point2D.Double(5,15);
		assertFalse(GeometricMath.isLeft(l3,p3));
		assertTrue(GeometricMath.isLeft(l3,p4));
	}

}
