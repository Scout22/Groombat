import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.*;

// Pour les details sur les fonctions de dessin, voir :
// http://java.sun.com/j2se/1.5.0/docs/api/java/awt/Graphics.html
// voir aussi : http://java.sun.com/docs/books/tutorial/2d/index.html
class Move extends JPanel 
{
  private int x;
  private int prev_x;
  private int y;
  private int prev_y;
  private Simulator sim;
  
  public Move(Simulator sim)
  {
	this.sim = sim;
    x = 0;
    y = 0;
    setBackground(Color.white);
    setOpaque(true);
  }

  public void moveRobot(int x, int y)
  {
    this.x = x;
    this.y = y;
  }

  public void paint(Graphics g) {
	  
	//g2.setColor(Color.red); // exemple de base
	//g2.fillOval(x, y, 40, 40); // a supprimer
	  
      super.paint(g);
    
    // on recupere la zone de dessin
    Graphics2D g2 = (Graphics2D) g;
    
    // on efface tout
    g2.setColor(Color.white);
    g2.fillRect(0, 0 , this.getHeight(), this.getWidth());
    
    // on dessine la carte
    for(int i=0; i<this.sim.getObstacles().size(); i++){
    	paintObstacle(g2, this.sim.getObstacles().get(i));
    }
    for(int i=0; i<this.sim.getDirtSpots().size(); i++){
    	paintDirtSpot(g2, this.sim.getDirtSpots().get(i)); // j'en suis la
    }
    
    // on dessine les robots
    for(int i=0; i<this.sim.getRobots().size(); i++){
    	paintRobot(g2,this.sim.getRobots().get(i));
    }
    
    // on rend la main
    g2.dispose();
  }

  private void paintRobot(Graphics2D g, Robot robot) {
	  g.setColor(Color.blue);
	  Shape circle = new Ellipse2D.Double(robot.getX(),robot.getY(), robot.getRadius(),robot.getRadius());
	  g.draw(circle);
	  Shape line = new Line2D.Double(robot.getX(),robot.getY(), robot.getX()+robot.getRadius()*Math.cos(Math.PI*robot.getTheta()/180), robot.getY()+robot.getRadius()*Math.sin(Math.PI*robot.getTheta()/180));
	  g.draw(line);
  }

  public void paintDirtSpot(Graphics2D g, DirtSpot dirt){
	  g.setColor(Color.pink);
	  Shape circle = new Ellipse2D.Double(dirt.getX(),dirt.getY(), dirt.getRadius(),dirt.getRadius());
	  g.draw(circle);
  }
  
  
  public void paintObstacle(Graphics2D g, Obstacle obs){
	  g.setColor(Color.black);
	  if(obs instanceof Trashcan){
		  Trashcan t = (Trashcan) obs;
		  Shape circle = new Ellipse2D.Double(t.getX(),t.getY(), t.getRayon(),t.getRayon());
		  g.draw(circle);
	  }
	  if(obs instanceof Wall){
		  Wall w = (Wall) obs;
		  Shape line = new Line2D.Double(w.getX1(), w.getY1(), w.getX2(), w.getY2());
		  g.draw(line);
	  }
  }
  
  
  /*public static void main (String [] args) // a effacer a la fin
  {
    JFrame ma_fenetre = new JFrame("Cercle rouge");
    Move m = new Move();
    m.setPreferredSize(new Dimension(400, 400));
    ma_fenetre.setContentPane(m);
    ma_fenetre.pack();
    ma_fenetre.show();
    int x = 0;
    boolean sens = true;

    while (true)
      {
	m.moveRobot(x, 0);
	if (sens)
	  x += 1;
	else
	  x -= 1;
	if (x == 0 || x == 100)
	  sens = !sens;
	// attend 0.01 sec
	try  { Thread.sleep(10); }
	catch (Exception e) {}
	// redessine (appelle entre autres paint())
	m.repaint();
      }
  }*/
}
