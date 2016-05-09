import java.awt.geom.Point2D;

/**
 * @author Yanis
 * Classe definissant un capteur de poussiere
 */
public class DirtSensor extends Sensor {
	
	protected double radiusInRobot; 
	protected double angle;
	protected double sensorRadius;
	protected boolean triggered;
	
	
	/**
	 * Constructeur par default.
	 * Le capteur de poussiere est positionne au centre du robot avec un rayon nul.
	 */
	public DirtSensor() {
		this.radiusInRobot = 0.0;
		this.angle = 0.0;
		this.sensorRadius = 0.0;
		this.triggered = false;
		type="DirtSensor";
	}

	/**
	 * Constructeur du capteur de tache.
	 * On creer un capteur de poussiere positionne dans le robot avec une distance et un angle, ainsi qu'un rayon de detection.
	 * On donne pour le control d'erreur le rayon total du robot, pour que le capteur ne depasse par du robot.
	 * @param radiusInRobot la distance entre le centre du robot et le capteur (en m).
	 * @param angle angle de la position du capteur dans le robot (en degree).
	 * @param sensorRadius rayon du capteur (en m).
	 * @param robot robot qui porte le capteur.
	 */	
	public DirtSensor(double radiusInRobot, double angle, double sensorRadius, Robot robot) {
		if(radiusInRobot + sensorRadius > robot.getRadius()){
			//System.out.println("Error creating DirtSensor : sensor outside robot boudaries, setting to default (null)");
			this.radiusInRobot = 0.0;
			this.angle = 0.0;
			this.sensorRadius = 0.0;
			this.triggered = false;
		}
		else{
			this.radiusInRobot = radiusInRobot;
			this.angle = angle;
			this.sensorRadius = sensorRadius;
			this.triggered = false;
		}
	}

	/**
	 * Renvoie la distance entre le centre du capteur et le centre du robot.
	 */
	public double getRadiusInRobot() {
		return radiusInRobot;
	}

	/**
	 * Fixe la distance entre le centre du capteur et le centre du robot.
	 * @param radiusInRobot distance souhaitee.
	 */
	public void setRadiusInRobot(double radiusInRobot) {
		this.radiusInRobot = radiusInRobot;
	}

	/**
	 * Renvoie l'angle de positionnement du capteur dans le robot.
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Fixe l'angle de positionnement du capteur dans le robot.
	 * @param angle angle souhaite.
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

	/**
	 * Renvoie le rayon du capteur.
	 */
	public double getSensorRadius() {
		return sensorRadius;
	}

	/**
	 * Fixe le rayon du capteur.
	 * @param sensorRadius rayon souhaite.
	 */
	public void setSensorRadius(double sensorRadius) {
		this.sensorRadius = sensorRadius;
	}

	/**
	 * Renvoie l'etat du capteur, true si il est actif.
	 */
	public boolean isTriggered() {
		return triggered;
	}

	/**
	 * Impose l'etat du capteur.
	 * @param triggered etat du capteur, vrai = capteur actif.
	 */
	public void setTriggered(boolean triggered) {
		this.triggered = triggered;
	}

	/** 
	 * Indique si le capteur est actif car au dessus d'une tache.
	 * @param map carte contenant la liste des taches.
	 * @param rob robot qui contient le capteur
	 */
	@Override
	public void updateState(Map map, Robot rob) {
		triggered = false;
		Point2D.Double absPos=getAbsPos( rob);
		for(DirtSpot ds:map.getDirtSpots()){
			triggered=Collision.CircleCircle(absPos, sensorRadius, ds.getPt(), ds.getRadius());
			if(triggered){
				break;
			}
		}
		
	}
	
	/**
	 * Obtient la position absolue du capteur dans la fenetre
	 * @param rob robot considerer
	 * @return Position du capteur au sein de la fenetre
	 */
	public Point2D.Double getAbsPos(Robot rob){
		return new Point2D.Double(rob.getX()+Math.cos(angle+rob.getTheta())*radiusInRobot,rob.getY()-Math.sin(angle+rob.getTheta())*radiusInRobot);
		
	}
	
	/**
	 * Position du capteur au seins d'un robot
	 * @return position relative du robot au sein d'un robot
	 */
	public Point2D.Double getRelativePos(){
		return new Point2D.Double(Math.cos(angle)*radiusInRobot,Math.sin(angle)*radiusInRobot);
		
	}

	/**
	 * Renvoie le capteur sous forme textuelle.
	 */
	@Override
	public String toString() {
		
		return "DirtSensor etat: "+(triggered?"actif":"inactif");
	}
}
