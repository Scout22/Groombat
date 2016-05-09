import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * @author Yanis
 *	Class permettant l'affichage de la simulation
 */
@SuppressWarnings("serial")
class GraphicalUIP extends UI 
{
	private JFrame ma_fenetre ;
	private Panneau panDisplay;
	
	/**
	 * L'instanciation de cette class provoque l'ouverture d'une fentre graphique et affiche la simulation
	 * @param sim Simulation a afficher
	 */
	public GraphicalUIP(Simulator sim)
	{
		panDisplay=new Panneau(sim);
		ma_fenetre = new JFrame("Groombat");
	    ma_fenetre.setContentPane(panDisplay);
	    ma_fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    ma_fenetre.setResizable(false);
	    ma_fenetre.pack();
	    ma_fenetre.setVisible(true);
	    ma_fenetre.setBounds(0, 0, 1020, 1020);
		ma_fenetre.setResizable(false);
		panDisplay.setPreferredSize(new Dimension((int)(1000),(int)(1000)));
		panDisplay.setBounds(10, 10, 1000, 1000);
		autoScale();
		
	}



	public void paint(Graphics g) {
		super.paint(g);
		panDisplay.paint(g);
	}
	

	@Override
	void updateDisplay() {
		panDisplay.repaint();
	}

	/**
	 * Change l'echelle de l'affichage
	 */
	public void autoScale(){
		panDisplay.autoScale();
	}

	


}
