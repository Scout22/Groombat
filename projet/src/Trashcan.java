import java.awt.geom.*;

public class Trashcan extends Obstacle {
	private double x;
	private double y;
	private double rayon;

	
	
	Trashcan(double x, double y, double rayon){
		this.x=x;
		this.y=y;
		if(rayon>0){
			this.rayon=rayon;}
		else{
			rayon=0;
		}

	}
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getRayon(){
		return rayon;
	}
	
	
	public Point2D.Double getPt(){
		return new Point2D.Double(x,y);
	}
	
	public boolean isCollideLine(Point2D p1,Point2D p2){
		Line2D.Double line=new Line2D.Double(p1,p2);
		Point2D.Double centre= new Point2D.Double(x, y);
		if(line.ptLineDist(centre)<=rayon){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isCollideArc(Point2D centre,double rayon,double angleMin,double angleSpan){
		if(isCollideCircle(new Point2D.Double(centre.getX(),centre.getY()),rayon)){
			double angleContact=Math.atan2(centre.getX()-x,centre.getY()-y);
			angleContact=Math.toDegrees(angleContact);
			if(angleContact<0){
				angleContact+=360;
			}
			System.out.println(angleContact);
			if(angleContact>angleMin && angleContact<(angleMin+angleSpan)%360){
				return true;
			}
		}
		return false;
	}
	
	public boolean isCollideCircle(Point2D.Double centre,double radius){
		Point2D.Double trashcanCenter=new Point2D.Double(x,y);
		if(trashcanCenter.distance(centre)<=radius+rayon){
		return true;}
		else{
			return false;
		}
	}

	public boolean isCollide(Robot rob){
		
		return isCollideCircle(new Point2D.Double(rob.getX(),rob.getY()),rob.getRadius());
		
	}



}


