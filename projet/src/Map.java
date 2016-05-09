import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * @author Yanis
 * Class représentant un terrain avec des obstacles et des taches
 */
public class Map {

	private ArrayList<Obstacle> obstacles;
	private ArrayList<DirtSpot> dirtSpots;
	
	/**
	 * Constructeur de map
	 * @param obstacles ArrayList d'obstacle a ajouter
	 * @param dirtSpots ArrayList de taches a ajouter
	 */
	public Map(ArrayList<Obstacle> obstacles,ArrayList<DirtSpot> dirtSpots){
		this.obstacles=obstacles;
		this.dirtSpots=dirtSpots;
	}
	
	/**
	 * Constructeur par défaut, initialise une carte vide
	 */
	public Map(){
		obstacles=new ArrayList<Obstacle>();
		dirtSpots=new ArrayList<DirtSpot>();
	}
	
	/**
	 * Ajoute un obstacle à la map
	 * @param obs obstacle a ajouter
	 */
	public void addObstacle(Obstacle obs){
		obstacles.add(obs);
	}
	
	/**
	 * Ajoute une tache à la Map
	 * @param ds tache a ajouter
	 */
	public void addDirtSpot(DirtSpot ds){
		dirtSpots.add(ds);
		
	}
	
	/**
	 * Permet de nettoyer les taches, (réduire leur tache) avec un robot
	 * @param rob robot qui nettoie
	 * @param d puissance de nettoyage
	 */
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
	
	/**
	 * Permet de reccupere les obstacles
	 * @return ArrayList d'obstacle
	 */
	public ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}

	/**
	 * Permet de reccupere les taches
	 * @return ArrayList de tache
	 */
	public ArrayList<DirtSpot> getDirtSpots() {
		return dirtSpots;
	}

}
