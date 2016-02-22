
public class Bumper extends Sensor {

	protected double angleInit;
	protected double angleFin;
	
	/**
	 * Constructeur par default.
	 * Le bumper est positionne a un angle initial nul robot.
	 */
	
	public Bumper() {
		this.angleInit = 0.0;
		this.angleFin = 0.0;
	}

	/**
	 * Constructeur.
	 * Le bumper est positionne avec une position relative a celle de la peripherie du robot
	 * @param angleInit angle de depart du bumper, dans le sens trigo
	 * @param angleFin angle pris dans le sens trigo a partir de l angle initial
	 */
	
	public Bumper(double angleInit, double angleFin) {
		this.angleInit = angleInit;
		this.angleFin = angleFin;
	}
	
	/**
	 * Indique si le capteur est actif car en collision avec un obstacle
	 * @param map carte contenant la liste des obstacles
	 * @param robot robot portant le capteur
	 */
	
	public boolean isTriggered(Map map, Robot robot){
		for(int i=0; i<map.getObstacles().size(); i++){
			if(map.getObstacles().get(i).collideSensor(this.angleInit, this.angleFin, robot)){
				return true;
			}
		}
		return false;
	}

	public double getAngleInit() {
		return angleInit;
	}

	public void setAngleInit(double angleInit) {
		this.angleInit = angleInit;
	}

	public double getAngleFin() {
		return angleFin;
	}

	public void setAngleFin(double angleFin) {
		this.angleFin = angleFin;
	}

}
