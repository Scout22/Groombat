import java.awt.*;
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
  public Move()
  {
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
    // on effac une zone un peu plus grande que le cercle
    g2.setColor(Color.white);
    g2.fillRect(prev_x, prev_y , 40, 40);
    // on dessin un disque rouge
    g2.setColor(Color.red);
    g2.fillOval(x, y, 40, 40);
    // on rend la main
    g2.dispose();
    // on retient x,y pour pouvoir effacer au prochain appel
    prev_x = x;
    prev_y = y;
  }

  public static void main (String [] args) 
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
  }
}
