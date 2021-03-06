import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * @author Oscar
 * Classe permettant de creer une image de fond pour une fenetre
 */
@SuppressWarnings("serial")
public class BackgroundUI extends JPanel {

	private Image background;
	
	/**
	 * Instancie le contenu du menu avec une image de fond.
	 */
	public BackgroundUI() {
		try {
			background = ImageIO.read(new File("bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Affiche l'image de fond du menu.
	 */
	public void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, null);
	}
	
}
