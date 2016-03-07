import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

//Les murs ne peuvent etre que verticale ou horizontale.
public class Wall extends Obstacle {
	private Line2D.Double line;
	
	Wall(double x1,double y1,double x2,double y2){
		line=new Line2D.Double(x1,y1,x2,y2);
		type="Wall";
	}
	
	public double getX1(){
		return line.getX1();
	}
	public double getX2(){
		return line.getX2();
	}
	public double getY1(){
		return line.getY1();
	}
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
