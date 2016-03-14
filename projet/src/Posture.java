import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Represente une position x,y + un angle
 */
public class Posture implements Cloneable
{
	private double x, y, theta;
	/**
	 * Constructeur
	 */
	public Posture(double x, double y, double theta)
	{
		this.x = x;
		this.y = y;
		this.theta = theta;
	}
	public double getX() { return x;  }
	public double getY() { return y; }
	public double getTheta() { return theta; }
	public Object clone() { return new Posture(x, y, theta); }
	/**
	 * rotation autour de la coordonnee (0, 0)
	 */
	public Posture rotate(double alpha)
	{
		double x_ = Math.cos(alpha) * getX() - Math.sin(alpha) * getY();
		double y_ = Math.cos(alpha) * getY() + Math.sin(alpha) * getX();
		x = x_;
		y = y_;
		theta = normalize_angle(alpha + theta);
		return this;
	}
	
	public static Line2D.Double rotate(Line2D.Double org,Point2D.Double c,double alpha){
		double x1 = org.getX1();
		double y1 = org.getY1();
		double x2 = org.getX2();
		double y2 = org.getY2();
		double a = c.getX();
		double b = c.getY();
		double X1 = x1 - a;
		double	Y1 = y1 - b;
		double X2 = x2 - a;
		double	Y2 = y2 - b;
		double x1Bis = a + X1 * Math.cos(alpha) - Y1 * Math.sin(alpha);
		double y1Bis = b + X1 * Math.sin(alpha) + Y1 * Math.cos(alpha);
		double x2Bis = a + X2 * Math.cos(alpha) - Y2 * Math.sin(alpha);
		double y2Bis = b + X2 * Math.sin(alpha) + Y2 * Math.cos(alpha);
		return new Line2D.Double(x1Bis,y1Bis,x2Bis,y2Bis);
		
	}
	/**
	 * Deplace un robot en fonction de la distance parcourue par 
	 * chaque roue
	 * @param d_l distance parcourue par la roue gauche
	 * @param d_r distance parcourue par la roue droite
	 * @param dist distance entre les deux roues
	 */
	public Posture move(double d_l, double d_r,
			double dist)
	{
		double alpha = (d_r - d_l) / dist;
		Posture p = null;
		if (alpha > 1e-20  || alpha < -1e-20)
		{
			double r = (d_l / alpha) +  dist / 2;
			double d_x = (Math.cos(alpha) - 1) * r;
			double d_y = Math.sin(alpha) * r;
			p = new Posture(d_x, d_y, alpha);
			p.rotate(this.getTheta() - Math.PI / 2);
			p.theta = alpha;
		}
		else
		{
			p = new Posture(d_l * Math.cos(this.getTheta()),
					d_l * Math.sin(this.getTheta()),
					0);
		}

		this.x += p.x;
		this.y += p.y;
		this.theta = normalize_angle(p.theta + this.theta);

		return this;
	}

	public String toString()
	{
		return x + " " + y + " " + theta;
	}
	
	
	public static boolean isAngleInSpan(Double angleContact,Double angleMin,Double angleSpan){
		//Cas Basique en dehors bumper n'incluant pas le point pi
		if(!(angleMin>0 && angleMin*Posture.normalize_angle(angleMin+angleSpan)<0)){

			if(angleContact>=angleMin){
				if(angleContact<(angleMin+angleSpan)){
					return true;
				}}}

		//Cas ou l'on se trouve au dessus d'une discontinuité
		else{
			if((angleContact>=angleMin && angleContact<=Math.PI )||( angleContact<=Posture.normalize_angle(angleMin+angleSpan) && angleContact>=-Math.PI) ){

				return true;
			}}
		return false;
	}
	/**
	 * return l'angle remis dans [-PI;PI]
	 */
	public static double normalize_angle(double a)
	{
		while (a > Math.PI)
			a -= 2 * Math.PI;
		while (a < -Math.PI)
			a += 2 * Math.PI;
		return a;
	}  

	public static void main(String args[])
	{
		Posture p =  new Posture(0, 0, 0);
		for (int j = 0; j < 4; ++j)
		{
			for (int i = 0; i < 10; ++i)
			{
				System.out.println(p);
				p.move(5.34, 0, 34);
			}
			for (int i = 0; i < 10; ++i)
			{
				System.out.println(p);
				p.move(5, 5, 34);
			}
		}
	}
}
