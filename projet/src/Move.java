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
    //g2.setColor(Color.red);
    //g2.fillOval(x, y, 40, 40);
    // on dessine les robots
    // on rend la main
    g2.dispose();
  }
  public void paintDirtSpot(Graphics2D g, DirtSpot dirt){
	  g.setColor(Color.pink);
	  
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
