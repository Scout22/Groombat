/**
 * @author Yanis
 * Class permettant d'afficher lancer la simulation dans un le terminal en mode texte
 */
@SuppressWarnings("serial")
public class TextUI extends UI {
	private Simulator sim;
	/**
	 * Constucteur de la classe
	 * @param sim simulateur a afficher 
	 */
	public TextUI(Simulator sim)
	{
		this.sim = sim;
	}
	
	
	
	/**
	 * Affiche la position du robot
	 * @param robot robot dont on cherche a afficher la posture
	 */
	private void paintRobot(Robot robot) {
		int i=1;
		System.out.println("\n\n\nPosition du robot "+robot.getPosture().toString());
		System.out.println("Etat des capteurs du robot:");
		
		for(Sensor sens:robot.getSensors()){
			System.out.println("n°"+i+":"+sens.toString());
			i++;
			}
			
		}



	/* !CodeTemplates.overridecomment.nonjd!
	 * @see UI#updateDisplay()
	 */
	@Override
	void updateDisplay() {
		for(Robot rob:sim.getRobots()){
			paintRobot(rob);
		}
	}

	
}


