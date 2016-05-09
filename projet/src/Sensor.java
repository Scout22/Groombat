
/**
 * @author Yanis
 * Class generique permettant de representer un capteur du robot
 */
public abstract class Sensor {
	protected String type;
	public Sensor() {
		type="";
	}
	/**
	 * Met a jour l'etat du capteur
	 * @param map carte de l'environement ou se trouve le robot
	 */
	public abstract void updateState(Map map,Robot rob );
	
	/**
	 * Donne le type du capteur.
	 * @return type le type du capteur.
	 */
	public String getType(){
		return type;
	}
	
	public abstract String toString();
		
}

