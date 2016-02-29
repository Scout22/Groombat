import java.util.ArrayList;


public class Robot {

	private double speedRight;
	private double speedLeft;
	private Posture posture;
	private double radius;
	private IaRobot ia;
	public ArrayList<Sensor> sensors;
	
	/**
	 * Constructeur par default.
	 * Le robot possede une position et une ia par default, aucun capteur, des vitesses nulles et un rayon de 10cm.
	 */
	public Robot(){
		this.speedLeft = 0.0;
		this.speedRight = 0.0;
		this.posture = new Posture(0.0,0.0,0.0);
		this.radius = 0.1;
		this.ia = new IaRobot(this);
		this.sensors = new ArrayList<Sensor>();
	}
	
	/**
	 * Constructeur du robot.
	 * @param speedLeft vitesse de depart de la roue gauche (en m/s).
	 * @param speedRight vitesse de depart de la roue droite (en m/s).
	 * @param x position en x de depart du robot (en m).
	 * @param y position en y de depart du robot (en m).
	 * @param theta positionnement angulaire de depart du robot (en degre).
	 * @param mode entier correspondant au mode de fonctionnement de l'ia du robot, 1 pour la valeur par default.
	 * @param sensors liste des capteurs du robot.
	 */
	public Robot(double speedLeft, double speedRight, double x, double y, double theta, int mode, ArrayList<Sensor> sensors){
		this.speedLeft = speedLeft;
		this.speedRight = speedRight;
		this.posture = new Posture(x,y,theta);
		this.ia = new IaRobot(this, mode);
		this.sensors = sensors;
	}
	
	
	public IaRobot getIa() {
		return ia;
	}

	public void setIa(IaRobot ia) {
		this.ia = ia;
	}

	public double getSpeedRight() {
		return speedRight;
	}

	public void setSpeedRight(double speedRight) {
		this.speedRight = speedRight;
	}

	public double getSpeedLeft() {
		return speedLeft;
	}

	public void setSpeedLeft(double speedLeft) {
		this.speedLeft = speedLeft;
	}

	public Posture getPosture() {
		return posture;
	}

	public void setPosture(Posture posture) {
		this.posture = posture;
	}

	public ArrayList<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(ArrayList<Sensor> sensors) {
		this.sensors = sensors;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return this.radius;
	}

	public double getX() {
		return this.posture.getX();
	}

	public double getY() {
		return this.posture.getY();
	}
	
	public double getTheta(){
		return this.posture.getTheta();
	}

}
