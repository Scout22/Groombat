import java.util.ArrayList;


/**
 * @author Oscar
 * Class representant un robot, contient sa position, ses capteurs et son IA
 */

public class Robot {

	private double speedRight;
	private double speedLeft;
	private Posture posture;
	private double radius;
	private IaRobot ia;
	public ArrayList<Sensor> sensors;
	private double distWheel;

	/**
	 * Constructeur du robot.
	 * @param speedLeft vitesse de depart de la roue gauche (en m/s).
	 * @param speedRight vitesse de depart de la roue droite (en m/s).
	 * @param x position en x de depart du robot (en m).
	 * @param y position en y de depart du robot (en m).
	 * @param theta positionnement angulaire de depart du robot (en degre).
	 * @param radius rayon du robot (en m).
	 * @param mode mode de fonctionnement du robot (entier).
	 * @param distWheel distance entre les roues du robot (en m).
	 */
	public Robot(double speedLeft, double speedRight, double x, double y,double theta, double radius, int ia_,double distWheel){
		this.speedLeft = speedLeft;
		this.speedRight = speedRight;
		this.posture = new Posture(x,y,theta);
		this.sensors = new ArrayList<Sensor>();
		this.radius = radius;
		this.distWheel=distWheel;
		switch(ia_){
		case 2:
			this.ia=new IaRobotTache(this);
			break;
		case 1:
			this.ia=new IaRobotRandom(this);
			break;
		}
	}

	/**
	 * Autre contstructeur du robot, utile dans certaines fonctions.
	 * @param speedLeft vitesse de depart de la roue gauche (en m/s).
	 * @param speedRight vitesse de depart de la roue droite (en m/s).
	 * @param p posture du robot.
	 * @param radius rayon du robot (en m).
	 * @param distWheel distance entre les roues du robot (en m).
	 */
	public Robot(double speedLeft, double speedRight, Posture p, double radius,double distWheel){
		this.speedLeft = speedLeft;
		this.speedRight = speedRight;
		this.posture = p;
		this.radius = radius;
		this.distWheel=distWheel;
		ia=new IaRobot();

	}

	/**
	 * Renvoie la distance entre les roues du robot (en m).
	 * @return distWheel Renvoie la distance entre les roues du robot (en m).
	 */
	public double getDistWheel(){
		return distWheel;
	}

	/** Renvoie l'IA du robot.
	 * @return ia l'IA du robot.
	 */
	public IaRobot getIa() {
		return ia;
	}

	/**
	 * Associe une IA au robot.
	 * @param ia IA que l'on veut associer au robot.
	 */
	public void setIa(IaRobot ia) {
		this.ia = ia;
	}

	/**
	 * Renvoie la vitesse de la roue droite.
	 * @return speedRight la vitesse de la roue droite.
	 */
	public double getSpeedRight() {
		return speedRight;
	}

	/**
	 * Fixe la vitesse de la roue droite.
	 * @param speedRight la vitesse souhaitee.
	 */
	public void setSpeedRight(double speedRight) {
		this.speedRight = speedRight;
	}

	/**
	 * Renvoie la vitesse de la roue gauche.
	 * @return speedLeft la vitesse de la roue gauche.
	 */
	public double getSpeedLeft() {
		return speedLeft;
	}

	/**
	 * Fixe la vitesse de la roue gauche.
	 * @param speedLeft la vitesse souhaitee.
	 */
	public void setSpeedLeft(double speedLeft) {
		this.speedLeft = speedLeft;
	}

	/**
	 * Renvoie la Posture du robot.
	 * @return posture la posture du robot.
	 */
	public Posture getPosture() {
		return posture;
	}

	/**
	 * Fixe la posture du robot.
	 * @param posture posture souhaitee.
	 */
	public void setPosture(Posture posture) {
		this.posture = posture;
	}

	/**
	 * Renvoie la liste des capteurs du robot.
	 * @return sensors la liste des capteurs du robot.
	 */
	public ArrayList<Sensor> getSensors() {
		return sensors;
	}

	/**
	 * Associe une liste de capteurs au robot.
	 * @param sensors la liste de capteurs souhaitee.
	 */
	public void setSensors(ArrayList<Sensor> sensors) {
		this.sensors = sensors;
	}

	/**
	 * Ajoute un capteur a la liste des capteurs du robot.
	 * @param sensor capteur a ajouter.
	 */
	public void addSensor(Sensor sensor){
		this.sensors.add(sensor);
	}

	/**
	 * Fixe le rayon du robot.
	 * @param radius rayon souhaite.
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Renvoie le rayon du robot.
	 * @return rayon du robot.
	 */
	public double getRadius() {
		return this.radius;
	}

	/**
	 * Renvoie la position en x du robot.
	 * @return x la position en x du robot.
	 */
	public double getX() {
		return this.posture.getX();
	}

	/**
	 * Renvoie la position en y du robot.
	 * @return y la position en y du robot.
	 */
	public double getY() {
		return this.posture.getY();
	}

	/**
	 * Renvoie l'angle d'orientation du robot.
	 * @return theta l'angle d'orientation du robot.
	 */
	public double getTheta(){
		return this.posture.getTheta();
	}

	public Robot clone(){
		return new Robot(speedLeft,speedRight, posture.getX(), posture.getY(),posture.getTheta(),  radius,  0, distWheel);
	}

	public void setDistWheel(double dist_w) {
		distWheel=dist_w;

	}


}
