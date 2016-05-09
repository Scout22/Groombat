
/**
 * @author Oscar
 * Classe définissant le mode de fonctionnement aléatoire du robot:
 * Avance jusqu'à rencontrer un obstacle, change aléatoirement de direction si l'on rencontre un obstacle
 */
public class IaRobotRandom extends IaRobot {
	/**
	 * Constructeur de l'IA aleatoire.
	 * @param robot robot controle par l'IA.
	 */
	public IaRobotRandom(Robot robot) {
		this.robot = robot;
		this.mode = 1;
	}

	public void start(){
		
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

	
	
}
