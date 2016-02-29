import java.util.Random;


public class IaRobot {

	Robot robot;
	int mode; 
	
	// completer avec le fonctionnement du mode 1
	/**
	 * Constructeur par default.
	 * Le mode de l'IA est mis a 1 (mode yolo).
	 * @param robot robot controle par l'IA
	 */
	
	public IaRobot(Robot robot) {
		this.robot = robot;
	}
	
	//a completer avec les differents modes de fonctionnement
	/**
	 * Constructeur de l'IA.
	 * Les differents modes : (1) mode yolo
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
		}
	}
	
	private void yolo(){
		boolean[] freeAngles = new boolean[72]; // tableau des angles libres, 5° de precision
		for(int i=0; i<freeAngles.length; i++){
			freeAngles[i] = true;
		}
		for(int i=0; i<this.robot.sensors.size(); i++){
			if(this.robot.sensors.get(i) instanceof Bumper){
				this.blockAngles(freeAngles, (Bumper)this.robot.sensors.get(i));
			}
		}
		int[] directions = new int[72];
		int counter = 0;
		for(int i=0; i<freeAngles.length; i++){
			if(freeAngles[i]){
				directions[counter] = i*360/freeAngles.length;
				counter++;
			}
		}
		if(counter == freeAngles.length){
			this.robot.setSpeedLeft(1);
			this.robot.setSpeedRight(1);
		}
		else{
			Random r = new Random();
			double angle = directions[r.nextInt(counter)];
			this.robot.getPosture().rotate(angle*Math.PI/360);
			this.robot.setSpeedLeft(1);
			this.robot.setSpeedRight(1);
		}
	}
	
	private void blockAngles(boolean[] freeAngles, Bumper sensor){
		if(freeAngles.length <1){
			System.out.println("Error trying to block angles, freeAngles length is null, returning");
			return;
		}
		int precision = 360/freeAngles.length;
		for(int i=0; i<freeAngles.length; i++){
			if(i*precision > (sensor.getAngleInit()-precision) && i*precision < (sensor.getAngleInit()+sensor.getSpan()+precision)){
				freeAngles[i] = false;
			}
		}
	}
}
