import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.*;

// Pour les details sur les fonctions de dessin, voir :
// http://java.sun.com/j2se/1.5.0/docs/api/java/awt/Graphics.html
// voir aussi : http://java.sun.com/docs/books/tutorial/2d/index.html
class Move extends JPanel 
{
	private Simulator sim;
	private double scaleFactor;
	private Image img0;

	public Move(Simulator sim, double sf)
	{
		img0 = Toolkit.getDefaultToolkit().getImage("a.png");
		scaleFactor=sf;
		this.sim = sim;
		setBackground(Color.white);
		setOpaque(true);
	}



	public void paint(Graphics g) {



		super.paint(g);

		// on recupere la zone de dessin
		Graphics2D g2 = (Graphics2D) g;

		// on efface tout
		g2.setColor(Color.white);
		g2.fillRect(0, 0 , this.getHeight(), this.getWidth());

		// on dessine la carte
		
		for(Obstacle ob:sim.getObstacles()){
			paintObstacle(g2, ob);
		}
		for(DirtSpot ds:sim.getDirtSpots()){
			paintDirtSpot(g2, ds); 
		}

		// on dessine les robots
		for(Robot rob:sim.getRobots()){
			paintRobot(g2,rob);
		}

		// on rend la main
		g2.dispose();
	}

	private void paintRobot(Graphics2D g, Robot robot) {


		double x=scaleFactor*robot.getX();
		double y=scaleFactor*robot.getY();
		double radius=scaleFactor*robot.getRadius();
		double theta=robot.getTheta();


		AffineTransform old = g.getTransform();
		AffineTransform tr2= new AffineTransform();
		

		tr2.rotate(-theta, x, y);
		g.transform(tr2);
		g.drawImage(img0,(int)(x-radius),(int)(y-radius),(int)(2*radius),(int)(2*radius),null);
		g.setColor(Color.RED);
		g.drawOval((int)(x-radius),(int)(y-radius),(int)(2*radius),(int)(2*radius));
		g.drawLine((int)(x),(int)(y), (int)(x+radius),(int)(y));
		g.setStroke(new BasicStroke(5));
		for(Sensor sens:robot.getSensors()){
			if(sens instanceof Bumper){
				Bumper bump=(Bumper)sens;
				if(bump.isTriggered()){
					g.setColor(Color.RED);
				}
				else{
					g.setColor(Color.CYAN);
				}
				g.drawArc((int)(x-radius),(int)(y-radius),(int)(2*radius),(int)(2*radius),-(int)Math.toDegrees(bump.angleInit),-(int)Math.toDegrees(bump.span));
			}
			else if(sens instanceof DirtSensor){
				DirtSensor ds=(DirtSensor)sens;
				if(ds.isTriggered()){
					g.setColor(Color.orange);
				}
				else{
					g.setColor(Color.yellow);
				}
				g.fillOval((int)(x+scaleFactor*(-ds.getSensorRadius()+ds.getRelativePos().getX())),(int)(y+scaleFactor*(-ds.getSensorRadius()+ds.getRelativePos().getY())),(int)(2*scaleFactor*ds.getSensorRadius()),(int)(2*scaleFactor*ds.getSensorRadius()));
				
			}
			
		}


		//g.draw(circle);
		

		g.setTransform(old);
	}

	public void paintDirtSpot(Graphics2D g, DirtSpot dirt){

		double x=scaleFactor*dirt.getX();
		double y=scaleFactor*dirt.getY();
		double radius=scaleFactor*dirt.getRadius();
		g.setColor(Color.pink);
		Shape circle = new Ellipse2D.Double(x-radius,y-radius, 2*radius,2*radius);
		g.draw(circle);
	}

	public void paintObstacle(Graphics2D g, Obstacle obs){

		g.setColor(Color.black);
		if(obs instanceof Trashcan){
			Trashcan t = (Trashcan) obs;
			double x=scaleFactor*t.getX();
			double y=scaleFactor*t.getY();
			double radius=scaleFactor*t.getRadius();
			Shape circle = new Ellipse2D.Double(x-radius,y-radius, 2*radius,2*radius);
			g.draw(circle);
		}
		if(obs instanceof Wall){
			Wall w = (Wall) obs;
			double x1=scaleFactor*w.getX1();
			double y1=scaleFactor*w.getY1();
			double x2=scaleFactor*w.getX2();
			double y2=scaleFactor*w.getY2();
			Shape line = new Line2D.Double(x1, y1,x2, y2);
			g.draw(line);
		}
	}


}
