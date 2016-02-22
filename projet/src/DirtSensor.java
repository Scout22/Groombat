
public class DirtSensor extends Sensor {
	
	protected double x; // revenir a radius/angle
	protected double y;
	protected double sensorRadius;
	
	
	/**
	 * Constructeur par default.
	 * Le capteur de poussiere est positionne au centre du robot avec un rayon nul.
	 */
	public DirtSensor() {
		this.x = 0.0;
		this.y = 0.0;
		this.sensorRadius = 0.0;
	}

	/**
	 * Constructeur.
	 * On creer un capteur de poussiere positionne dans le robot avec une distance et un angle, ainsi qu un rayon de detection.
	 * On donne pour le control d erreur le rayon total du robot, pour que le capteur ne depasse par du robot.
	 * @param x distance du centre du robot (en m).
	 * @param y distance du centre du robot (en m).
	 * @param sensorRadius rayon du capteur.
	 * @param robot robot qui porte le capteur
	 */	
	public DirtSensor(double x, double y, double sensorRadius, Robot robot) {
		double distanceFromCenter = Math.sqrt(Math.pow(x-robot.getX(),2) +Math.pow(y-robot.getY(),2));
		if(distanceFromCenter + sensorRadius > robot.getRadius()){
			System.out.println("Error creating DirtSensor : sensor outside robot boudaries, setting to default (null)");
			this.x = 0.0;
			this.y = 0.0;
			this.sensorRadius = 0.0;
		}
		else{
			this.x = x;
			this.y = y;
			this.sensorRadius = sensorRadius;
		}
	}

	/** 
	 * Indique si le capteur est actif car au dessus d une tache
	 * @param map carte contenant la liste des taches
	 */
	
	public boolean isTriggered(Map map, Robot robot){
		for(int i=0; i<map.getDirtSpots().size(); i++){
			if(map.getDirtSpots().get(i).colliderSensor(this.x, this.y, this.sensorRadius)){
				return true;
			}
		}
		return false;
	}
}
