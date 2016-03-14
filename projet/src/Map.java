import java.awt.geom.Point2D;
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
	public void cleanDirtSpot(Robot rob,double d){
		ArrayList<DirtSpot> destroy = new ArrayList<DirtSpot>();
		for(DirtSpot ds:dirtSpots){
			if(Collision.CircleCircle(new Point2D.Double(rob.getX(), rob.getY()), rob.getRadius(), ds.getPt(), ds.getRadius())){
				if(ds.clean(d)){
					destroy.add(ds);
				}
			}
		}
		for(DirtSpot dest:destroy){
			dirtSpots.remove(dest);
		}
	}
	
	public ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}

	public ArrayList<DirtSpot> getDirtSpots() {
		return dirtSpots;
	}

}
