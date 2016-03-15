@SuppressWarnings("serial")
public class TextUI extends UI {
	
	private Simulator sim;
	

	public TextUI(Simulator sim)
	{
		this.sim = sim;
	}
	
	
	
	private void paintRobot(Robot robot) {
		int i=1;
		System.out.println("\n\n\nPosition du robot "+robot.getPosture().toString());
		System.out.println("Etat des capteurs du robot:");
		
		for(Sensor sens:robot.getSensors()){
			System.out.println("n°"+i+":"+sens.toString());
			i++;
			}
			
		}



	@Override
	void updateDisplay() {
		for(Robot rob:sim.getRobots()){
			paintRobot(rob);
		}
	}

	
}


