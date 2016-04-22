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
		addFence();
	}

	public Simulator(String mode){
		if(mode=="demo"){
			Robot groombat = new Robot(-0.4,-0.5,2,1,Math.toRadians(0),0.4,2,0.2);
			robots = new ArrayList<Robot>();
			robots.add(groombat);


			DirtSensor sens = new DirtSensor(0.1,-2*Math.PI/3,0.05,groombat);
			groombat.addSensor(sens);

			for(int i=-11;i<360;i+=10){
				Bumper bump = new Bumper(i,11);
				groombat.addSensor(bump);}

			Trashcan trash = new Trashcan(0.5,2.5,0.2);
			Trashcan trash2 = new Trashcan(2,2,0.2);
			Trashcan trash3 = new Trashcan(3,1,0.2);

			Wall wall1 = new Wall(0,0,0,4);
			Wall wall2 = new Wall(0,4,4,4);
			Wall wall3 = new Wall(4,4,4,0);
			Wall wall4 = new Wall(4,0,0,0);
			Wall wall5 = new Wall(2,2,2,4);

			terrain=new Map();
			terrain.addObstacle(trash);
			terrain.addObstacle(trash2);
			terrain.addObstacle(trash3);

			terrain.addObstacle(wall1);
			terrain.addObstacle(wall2);
			terrain.addObstacle(wall3);
			terrain.addObstacle(wall4);
			terrain.addObstacle(wall5);


			DirtSpot dirt = new DirtSpot(1,1,0.4);
			terrain.addDirtSpot(dirt);

			width=4;
			height=4;
		}
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
	public void setMap(Map mapy) {
		terrain=mapy;

	}
	public void addFence(){
		terrain.addObstacle(new Wall(0,0,0,height));
		terrain.addObstacle(new Wall(0,height,width,height));
		terrain.addObstacle(new Wall(width,height,width,0));
		terrain.addObstacle(new Wall(width,0,0,0));
	}
	public Map getMap() {
		return terrain;
	}


}
