
//Les murs ne peuvent etre que verticale ou horizontale.
public class Wall extends Obstacle {
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	Wall(double x1,double y1,double x2,double y2){
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
	}
	
	public double getX1(){
		return x1;
	}
	public double getX2(){
		return y1;
	}
	public double getY1(){
		return x2;
	}
	public double getY2(){
		return y2;
	}
	
	boolean isCollide(Robot rob){
		double xRobot=rob.getX();
		double yRobot=rob.getY();
		double rRayon=rob.getRadius();

		
		return true;
	}

}
