
public class Bumper extends Sensor {

	protected double angleInit;
	protected double span;
	protected boolean triggered;
	
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
		this.angleInit = angleInit;
		this.span = span;
		this.triggered = false;
	}
	
	/**
	 * Indique si le capteur est actif car en collision avec un obstacle.
	 * @param map carte contenant la liste des obstacles.
	 * @param robot robot portant le capteur.
	 */
	
	public void isTriggered(Map map, Robot robot){
		this.triggered = false;
		for(int i=0; i<map.getObstacles().size(); i++){
			if(map.getObstacles().get(i).collideSensor(this.angleInit, this.span, robot)){
				this.triggered = true;
				return;
			}
		}
	}

	public boolean isTriggered() {
		return triggered;
	}

	public void setTriggered(boolean triggered) {
		this.triggered = triggered;
	}

	public double getAngleInit() {
		return angleInit;
	}

	public void setAngleInit(double angleInit) {
		this.angleInit = angleInit;
	}

	public double getSpan() {
		return span;
	}

	public void setSpan(double span) {
		this.span = span;
	}

}
