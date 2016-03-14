import java.awt.geom.Point2D;

public class DirtSpot {
	private double x;
	private double y;
	private double rayon;
	private double remaining_life;
	
	DirtSpot(double x,double y,double rayon){
		this.x=x;
		this.y=y;
		this.rayon=rayon;
		remaining_life=Math.PI*rayon*rayon*30;
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

	public boolean clean(double d) {
		remaining_life-=d;
		if(remaining_life<0){
			return true;
		}
		else {
			return false;
		}	
	}

	public Point2D.Double getPt() {
		return new Point2D.Double(x, y);
	}

}
