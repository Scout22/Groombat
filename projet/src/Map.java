import java.util.ArrayList;

public class Map {

	private ArrayList<Obstacle> obstacles;
	private ArrayList<DirtSpot> dirtSpots;
	
	public Map(ArrayList<Obstacle> obstacles,ArrayList<DirtSpot> dirtSpots){
		this.obstacles=obstacles;
		this.dirtSpots=dirtSpots;
	}
	public Map(){
		obstacles=new ArrayList<Obstacle>();
		dirtSpots=new ArrayList<DirtSpot>();
	}
	
	public void addObstacle(Obstacle obs){
		obstacles.add(obs);
	}
	
	public void addDirtSpot(DirtSpot ds){
		dirtSpots.add(ds);
		
	}
	
	public ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}

	public ArrayList<DirtSpot> getDirtSpots() {
		return dirtSpots;
	}

}
