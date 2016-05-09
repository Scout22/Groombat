import java.awt.geom.*;

/**
 * @author Yanis
 * Classe définissant les obstacles de formes ciculaires (poubelle)
 */
public class Trashcan extends Obstacle {
	private double x;
	private double y;
	private double rayon;
	
	/**
	 * Instancie un obstacle de type poubelle.
	 * @param x position en x du centre de la poubelle.
	 * @param y position en y du centre de la poubelle.
	 * @param rayon rayon de la poubelle.
	 */
	Trashcan(double x, double y, double rayon){
		type="Trashcan";
		this.x=x;
		this.y=y;
		if(rayon>0){
			this.rayon=rayon;}
		else{
			rayon=0;
		}
	}
	
	/**
	 * Renvoie la position en x du centre de la poubelle.
	 * @return x la position en x du centre de la poubelle.
	 */
	public double getX(){
		return x;
	}
	
	/**
	 * Renvoie la position en y du centre de la poubelle.
	 * @return y la position en y du centre de la poubelle.
	 */
	public double getY(){
		return y;
	}
	
	/**
	 * Renvoie le rayon de la poubelle.
	 * @return rayon le rayon de la poubelle.
	 */
	public double getRadius(){
		return rayon;
	}
	
	
	/**
	 * Renvoie le centre de l'obstacle
	 * @return Centre de l'obstacle
	 */
	public Point2D.Double getPt(){
		return new Point2D.Double(x,y);
	}
	
	
	/* !CodeTemplates.overridecomment.nonjd!
	 * @see Obstacle#isCollideRobot(Robot)
	 */
	public boolean isCollideRobot(Robot rob){		
		return Collision.CircleCircle(getPt(), rayon, new Point2D.Double(rob.getX(),rob.getY()),rob.getRadius());
	}



}


