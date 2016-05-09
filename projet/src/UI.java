import javax.swing.JPanel;

/**
 * @author Yanis
 * Class abstraite gerant les interface graphique
 */
public abstract class UI extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Met a jour l'affichage de l'interface homme/machine
	 */
	abstract void updateDisplay();
	
}
