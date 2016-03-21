import java.util.Random;


public class IaRobot {

	Robot robot;
	int mode;
	private boolean is_Rotating_Left = false;
	private boolean is_Rotating_Right = false;
	
	// completer avec le fonctionnement du mode 1
	/**
	 * Constructeur par defaut.
	 * Le mode de l'IA est mis a 1 (mode yolo).
	 * @param robot robot controle par l'IA
	 */
	
	public IaRobot(Robot robot) {
		this.robot = robot;
	}
	
	//a completer avec les differents modes de fonctionnement
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

	
	private boolean front_clear(Bumper bumper) {
		boolean res = bumper.isTriggered() && (Posture.isAngleInSpan(bumper.getAngleInit(), -Math.PI/2, Math.PI) || Posture.isAngleInSpan(Posture.normalize_angle(bumper.getAngleInit()+bumper.getSpan()), -Math.PI/2, Math.PI));
		return !res;
	}

	private void rotateRight() {
		this.robot.setSpeedLeft(-1);
		this.robot.setSpeedRight(1);	
	}

	private void rotateLeft() {
		this.robot.setSpeedLeft(1);
		this.robot.setSpeedRight(-1);	
	}
}
