import java.awt.geom.*;

public class Trashcan extends Obstacle {
	private double x;
	private double y;
	private double rayon;
	

	
	
	Trashcan(double x, double y, double rayon){
		type="Trashcan";
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
	
	public double getRadius(){
		return rayon;
	}
	
	
	public Point2D.Double getPt(){
		return new Point2D.Double(x,y);
	}
	
	
	public boolean isCollideRobot(Robot rob){		
		return Collision.CircleCircle(getPt(), rayon, new Point2D.Double(rob.getX(),rob.getY()),rob.getRadius());
	}



}


