
public class DirtSpot {
	private double x;
	private double y;
	private double rayon;
	
	DirtSpot(double x,double y,double rayon){
		this.x=x;
		this.y=y;
		this.rayon=rayon;
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

	public boolean colliderSensor(double x, double y, double sensorRadius) {
		// TODO Auto-generated method stub
		return false;
	}

}
