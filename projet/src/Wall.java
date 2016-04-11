import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

//Les murs ne peuvent etre que verticale ou horizontale.
public class Wall extends Obstacle {
	private Line2D.Double line;
	
	/**
	 * Instancie un obstacle de type mur, definit a partir de deux points.
	 * @param x1 la position en x du premier point.
	 * @param y1 la position en y du premier point.
	 * @param x2 la position en x du deuxieme point.
	 * @param y2 la position en y du deuxieme point.
	 */
	Wall(double x1,double y1,double x2,double y2){
		line=new Line2D.Double(x1,y1,x2,y2);
		type="Wall";
	}
	
	/**
	 * Renvoie la position en x du premier point.
	 * @return x1 la position en x du premier point.
	 */
	public double getX1(){
		return line.getX1();
	}
	
	/**
	 * Renvoie la position en x du deuxieme point.
	 * @return x2 la position en x du deuxieme point.
	 */
	public double getX2(){
		return line.getX2();
	}
	
	/**
	 * Renvoie la position en y du premier point.
	 * @return y1 la position en y du premier point.
	 */
	public double getY1(){
		return line.getY1();
	}
	
	/**
	 * Renvoie la position en x du premier point.
	 * @return x1 la position en x du premier point.
	 */
	public double getY2(){
		return line.getY2();
	}

	public Line2D.Double getLine(){
		return line;
	}
	
	public boolean isCollideRobot(Robot rob){
		double xRobot=rob.getX();
		double yRobot=rob.getY();
		double rRobot=rob.getRadius();
		Point2D.Double cRobot= new Point2D.Double(xRobot, yRobot);
		return Collision.CircleLine(cRobot,rRobot,line);
	}

}
