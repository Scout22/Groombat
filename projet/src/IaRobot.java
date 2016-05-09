/**
 * @author Oscar
 * Class definissant gerant l'intelligence du robot
 */
public class IaRobot {

	protected Robot robot;
	protected int mode;
	protected boolean is_Rotating_Left = false;
	protected boolean is_Rotating_Right = false;

	/**
	 * Constructeur par defaut.
	 * Le mode de l'IA est mis a 1 (mode yolo).
	 * @param robot robot controle par l'IA
	 */

	public  IaRobot() {
		mode=-1;
	}


	/**
	 * Initialisation pour un pas de temps de l'IA d'un robot.
	 * Donne au final une vitesse aux deux roues du robot.
	 */
	public void start(){
	}


	/**
	 * Test si le robot peut avancer ou est bloque.
	 */
	protected boolean front_clear(Bumper bumper) {
		boolean res = bumper.isTriggered() && (Posture.isAngleInSpan(bumper.getAngleInit(), -Math.PI/2, Math.PI) || Posture.isAngleInSpan(Posture.normalize_angle(bumper.getAngleInit()+bumper.getSpan()), -Math.PI/2, Math.PI));
		return !res;
	}

	/**
	 * Tourne le robot vers la droite.
	 */
	protected void rotateRight() {
		this.robot.setSpeedLeft(-1);
		this.robot.setSpeedRight(1);	
	}

	/**
	 * Tourne le robot vers la gauche.
	 */
	protected void rotateLeft() {
		this.robot.setSpeedLeft(1);
		this.robot.setSpeedRight(-1);	
	}
}
