
/**
 * @author Oscar
 * Classe d�finissant le mode de fonctionnement al�atoire du robot:
 * Avance jusqu'� rencontrer un obstacle, change al�atoirement de direction si l'on rencontre un obstacle
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
