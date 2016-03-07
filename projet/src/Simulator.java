import java.util.ArrayList;

public class Simulator {
	

	
	private ArrayList <Obstacle> obstacles;
	private ArrayList <DirtSpot> dirtSpots;
	private ArrayList <Robot> robots;
	private double width;
	private double height;
	private double deltaT=0.001;
	
	public Simulator() {
		obstacles=new ArrayList <Obstacle>();
		dirtSpots=new ArrayList <DirtSpot>();
		robots=new ArrayList <Robot>();
		}
	public Simulator(ArrayList <Obstacle> obs,ArrayList <DirtSpot> dirt,ArrayList <Robot> rob,double deltaT,double width,double height) {
		obstacles=new ArrayList <Obstacle>();
		dirtSpots=new ArrayList <DirtSpot>();
		robots=new ArrayList <Robot>();
		obstacles=obs;
		dirtSpots=dirt;
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
		dirtSpots.add(ds);		
	}
	public void addObstacle(Obstacle ob){
		obstacles.add(ob);		
	}
	
	public void updatePosAllRobot(){
		Robot tempRobot=new Robot();
		for(Robot rob:robots){
			tempRobot=rob;
			updatePosRobot(tempRobot);
			if(isValidPos(tempRobot)){
				rob=tempRobot;
			}
			
		}
	}
	
	public void updatePosRobot(Robot rob){
		Posture p =rob.getPosture();
		p.move(deltaT*rob.getSpeedLeft(), deltaT*rob.getSpeedRight(), rob.getDistWheel());
	}
	public boolean isValidPos(Robot rob){
		for(Obstacle ob:obstacles){
			if(ob.isCollideRobot(rob)){
				return false;
			}
		}
		return true;
	}
	public void addRobot(Robot rob){
		robots.add(rob);
	}

	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}
	public ArrayList<DirtSpot> getDirtSpots() {
		return dirtSpots;
	}
	public ArrayList<Robot> getRobots() {
		return robots;
	}


}
