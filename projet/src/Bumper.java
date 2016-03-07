import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Bumper extends Sensor {

	protected double angleInit;
	protected double span;
	protected boolean triggered;
	private static double thickness=0.002;
	
	/**
	 * Constructeur par default.
	 * Le bumper est positionne a un angle initial nul robot.
	 */
	
	public Bumper() {
		this.angleInit = 0.0;
		this.span = 0.0;
		this.triggered = false;
	}

	/**
	 * Constructeur.
	 * Le bumper est positionne avec une position relative a celle de la peripherie du robot.
	 * @param angleInit angle de depart du bumper, dans le sens trigo.
	 * @param span angle pris dans le sens trigo a partir de l angle initial.
	 */
	
	public Bumper(double angleInit, double span) {
		this.angleInit = Math.toRadians(angleInit%360);
		if(angleInit<0){
			angleInit=Math.PI+angleInit;
		}
		this.span = Math.toRadians(span);
		this.triggered = false;
	}
	
	/**
	 * Indique si le capteur est actif car en collision avec un obstacle.
	 * @param map carte contenant la liste des obstacles.
	 * @param robot robot portant le capteur.
	 */
	
	public void updateState(Map terrain, Robot robot){
		triggered=false;
		for(Obstacle ob:terrain.getObstacles()){
			if(triggered){
				break;
			}
			switch (ob.getType()){
			case "Wall":
				Wall w=(Wall)ob;
				triggered=Collision.ArcLine(new Point2D.Double(robot.getX(), robot.getY()), robot.getRadius()+thickness,(robot.getTheta()+ angleInit)%(2*Math.PI), span, w.getLine());
				break;
			case "Trashcan":
				Trashcan tc=(Trashcan)ob;
				triggered=Collision.ArcCircle(new Point2D.Double(robot.getX(), robot.getY()), robot.getRadius()+thickness, (robot.getTheta()+ angleInit)%(2*Math.PI), span, tc.getPt(),tc.getRadius());
				break;
			default:
			}
		}
		if(triggered){
			System.out.println("Sensor triggered");
		}
	}

	public boolean isTriggered() {
		return triggered;
	}

	public double getAngleInit() {
		return angleInit;
	}

	public double getSpan() {
		return span;
	}


}
