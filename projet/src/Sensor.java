
public abstract class Sensor {

	public Sensor() {
		
	}

	
	
	/**
	 * Indique si le capteur est active par un element de la carte
	 * retourne faux par default
	 * @param map carte parcourue par le robot
	 */
	public boolean isTriggered(Map map){
		return false;
	}
}

