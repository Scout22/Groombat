import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Class fournissant des fonctions de calcul geometrique
 * @author Yanis
 *
 */
public abstract class GeometricMath {
	
	
	/**
	 * Fournis à partir d'une ligne sa normal à gauche
	 * @param line ligne dont on cherche a connaitre la normale
	 * @return normale à gauche de la ligne
	 */
	public static Line2D.Double LeftNormal(Line2D.Double line){

		return new Line2D.Double(0,0,line.y1-line.y2,line.x1-line.x2);
	}
	
	/**
	 * Fournis à partir d'une ligne sa normal à droite
	 * @param line ligne dont on cherche a connaitre la normale
	 * @return normale à droite de la ligne
	 */
	public static Line2D.Double RightNormal(Line2D.Double line){

		return new Line2D.Double(0,0,line.y2-line.y1,line.x2-line.x1);
	}

	/**
	 * Normalise un vecteur et le multiplie par un coefficient k
	 * @param line1 vecteur a normalisé
	 * @param k coefficient de normalisation
	 * @return vecteur normalisé
	 */
	public static Line2D.Double normalise(Line2D.Double line1,double k)
	{

		double norme=Math.sqrt(Math.pow((line1.getP2().getX()-line1.getP1().getX()), 2)+Math.pow(((line1.getP2().getY()-line1.getP1().getY())), 2));
		return new Line2D.Double(0, 0, k*(line1.getP2().getX()-line1.getP1().getX())/norme, k*((line1.getP2().getY()-line1.getP1().getY()))/norme);
	}

	
	/**
	 * Verifie si un point est à gauche d'un segment
	 * @param line1 segment considere
	 * @param p1 point considerer
	 * @return Boolean vrai si le point est à gauche faux sinon
	 */
	public static boolean isLeft(Line2D.Double line1,Point2D.Double p1){
		double D_x=line1.getX2()-line1.getX1();
		double D_y=line1.getY2()-line1.getY1();
		double T_x=p1.getX()-line1.getX1();
		double T_y=p1.getY()-line1.getY1();
		if((D_x*T_y-D_y*T_x)<0){
			return false;}
		return true;
	}
}
