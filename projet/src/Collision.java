import java.awt.geom.*;

public abstract class Collision {
	private static double precision=0.001;
	public static boolean CircleLine(Point2D.Double c,double r,Line2D.Double line){
		if(line.ptSegDist(c)+precision<=r){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public static boolean CircleCircle(Point2D.Double c1,double r1,Point2D.Double c2,double r2){
		
		if(c1.distance(c2)+precision<=r1+r2){
			return true;}
		else{
			return false;
		}
		
	}
	
	public static boolean ArcCircle(Point2D.Double ac,double ar,double angleMin,double angleSpan,Point2D.Double c,double r){
		if(CircleCircle(c,r,ac,ar)){
			double angleContact=Math.atan2(ac.getY()-c.getY(),ac.getX()-c.getX());
			
			if(angleContact<0){
				angleContact=2*Math.PI+angleContact;
			}
			angleContact=(angleContact+Math.PI)%(2*Math.PI);
			if(angleContact>angleMin && angleContact<(angleMin+angleSpan)){
				return true;
			}
		}
		return false;
	}
	public static boolean ArcLine(Point2D.Double ac,double ar,double angleMin,double angleSpan,Line2D.Double line){
		if(line.ptSegDist(ac)+precision<=ar){
			
				return true;
			}
		
		return false;
	}
	
}
