import java.awt.geom.Point2D;

public class Bumper extends Sensor {

	protected double angleInit;
	protected double span;
	protected boolean triggered;
	private static double thickness=0.01;

	/**
	 * Constructeur par default.
	 * Le bumper est positionne a un angle initial nul robot.
	 */

	public Bumper() {
		this.angleInit = 0.0;
		this.span = 0.0;
		this.triggered = false;
		this.type="Bumper";
	}

	/**
	 * Constructeur.
	 * Le bumper est positionne avec une position relative a celle de la peripherie du robot.
	 * @param angleInit angle de depart du bumper, dans le sens trigo.
	 * @param span angle pris dans le sens trigo a partir de l angle initial.
	 */

	public Bumper(double angleInit, double span) {
		this.angleInit = Math.toRadians(angleInit%360);
		/*
		if(angleInit<0){
			angleInit=Math.PI+angleInit;
		}*/
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
		Point2D.Double robPos=new Point2D.Double(robot.getX(), robot.getY());
		for(Obstacle ob:terrain.getObstacles()){
			if(triggered){
				break;
			}
			switch (ob.getType()){
			case "Wall":
				Wall w=(Wall)ob;
				triggered=Collision.ArcLine(robPos, robot.getRadius()+thickness,Posture.normalize_angle(angleInit+robot.getTheta()), span, w.getLine());
				break;
			case "Trashcan":
				Trashcan tc=(Trashcan)ob;
				triggered=Collision.ArcCircle(robPos, robot.getRadius()+thickness, Posture.normalize_angle(angleInit+robot.getTheta()), span, tc.getPt(),tc.getRadius());
				break;
			default:
			}
		}
		
	}

	/**
	 * Renvoie le statut du bumper, vrai si il est actif.
	 */
	public boolean isTriggered() {
		return triggered;
	}

	/**
	 * Renvoie l'angle initial du bumper.
	 */
	public double getAngleInit() {
		return angleInit;
	}

	/**
	 * Renvoie l'amplitude angulaire du bumper.
	 */
	public double getSpan() {
		return span;
	}

	/**
	 * Donne les informations du bumper sous forme de String.
	 */
	@Override
	public String toString() {
		return "Bumper from:"+angleInit+", span:"+span+" etat: "+(triggered?"actif":"inactif");
	}


}
