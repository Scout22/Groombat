import java.awt.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ImageIcon background;
	JFrame ma_fenetre ;
	JPanel content;
	JLabel bg;
	JTextField sizeX;
	JTextField sizeY; 
	JTextField delta;
	JLabel tx;
	JLabel ty;
	JLabel tdel;
	public boolean init_done = false;
	private JButton start;
	private JButton done;
	private int length = 600;
	private int width = 600;
	private int x = 4;
	private int y = 4;
	private double del = 0.1;
	private String done_Text = "Done";
	private String error_Text = "Error !";
	private Boolean is_Error = false;
	
	
	/**
	 * Instancie le menu d'initialisation.
	 */
	public MenuUI() {
		//background =  new ImageIcon("bg_menu.jpg");
		
		content = new BackgroundUI();
	    
		//bg = new JLabel();
		//bg.setIcon(background);
		
		//content.add(bg);
		content.setLayout(null);
	
		setBackground(Color.white);
		setOpaque(true);
		
		ma_fenetre = new JFrame("Initialisation");
 
		this.start = new JButton("Start !");
		start.addActionListener(this);
		start.setSize(80,30);
		start.setLocation(250, 250);
		content.add(start);
		
		this.done = new JButton(done_Text);
		done.setSize(80,30);
		done.setLocation(250,450);
		done.addActionListener(this);
		
	
		ma_fenetre.setResizable(false);
		sizeX = new JTextField();
		sizeX.setColumns(5);
		sizeX.setSize(100, 25);
		sizeX.setLocation(100, 130);
		sizeX.setText("4");
		sizeX.addActionListener(this);
		sizeY = new JTextField();
		sizeY.setColumns(5);
		sizeY.setSize(100, 25);
		sizeY.setLocation(100, 260);
		sizeY.setText("4");
		sizeY.addActionListener(this);
		delta = new JTextField();
		delta.setColumns(5);
		delta.setSize(100, 25);
		delta.setLocation(100, 390);
		delta.setText("100");
		delta.addActionListener(this);
		
		tx = new JLabel("Largeur de la piece (m)");
		tx.setSize(250, 25);
		tx.setLocation(250, 130);
		ty = new JLabel("Longueur de la piece (m)");
		ty.setSize(250, 25);
		ty.setLocation(250, 260);
		tdel = new JLabel("Temps de rafraichissement (ms)");
		tdel.setSize(250, 25);
		tdel.setLocation(250, 390);
		
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
			 content.add(done);
			 content.add(delta);
			 content.add(tdel);
			 content.add(sizeX);
			 content.add(tx);
			 content.add(sizeY);
			 content.add(ty);
			 content.validate();
			 content.repaint();
		} else if(source == done && !is_Error){
			this.init_done = true;
			ma_fenetre.dispose();
		}
		else if(source == sizeX){
			try {
			      x = Integer.parseInt(sizeX.getText());
			      done.setText(done_Text);
			      is_Error = false;
			} catch (NumberFormatException ee) {
			      x=4;
			      done.setText(error_Text);
			      is_Error = true;
			}
		}
		else if(source == sizeY){
			try {
			      y = Integer.parseInt(sizeY.getText());
			      done.setText(done_Text);
			      is_Error = false;
			} catch (NumberFormatException ee) {
			      y=4;
			      done.setText(error_Text);
			      is_Error = true;
			}
		}
		else if(source == delta){
			try {
			      del = Double.parseDouble(delta.getText());
			      del = del/1000.0;
			      done.setText(done_Text);
			      is_Error = false;
			} catch (NumberFormatException ee) {
			      del=0.1;
			      done.setText(error_Text);
			      is_Error = true;
			}
		}
	}
	
	/**
	 * Met a jour l'affichage graphique du menu d'initialisation.
	 */
	void update_Menu() {
		content.repaint();
	}

	/**
	 * Renvoie la valeur de x, correspondant a la longueur de la piece simulee.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Fixe la valeur de x, correspondant a la longueur de la piece simulee.
	 * @param x valeur souhaitee.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Renvoie la valeur de y, correspondant a la largeur de la piece simulee.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Fixe la valeur de y, correspondant a la largeur de la piece simulee.
	 * @param y largeur souhaitee.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Renvoie la valeur de delta, correspondant au temps de rafraichissement en seconde.
	 */
	public double getDel() {
		return del;
	}

	/**
	 * Fixe la valeur de delta, correspondant au temps de rafraichissement en seconde.
	 * @param del valeur souhaitee.
	 */
	public void setDel(double del) {
		this.del = del;
	}
	
}
