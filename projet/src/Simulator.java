import java.util.ArrayList;

public class Simulator {
	

	
	private ArrayList <Obstacle> obstacles;
	private ArrayList <DirtSpot> dirtSpots;
	private ArrayList <Robot> robots;
	public Simulator() {
		obstacles=new ArrayList <Obstacle>();
		dirtSpots=new ArrayList <DirtSpot>();
		robots=new ArrayList <Robot>();
		}
	public void addDirtSpot(DirtSpot ds){
		dirtSpots.add(ds);		
	}
	public void addObstacle(Obstacle ob){
		obstacles.add(ob);		
	}
	public void updatePosRobot(){
		for(Robot rob:robots){
			
		}
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
