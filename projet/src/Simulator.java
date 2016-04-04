import java.util.ArrayList;

public class Simulator {



	private ArrayList <Robot> robots;
	private Map terrain;
	private double width;
	private double height;
	private double deltaT=0.001;

	public Simulator() {
		terrain=new Map();
		robots=new ArrayList <Robot>();
	}
	public Simulator(ArrayList <Obstacle> obs,ArrayList <DirtSpot> dirt,ArrayList <Robot> rob,double deltaT,double width,double height) {
		terrain=new Map(obs,dirt);
		robots=new ArrayList <Robot>();
		robots=rob;
		this.deltaT=deltaT;
		this.width=width;
		this.height=height;
	}


	public double getWidth(){
		return width;
	}
	public double getHeight(){
		return height;
	}
	public void addDirtSpot(DirtSpot ds){
		terrain.addDirtSpot(ds);
	}
	public void addObstacle(Obstacle ob){
		terrain.addObstacle(ob);		
	}

	public void updatePosAllRobot(){

		for(Robot rob:robots){
			Robot tempRobot=rob.clone();
			updatePosRobot(tempRobot);
			if(isValidPos(tempRobot)){
				updatePosRobot(rob);
			}
		}
	}
	private void updateAllSensor() {
		for(Robot rob:robots){
			for(Sensor sens:rob.getSensors()){
				sens.updateState(terrain,rob);
			}
		}

	}

	private void updatePosRobot(Robot rob){
		Posture p =rob.getPosture();
		p.move(deltaT*rob.getSpeedLeft(), deltaT*rob.getSpeedRight(), rob.getDistWheel());
	}
	public boolean isValidPos(Robot rob){
		for(Obstacle ob:terrain.getObstacles()){
			if(ob.isCollideRobot(rob)){
				return false;
			}
		}
		return true;
	}
	
	private void updateTerrain(){
		for(Robot rob:robots){
			terrain.cleanDirtSpot(rob,deltaT*5*rob.getRadius());
		}}
	
	public void addRobot(Robot rob){
		robots.add(rob);
	}

	public ArrayList<Obstacle> getObstacles() {
		return terrain.getObstacles();
	}
	public ArrayList<DirtSpot> getDirtSpots() {
		return terrain.getDirtSpots();
	}
	public ArrayList<Robot> getRobots() {
		return robots;
	}
	public void timeStep() {
		updatePosAllRobot();
		updateAllSensor();
		updateTerrain();

	}
	public double getDeltaT() {
		return deltaT;
	}



}
