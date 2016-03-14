import java.awt.geom.*;

public abstract class Collision {

	private static double precision=0;

	public static boolean CircleLine(Point2D.Double c,double r,Line2D.Double line){
		if(line.ptSegDist(c)+precision<=r){
			return true;
		}
		else{
			return false;
		}

	}

	public static boolean CircleCircle(Point2D.Double c1,double r1,Point2D.Double c2,double r2){

		if(c1.distance(c2)+precision<=r1+r2){
			return true;}
		else{
			return false;
		}

	}

	public static boolean ArcCircle(Point2D.Double ac,double ar,double angleMin,double angleSpan,Point2D.Double c,double r){
		if(CircleCircle(c,r,ac,ar)){
			//Calcule de l'angle entre le vecteur AB et l'axe X 
			double angleContact=Math.atan2(c.getY()-ac.getY(),+c.getX()-ac.getX());


			//angleContact=Posture.normalize_angle(angleContact);
			angleMin=Posture.normalize_angle(angleMin);

			return Posture.isAngleInSpan(angleContact, angleMin, angleSpan);
		}
		return false;
	}
	public static boolean ArcLine(Point2D.Double ac,double ar,double angleMin,double angleSpan,Line2D.Double line){
		if(line.ptSegDist(ac)+2*precision<=ar){

			Line2D.Double ln;
			if(isLeft(line,ac)){
				ln=normalise(LeftNormal(line),ar); //Vecteur normale a la ligne
			}
			else{
				ln=normalise(RightNormal(line),ar);
			}
			Line2D.Double lnc=new Line2D.Double(ac.getX(),ac.getY(),ac.getX()+ln.getX2(),ac.getY()+ln.getY2());//Normale a la ligne avec centre du cercle comme extremité


			double angleContact=Math.atan2(-(lnc.getY1()-lnc.getY2()),lnc.getX1()-lnc.getX2());

			return Posture.isAngleInSpan(angleContact, angleMin, angleSpan);

		}
		return false;}



	public static Line2D.Double LeftNormal(Line2D.Double line){

		return new Line2D.Double(0,0,line.y1-line.y2,line.x1-line.x2);
	}
	public static Line2D.Double RightNormal(Line2D.Double line){

		return new Line2D.Double(0,0,line.y2-line.y1,line.x2-line.x1);
	}

	public static double dotProduct(Line2D.Double line1,Line2D.Double line2)
	{
		return (line1.getP2().getX()-line1.getP1().getX())*(line2.getP2().getX()-line2.getP1().getX())+(line1.getP2().getY()-line1.getP1().getY())*(line2.getP2().getY()-line2.getP1().getY());
	}

	public static Line2D.Double normalise(Line2D.Double line1,double k)
	{

		double norme=Math.sqrt(Math.pow((line1.getP2().getX()-line1.getP1().getX()), 2)+Math.pow(((line1.getP2().getY()-line1.getP1().getY())), 2));
		return new Line2D.Double(0, 0, k*(line1.getP2().getX()-line1.getP1().getX())/norme, k*((line1.getP2().getY()-line1.getP1().getY()))/norme);
	}

	public static double Projection(Line2D.Double line1,Line2D.Double line2){
		return dotProduct(line1,normalise(line2,1));
	}
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
