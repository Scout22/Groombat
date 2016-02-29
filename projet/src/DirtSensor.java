
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
			System.out.println("Error creating DirtSensor : sensor outside robot boudaries, setting to default (null)");
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
	 * Indique si le capteur est actif car au dessus d'une tache.
	 * @param map carte contenant la liste des taches.
	 */
	
	public void isTriggered(Map map, Robot robot){
		this.triggered = false;
		for(int i=0; i<map.getDirtSpots().size(); i++){
			if(map.getDirtSpots().get(i).colliderSensor(this.radiusInRobot, this.angle, this.sensorRadius)){
				this.triggered = true;
				return;
			}
		}
	}

	public double getRadiusInRobot() {
		return radiusInRobot;
	}

	public void setRadiusInRobot(double radiusInRobot) {
		this.radiusInRobot = radiusInRobot;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getSensorRadius() {
		return sensorRadius;
	}

	public void setSensorRadius(double sensorRadius) {
		this.sensorRadius = sensorRadius;
	}

	public boolean isTriggered() {
		return triggered;
	}

	public void setTriggered(boolean triggered) {
		this.triggered = triggered;
	}
}
