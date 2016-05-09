import java.awt.geom.Point2D;

public class DirtSpot {
	private double x;
	private double y;
	private double rayon;
	
	/**
	 * Creation d'une tache de position (x,y) et de rayon rayon.
	 * Les points de vie initiaux de la tache dependent de son rayon
	 * @param x position en x du centre de la tache
	 * @param y position en y du centre de la tache
	 * @param rayon rayon de la tache
	 */
	DirtSpot(double x,double y,double rayon){
		this.x=x;
		this.y=y;
		this.rayon=rayon;
	}
	
	/**
	 * Renvoie la position en x du capteur.
	 */
	public double getX(){
		return x;
		
	}
	
	/**
	 * Renvoie la position en y du capteur.
	 */
	public double getY(){
		return y;
		
	}
	
	/**
	 * Renvoie le rayon du capteur.
	 */
	public double getRadius(){
		return rayon;
	}
	
	private double getLife(){
		return Math.PI*rayon*rayon*30;
	}

	/**
	 * Permet de netoyer une tache en la rendant plus petite jusqu'a la faire disparaitre.
	 * @param d enleve d points de vie a une tache.
	 */
	public boolean clean(double d) {
		rayon=Math.sqrt((getLife()-d)/(30*Math.PI));
		if(getLife()<2){
			return true;
		}
		else {
			return false;
		}	
	}

	/**
	 * Fonction permettant reccuperer le centre de la tache
	 * @return un point indicant la position du centre de la tache
	 */
	public Point2D.Double getPt() {
		return new Point2D.Double(x, y);
	}

}
