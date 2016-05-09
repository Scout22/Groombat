import java.awt.geom.*;

/**
 * Class gerant la detection de collision entre different type d'objet
 * @author Yanis MAZOUZ
 *
 */
public abstract class Collision {

	private static double precision=0;

	
	/**
	 * Verifie si une ligne est en collision avec un cercle
	 * @param c coordonnées du centre du cercle
	 * @param r rayon du cercle en m
	 * @param line ligne a etudier
	 * @return Boolean à vrai si le cercle de centre c et rayon r est en contacte avec la ligne, faux sionon
	 */
	public static boolean CircleLine(Point2D.Double c,double r,Line2D.Double line){
		if(line.ptSegDist(c)+precision<=r){
			return true;
		}
		else{
			return false;
		}

	}
	
	/**
	 * Verifie si deux cercles sont en collision
	 * @param c1 centre du cercle 1
	 * @param r1 rayon du cercle 1
	 * @param c2 centre du cercle 2
	 * @param r2 rayon du cercle 2
	 * @return Booléen a vrai si les deux cercles sont en contact
	 */
	public static boolean CircleCircle(Point2D.Double c1,double r1,Point2D.Double c2,double r2){

		if(c1.distance(c2)+precision<=r1+r2){
			return true;}
		else{
			return false;
		}

	}

	/**
	 * Verifie si un arc de cercle est en collision avec un cercle
	 * @param ac Centre de l'arc de cercle
	 * @param ar Rayon de l'arc de cercle
	 * @param angleMin angle min de l'arc de cercle en degrée
	 * @param angleSpan etandu de l'arc en degrée
	 * @param c centre du cercle
	 * @param r rayon du cercle
	 * @return Vrai en cas de contact entre les deux elements
	 */
	public static boolean ArcCircle(Point2D.Double ac,double ar,double angleMin,double angleSpan,Point2D.Double c,double r){
		if(CircleCircle(c,r,ac,ar)){
			//Calcule de l'angle entre le vecteur AB et l'axe X 
			double angleContact=Math.atan2(c.getY()-ac.getY(),+c.getX()-ac.getX());
			angleMin=Posture.normalize_angle(angleMin);

			return Posture.isAngleInSpan(angleContact, angleMin, angleSpan);
		}
		return false;
	}
	
	/**
	 * Verifie si un arc de cercle est en collision avec une ligne
	 * @param ac Centre ce l'arc de cercle
	 * @param ar Rayon de l'arc de cercle
	 * @param angleMin angle min de l'arc de cercle en degrée
	 * @param angleSpan etandu de l'arc en degrée
	 * @param line ligne 
	 * @return Vrai en cas de contact entre les deux elements
	 */
	public static boolean ArcLine(Point2D.Double ac,double ar,double angleMin,double angleSpan,Line2D.Double line){
		if(line.ptSegDist(ac)+2*precision<=ar){

			Line2D.Double ln;
			if(GeometricMath.isLeft(line,ac)){
				ln=GeometricMath.normalise(GeometricMath.LeftNormal(line),ar); //Vecteur normale a la ligne
			}
			else{
				ln=GeometricMath.normalise(GeometricMath.RightNormal(line),ar);
			}
			Line2D.Double lnc=new Line2D.Double(ac.getX(),ac.getY(),ac.getX()+ln.getX2(),ac.getY()+ln.getY2());//Normale a la ligne avec centre du cercle comme extremitï¿½


			double angleContact=Math.atan2(-(lnc.getY1()-lnc.getY2()),lnc.getX1()-lnc.getX2());

			return Posture.isAngleInSpan(angleContact, angleMin, angleSpan);

		}
		return false;}



	

}
