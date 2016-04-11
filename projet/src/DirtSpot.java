import java.awt.geom.Point2D;

public class DirtSpot {
	private double x;
	private double y;
	private double rayon;
	private double init_rayon;
	private double remaining_life;
	private double full_life;
	
	DirtSpot(double x,double y,double rayon){
		this.x=x;
		this.y=y;
		this.rayon=rayon;
		init_rayon = rayon;
		remaining_life=Math.PI*rayon*rayon*30;
		full_life = remaining_life;
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
		rayon = init_rayon*(remaining_life/full_life);
		if(remaining_life<2){
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
