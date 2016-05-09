import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Oscar
 * Classe permettant de reccuperer les paramètres de la simulation
 */
public class MenuUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JFrame ma_fenetre ;
	private JPanel content;
	private JLabel bg;
	private JTextField sizeX;
	private JTextField sizeY; 
	private JTextField nbRobot; 
	private JTextField delta;
	private JLabel tx;
	private JLabel tnb;
	private JLabel ty;
	private JLabel tdel;
	public Simulator sim;
	private boolean init_done = false;
	private JButton start;
	private JButton demoMode;
	private JButton done;
	private int length = 600;
	private int width = 600;
	private int x = 4;
	private int y = 4;
	private int nb= 1;
	private double del = 0.1;
	private String done_Text = "Done";
	private String error_Text = "Error !";
	private Boolean is_Error = false;
	private Boolean demoModeOn=false;


	/**
	 * Instancie le menu d'initialisation.
	 */
	public MenuUI() {
		//Affiche le fond d'ecran et parametre le Layout
		content = new BackgroundUI();
		content.setLayout(null);

		setBackground(Color.white);
		setOpaque(true);

		ma_fenetre = new JFrame("Initialisation");

		//Instancie et affiche les boutons du premier menu
		this.start = new JButton("Start !");
		start.addActionListener(this);
		start.setSize(150,30);
		start.setLocation(250, 250);
		content.add(start);
		
		//Instancie les elements graphique du deuxieme menu
		this.demoMode = new JButton("Launch Demo");
		demoMode.addActionListener(this);
		demoMode.setSize(150,30);
		demoMode.setLocation(250, 350);
		content.add(demoMode);

		this.done = new JButton(done_Text);
		done.setSize(80,30);
		done.setLocation(250,450);
		done.addActionListener(this);


		ma_fenetre.setResizable(false);
		sizeX = new JTextField();
		sizeX.setColumns(5);
		sizeX.setSize(100, 25);
		sizeX.setLocation(100, 100);
		sizeX.setText("4");
		sizeX.addActionListener(this);

		sizeY = new JTextField();
		sizeY.setColumns(5);
		sizeY.setSize(100, 25);
		sizeY.setLocation(100, 200);
		sizeY.setText("4");
		sizeY.addActionListener(this);

		delta = new JTextField();
		delta.setColumns(5);
		delta.setSize(100, 25);
		delta.setLocation(100, 300);
		delta.setText("10");
		delta.addActionListener(this);

		nbRobot = new JTextField();
		nbRobot.setColumns(5);
		nbRobot.setSize(100, 25);
		nbRobot.setLocation(100, 400);
		nbRobot.setText("1");
		nbRobot.addActionListener(this);


		tx = new JLabel("Largeur de la piece (m)");
		tx.setSize(250, 25);
		tx.setLocation(250, 100);
		ty = new JLabel("Longueur de la piece (m)");
		ty.setSize(250, 25);
		ty.setLocation(250, 200);
		tdel = new JLabel("Temps de rafraichissement (ms)");
		tdel.setSize(250, 25);
		tdel.setLocation(250, 300);
		
		tnb = new JLabel("Nombre de robot");
		tnb.setSize(250, 25);
		tnb.setLocation(250, 400);

		setPreferredSize(new Dimension(this.length,this.width));
		ma_fenetre.setContentPane(this);
		ma_fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ma_fenetre.pack();
		ma_fenetre.setContentPane(content);
		ma_fenetre.setLocationRelativeTo(null);
		ma_fenetre.setVisible(true);


	}

	/**
	 * Fonction d'affichage du menu d'initialisation.
	 * @param g composante graphique
	 */
	public void paint(Graphics g) {
		super.paint(g);
		super.paintComponents(g);

		// on recupere la zone de dessin
		Graphics2D g2 = (Graphics2D) g;

		// on efface tout
		g2.setColor(Color.white);
		g2.fillRect(0, 0 , this.length, this.width);

		g2.dispose();
	}





	/**
	 * Fonction d'interface permettant de reagir a une interaction utilisateur.
	 * @param e object ayant provoque l'appel de la fonction.
	 */
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if(source == start){
			content.remove(start);
			content.remove(demoMode);
			content.add(done);
			content.add(delta);
			content.add(tdel);
			content.add(sizeX);
			content.add(tx);
			content.add(sizeY);
			content.add(ty);
			//content.add(tnb);
			//content.add(nbRobot);
			content.validate();
			content.repaint();
		} else if(source == done && !is_Error){
			init_done=true;
			ArrayList<Robot> robs = new ArrayList<Robot>();
			ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
			ArrayList<DirtSpot> dirts = new ArrayList<DirtSpot>();
			sim = new Simulator(obs,dirts,robs,del,x,y);
			ma_fenetre.dispose();
		}
		else if(source==demoMode){
			demoModeOn=true;
			init_done=true;
			this.sim=new Simulator("demo");
			ma_fenetre.dispose();
		}
		checkValue();
	}
	
	/**
	 * Fonction qui verifie si les valeurs saisie sont correctes, et met la variable is_Error à true si c'est le cas.
	 */
	private void checkValue(){
		is_Error=false;
		try {
			x = Integer.parseInt(sizeX.getText());
			is_Error = is_Error || false;
		} catch (NumberFormatException ee) {
			x=4;
			is_Error = true;
		}

		try {
			del = Double.parseDouble(delta.getText());
			del = del/1000.0;
			is_Error = is_Error || false;
		} catch (NumberFormatException ee) {
			del=0.1;
			is_Error = true;
		}
		try {
			nb = Integer.parseInt(nbRobot.getText());
			is_Error = is_Error || false;
		} catch (NumberFormatException ee) {
			nb=1;
			is_Error = true;
		}
		try {
			y = Integer.parseInt(sizeY.getText());
			is_Error = is_Error || false;
		} catch (NumberFormatException ee) {
			y=4;
			is_Error = true;
		}
		if(is_Error){
			done.setText(error_Text);
		}
		else{
			done.setText(done_Text);
		}
	}
	
	/**
	 * @return Retourne le simulator generer par le Menu
	 */
	public Simulator getSimulator(){
		return this.sim;
		
	}

	/**
	 * Met a jour l'affichage graphique du menu d'initialisation.
	 */
	void update_Menu() {
		content.repaint();
	}

	/**
	 * @return Renvoie le nombre de robot à crée.
	 */
	public int getNbRobot() {
		return nb;
	}

	/**
	 * @return Boolean a true si le mode demo est selectioné et false sinon.
	 */
	public boolean isDemoMode() {
		return demoModeOn;
	}

	/**
	 * @return Boolean a true si l'initialisation est termiée et false sinon.
	 */
	public boolean isInit_done() {
		return init_done;
	}

}
