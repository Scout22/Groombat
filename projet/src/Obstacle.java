
/**
 * @author Yanis
 * Class generique definissant les obstacles
 */
public abstract class Obstacle {
	
	protected String type;
	public Obstacle(){
		type="Not defined";
	}
	
	/**
	 * Verifie si l'obstacle est en contact avec le robot
	 * @param robot robot a verifier
	 * @return boolean indiquant l'etat du capteur : Actif/Inactif
	 */
	public boolean isCollideRobot(Robot robot){
		return false;
	}
	/**
	 * Donne le type d'obstacle
	 * @return type d'obstacle representer
	 */
	public String getType(){
		return type;
	}
	

}
