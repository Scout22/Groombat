import static org.junit.Assert.*;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.junit.Test;

public class CollisionTest {

	@Test
	public void testCircleLine() {
		Point2D.Double center_cercle=new Point2D.Double(5,5);
		Double rayon_cercle=1.0;
		Line2D.Double ligne=new Line2D.Double(0,0,10,10);
		assertTrue(Collision.CircleLine(center_cercle, rayon_cercle, ligne));
		Line2D.Double ligne2=new Line2D.Double(0,0,2,2);
		assertFalse(Collision.CircleLine(center_cercle, rayon_cercle, ligne2));
	}

	@Test
	public void testCircleCircle() {
		Point2D.Double center_cercle1=new Point2D.Double(5,5);
		Point2D.Double center_cercle2=new Point2D.Double(3,5);
		Double rayon_cercle1=1.0;
		Double rayon_cercle2=1.1;
		assertTrue(Collision.CircleCircle(center_cercle1, rayon_cercle1, center_cercle2,rayon_cercle2));
		Double rayon_cercle3=0.9;
		assertFalse(Collision.CircleCircle(center_cercle1, rayon_cercle1, center_cercle2,rayon_cercle3));
	}

	@Test
	public void testArcCircle() {
		Point2D.Double center_cercle1=new Point2D.Double(5,5);
		Point2D.Double center_cercle2=new Point2D.Double(3,5);
		Double rayon_cercle1=1.0;
		Double rayon_cercle2=1.0;
		assertTrue(Collision.ArcCircle(center_cercle1, rayon_cercle1,Math.toRadians(90),Math.toRadians(270), center_cercle2,rayon_cercle2));
		assertFalse(Collision.ArcCircle(center_cercle1, rayon_cercle1,Math.toRadians(355),Math.toRadians(15), center_cercle2,rayon_cercle2));
	}

	@Test
	public void testArcLine() {
		Point2D.Double center_cercle=new Point2D.Double(5,5);
		double rayon_cercle=1.0;
		Line2D.Double ligne=new Line2D.Double(6,0,6,10);
		assertTrue(Collision.ArcLine(center_cercle, rayon_cercle,Math.toRadians(350),Math.toRadians(30), ligne));
		assertFalse(Collision.ArcLine(center_cercle, rayon_cercle,Math.toRadians(150),Math.toRadians(30), ligne));

	}

}
