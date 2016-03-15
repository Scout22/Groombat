
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
	
	public String getType(){
		return type;
	}
	
	public abstract String toString();
		
}

