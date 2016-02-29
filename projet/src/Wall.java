
//Les murs ne peuvent etre que verticale ou horizontale.
public class Wall extends Obstacle {
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	boolean isCollide(Robot rob){
		double xRobot=rob.getX();
		double yRobot=rob.getY();
		double rRayon=rob.getRadius();

		
		return true;
	}

}
