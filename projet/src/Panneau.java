import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * @author Yanis
 * Class derivant de JPanel permettant de crée des zones d'affichage comportant des robots/des obstacles/
 *
 */
@SuppressWarnings("serial")
public class Panneau extends JPanel {
	public ArrayList<Robot> robots=new ArrayList<Robot>();
	public Map map=new Map();
	private double scaleFactor=150;
	private Image img0;
	/**
	 * Construction d'un affichage a partir d'un robot
	 * @param rob robot a afficher
	 */
	public  Panneau(Robot rob)
	{
		img0=Toolkit.getDefaultToolkit().getImage("a.png");
		robots.add(rob);

	}

	/**
	 * Contstruction d'un affichage a partir d'une carte
	 * @param map carte a afficher
	 */
	public  Panneau(Map map)
	{
		img0=Toolkit.getDefaultToolkit().getImage("a.png");
		this.map=map;

	}
	/**
	 * Construction d'un affichage a partir d'un simulateur
	 * @param sim simulateur a afficher
	 */
	public Panneau(Simulator sim) {
		img0=Toolkit.getDefaultToolkit().getImage("a.png");
		robots=sim.getRobots();
		map=sim.getMap();
	}

	/** 
	 * Fonction permettant de peindre les elements graphique
	 * @param g zone graphique
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		//super.paint(g);

		// on recupere la zone de dessin
		Graphics2D g2 = (Graphics2D) g;

		// on efface tout
		g2.setColor(Color.white);
		g2.fillRect(0, 0 , this.getHeight(), this.getWidth());

		// on dessine la carte

		for(Obstacle ob:map.getObstacles()){
			paintObstacle(g2, ob);
		}
		for(DirtSpot ds:map.getDirtSpots()){
			paintDirtSpot(g2, ds); 
		}

		// on dessine les robots
		for(Robot rob:robots){
			paintRobot(g2,rob);
		}

		// on rend la main
		g2.dispose();

	}


	/**
	 * Fonction permettant l'affichage d'un robot
	 * @param g zone graphique
	 * @param robot robot a peindre
	 */
	private void paintRobot(Graphics2D g, Robot robot) {

		double x=scaleFactor*robot.getX();
		double y=scaleFactor*robot.getY();
		double radius=scaleFactor*robot.getRadius();
		double theta=robot.getTheta();


		AffineTransform old = g.getTransform();
		AffineTransform tr2= new AffineTransform();
		

		tr2.rotate(theta, x, y);
		g.transform(tr2);
		g.drawImage(img0,(int)(x-radius),(int)(y-radius),(int)(2*radius),(int)(2*radius),null);
		
		
		for(Sensor sens:robot.getSensors()){
			if(sens instanceof Bumper){
				Bumper bump=(Bumper)sens;
				g.setStroke(new BasicStroke(5));
				if(bump.isTriggered()){
					g.setColor(Color.RED);
				}
				else{
					g.setColor(Color.CYAN);
				}
				g.drawArc((int)(x-radius),(int)(y-radius),(int)(2*radius),(int)(2*radius),-(int)Math.toDegrees(bump.angleInit),-(int)Math.toDegrees(bump.span));
			}
			else if(sens instanceof Ultrasound){
				g.setStroke(new BasicStroke(1));
				Ultrasound us=(Ultrasound)sens;
				double dist=scaleFactor*us.getDist()+radius;
				if(us.isTriggered()){
					g.setColor(Color.GREEN);
				}
				else{
					g.setColor(new Color(1, 0, 0, (float)0.7));
				}
				g.fillArc((int)(x-dist),(int)(y-dist),(int)(2*dist),(int)(2*dist),-(int)Math.toDegrees(us.angleInit),-(int)Math.toDegrees(us.span));
			}
			else if(sens instanceof DirtSensor){
				g.setStroke(new BasicStroke(1));
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

		g.setTransform(old);
	}

	/**
	 * Fonction permettant l'affichage d'une tache
	 * @param g zone graphique
	 * @param dirt tache a peindre
	 */
	private void paintDirtSpot(Graphics2D g, DirtSpot dirt){

		double x=scaleFactor*dirt.getX();
		double y=scaleFactor*dirt.getY();
		double radius=scaleFactor*dirt.getRadius();
		g.setColor(Color.pink);
		g.setStroke(new BasicStroke(1));
		Shape circle = new Ellipse2D.Double(x-radius,y-radius, 2*radius,2*radius);
		g.draw(circle);
	}

	/**
	 * Fonction permettant l'affichage d'un obstacle
	 * @param g zone graphique
	 * @param obs obstacle a peindre
	 */
	private void paintObstacle(Graphics2D g, Obstacle obs){

		g.setColor(Color.black);
		
		if(obs instanceof Trashcan){
			g.setStroke(new BasicStroke(1));
			Trashcan t = (Trashcan) obs;
			double x=scaleFactor*t.getX();
			double y=scaleFactor*t.getY();
			double radius=scaleFactor*t.getRadius();
			Shape circle = new Ellipse2D.Double(x-radius,y-radius, 2*radius,2*radius);
			g.draw(circle);
		}
		if(obs instanceof Wall){
			Wall w = (Wall) obs;
			g.setStroke(new BasicStroke(5));
			double x1=scaleFactor*w.getX1();
			double y1=scaleFactor*w.getY1();
			double x2=scaleFactor*w.getX2();
			double y2=scaleFactor*w.getY2();
			Shape line = new Line2D.Double(x1, y1,x2, y2);
			g.draw(line);
		}
	}

	/**
	 * @return Le scaleFactor: Ration nombre de pixel/metre
	 */
	public double getScaleFactor(){
		return scaleFactor;
	}

	/**
	 * Set le ScaleFactor: Ration entre le nombre de pixel/metre reel 
	 * @param sf nouveau coefficient
	 */
	public void setScaleFactor(double sf){
		scaleFactor=sf;
	}

	/**
	 * Methode permettant de maximiser l'affichage a l'ecran, pour se faire soit l'on utilise les murs exterieur comme reference soit le robot de plus grande taille si il n'y a pas de mur
	 */
	public void autoScale(){
		double screenSize=Math.max(getWidth(),getHeight());
		double max_dim=0;

		for(Robot rob:robots){
			max_dim=Math.max(max_dim,rob.getRadius()*2);
			if(map.getObstacles().size()==0){
				rob.setPosture(new Posture(rob.getRadius()*1.05,rob.getRadius()*1.05,rob.getTheta()));
			}
		}

		for(Obstacle obs:map.getObstacles()){
			if(obs instanceof Wall){
				Wall w = (Wall) obs;
				max_dim=Math.max(max_dim,Math.max(w.getX1(), Math.max(w.getY1(), Math.max(w.getY2(), w.getX2()))));
			}
		}
		if(max_dim!=0){
			scaleFactor=(screenSize/max_dim)*0.9;
		}
	}

}