

public class Trashcan extends Obstacle {
	private double x;
	private double y;
	private double rayon;

	Poubelle(double x, double y, double rayon){
		this.x=x;
		this.y=y;
		if(rayon>0){
			this.rayon=rayon;}
		else{
			rayon=0;
		}

	}

	boolean isCollide(Robot rob){
		double xRobot=Robot.getX();
		double yRobot=Robot.getY();
		double rRayon=Robot.getForme();

		double norme=Math.sqrt(Math.sqrt(Math.pow((x-xRobot),2)+Math.pow((y-yRobot),2)));

		if(norme<rRayon+rayon){
			return false;
		}
		return true;
	}



}


