import java.util.Random;

/**
 * @author Oscar
 * Classe définissant le mode de fonctionnement cleaner du robot:
 * Avance jusqu'à rencontrer un obstacle, change aléatoirement de direction si l'on rencontre un obstacle, reste sur une tache si on en rencontre
 */
public class IaRobotTache extends IaRobot {
	
	/**
	 * Constructeur de l'IA.
	 * @param robot robot controle par l'IA.
	 */

	public IaRobotTache(Robot robot) {
		this.robot = robot;
		this.mode = 2;
	}
	
	public void start(){
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
	 * Test si le robot associe a cet IA est sur une tache
	 * @return vrai si l'on est sur une tache
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
}
