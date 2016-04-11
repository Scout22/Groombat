import java.util.Random;


public class IaRobot {

	Robot robot;
	int mode;
	private boolean is_Rotating_Left = false;
	private boolean is_Rotating_Right = false;
	
	/**
	 * Constructeur par defaut.
	 * Le mode de l'IA est mis a 1 (mode yolo).
	 * @param robot robot controle par l'IA
	 */
	
	public IaRobot(Robot robot) {
		this.robot = robot;
	}
	
	/**
	 * Constructeur de l'IA.
	 * Les differents modes : (1) mode yolo (2) mode cleaner, le robot s'arrete sur les taches
	 * @param robot robot controle par l'IA.
	 * @param mode entier representant le mode de fonctionnement de l'IA.
	 */
	
	public IaRobot(Robot robot, int mode) {
		this.robot = robot;
		this.mode = mode;
	}

	/**
	 * Initialisation pour un pas de temps de l'IA d'un robot.
	 * Donne au final une vitesse aux deux roues du robot.
	 */
	public void start(){
		switch(this.mode){
		case 1:
			this.yolo();
			break;
		case 2:
			this.cleaner();
			break;
		}
	}
	
	
	/**
	 * Mode cleaner de l'IA.
	 * Le robot cherche aleatoirement les taches et s'arrete quand il passe dessus.
	 */
	private void cleaner() {
		if(is_On_Dirt()){
			this.robot.setSpeedLeft(0); 
			this.robot.setSpeedRight(0);
		}
		else{
			boolean all_clear = true;
			for(int i=0; i<this.robot.sensors.size(); i++){
				if(this.robot.sensors.get(i) instanceof Bumper){
					if(!this.front_clear((Bumper)this.robot.sensors.get(i))){
						all_clear = false;
					}
				}
			}

			if(all_clear){
				this.robot.setSpeedLeft(1); 
				this.robot.setSpeedRight(1);
				is_Rotating_Left = false;
				is_Rotating_Right = false;
			}
			else{
				Random r = new Random();
				int ran = r.nextInt(2);
				if(!is_Rotating_Right && (ran==0 || is_Rotating_Left)){
					rotateLeft();
					is_Rotating_Left = true;
				}
				else{
					rotateRight();
					is_Rotating_Right = true;
				}
			}
		}
	}

	
	/**
	 * Test si le robot associe a cet IA est sur une tâche
	 */
	private boolean is_On_Dirt() {
		for(Sensor sensor:this.robot.getSensors()){
			if(sensor instanceof DirtSensor){
				DirtSensor ds = (DirtSensor) sensor;
				if(ds.isTriggered()){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Mode yolo de l'IA.
	 * Le robot fonce tout droit puis tourne vers la gauche tant qu'il est bloque.
	 */
	private void yolo(){
		boolean all_clear = true;
		for(int i=0; i<this.robot.sensors.size(); i++){
			if(this.robot.sensors.get(i) instanceof Bumper){
				if(!this.front_clear((Bumper)this.robot.sensors.get(i))){
					all_clear = false;
				}
			}
		}

		if(all_clear){
			this.robot.setSpeedLeft(1); 
			this.robot.setSpeedRight(1);
		}
		else{
			rotateLeft();
		}
	}

	/**
	 * Test si le robot peut avancer ou est bloque.
	 */
	private boolean front_clear(Bumper bumper) {
		boolean res = bumper.isTriggered() && (Posture.isAngleInSpan(bumper.getAngleInit(), -Math.PI/2, Math.PI) || Posture.isAngleInSpan(Posture.normalize_angle(bumper.getAngleInit()+bumper.getSpan()), -Math.PI/2, Math.PI));
		return !res;
	}

	/**
	 * Tourne le robot vers la droite.
	 */
	private void rotateRight() {
		this.robot.setSpeedLeft(-1);
		this.robot.setSpeedRight(1);	
	}

	/**
	 * Tourne le robot vers la gauche.
	 */
	private void rotateLeft() {
		this.robot.setSpeedLeft(1);
		this.robot.setSpeedRight(-1);	
	}
}
