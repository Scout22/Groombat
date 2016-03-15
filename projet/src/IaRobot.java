import java.util.Random;


public class IaRobot {

	Robot robot;
	int mode;
	private boolean is_Rotating = false;
	private double angle_Goal = 0;
	
	// completer avec le fonctionnement du mode 1
	/**
	 * Constructeur par defaut.
	 * Le mode de l'IA est mis a 1 (mode yolo).
	 * @param robot robot controle par l'IA
	 */
	
	public IaRobot(Robot robot) {
		this.robot = robot;
	}
	
	//a completer avec les differents modes de fonctionnement
	/**
	 * Constructeur de l'IA.
	 * Les differents modes : (1) mode yolo (2) mode cleaner, le robot s'arrete sur les taches
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
		case 2:
			this.cleaner();
			break;
		}
	}
	
	private void cleaner() {
		if(is_On_Dirt()){
			this.robot.setSpeedLeft(0); 
			this.robot.setSpeedRight(0);
		}
		else if(this.is_Rotating){
			if(this.angle_Goal < this.robot.getPosture().getTheta()+0.1 && this.angle_Goal > this.robot.getPosture().getTheta()-0.1){
				this.is_Rotating = false;
				this.angle_Goal = 0;
			}
			else if(this.angle_Goal - this.robot.getPosture().getTheta() <Math.PI){
				rotateLeft();
			}
			else{
				rotateRight();
			}
		}
		else{
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
			//System.out.println("Counter = " + counter + " and length = " + freeAngles.length + "and free0 : "+freeAngles[(int)freeAngles.length/4]);
			if(counter == freeAngles.length || freeAngles[(int)freeAngles.length/4]){
				this.robot.setSpeedLeft(1); 
				this.robot.setSpeedRight(1);
			}
			else{
				Random r = new Random();
				double angle = directions[r.nextInt(counter)];
				this.angle_Goal = (Math.PI*angle)/180;
				if(this.angle_Goal - this.robot.getPosture().getTheta() <Math.PI){
					rotateLeft();
				}
				else{
					rotateRight();
				}
			}
		}
		
	}

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

	private void yolo(){
		if(this.is_Rotating){
			if(this.angle_Goal < this.robot.getPosture().getTheta()+0.1 && this.angle_Goal > this.robot.getPosture().getTheta()-0.1){
				this.is_Rotating = false;
				this.angle_Goal = 0;
			}
			else if(this.angle_Goal - this.robot.getPosture().getTheta() <Math.PI){
				rotateLeft();
			}
			else{
				rotateRight();
			}
		}
		else{
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
			//System.out.println("Counter = " + counter + " and length = " + freeAngles.length + "and free0 : "+freeAngles[(int)freeAngles.length/4]);
			if(counter == freeAngles.length || freeAngles[(int)freeAngles.length/4]){
				this.robot.setSpeedLeft(1); 
				this.robot.setSpeedRight(1);
			}
			else{
				Random r = new Random();
				double angle = directions[r.nextInt(counter)];
				this.angle_Goal = (Math.PI*angle)/180;
				//this.angle_Goal = 0; // bullshit
				if(this.angle_Goal - this.robot.getPosture().getTheta() <Math.PI){
					rotateLeft();
				}
				else{
					rotateRight();
				}
			}
		}
	}
	
	private void rotateRight() {
		this.robot.setSpeedLeft(-1);
		this.robot.setSpeedRight(1);	
	}

	private void rotateLeft() {
		this.robot.setSpeedLeft(1);
		this.robot.setSpeedRight(-1);	
	}

	private void blockAngles(boolean[] freeAngles, Bumper sensor){
		if(freeAngles.length <1){
			System.out.println("Error trying to block angles, freeAngles length is null, returning");
			return;
		}
		int precision = 360/freeAngles.length;
		for(int i=0; i<freeAngles.length; i++){
			if(i*precision > (sensor.getAngleInit()-precision) && i*precision < (sensor.getAngleInit()+sensor.getSpan()+precision) && sensor.isTriggered()){
				freeAngles[i] = false;
			}
		}
	}
}
