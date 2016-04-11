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
	private int length = 700;
	private int width = 700;
	private int x = 4;
	private int y = 4;
	private double del = 0.1;
	
	public MenuUI() {
		background =  new ImageIcon("bg_menu.jpg");
		
		content = new JPanel();
	    
		bg = new JLabel();
		bg.setIcon(background);
		
		content.add(bg);
		content.setLayout(new FlowLayout());
	
		setBackground(Color.white);
		setOpaque(true);
		
		ma_fenetre = new JFrame("Initialisation");
		
		ma_fenetre.setLayout(new FlowLayout());
 
		this.start = new JButton("Start !");
		start.addActionListener(this);
		content.add(start);
		this.done = new JButton("Done");
		done.addActionListener(this);
		
	
		
		sizeX = new JTextField();
		sizeX.setColumns(5);
		sizeX.addActionListener(this);
		sizeY = new JTextField();
		sizeY.setColumns(5);
		sizeY.addActionListener(this);
		delta = new JTextField();
		delta.setColumns(5);
		delta.addActionListener(this);
		
		tx = new JLabel("largeur de la piece (m)");
		ty = new JLabel("longueur de la piece (m)");
		tdel = new JLabel("temps de rafraichissement (s)");
		
	    setPreferredSize(new Dimension(this.length,this.width));
	    ma_fenetre.setContentPane(this);
	    ma_fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    ma_fenetre.pack();
	    ma_fenetre.setContentPane(content);

	    ma_fenetre.setVisible(true);
	 

	}

	/**
	 * @param args
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
		} else if(source == done){
			System.out.println("Vous avez cliqué sur <done>.");
			this.init_done = true;
			ma_fenetre.dispose();
		}
		else if(source == sizeX){
			try {
			      x = Integer.parseInt(sizeX.getText());
			} catch (NumberFormatException ee) {
			      x=4;
			      System.out.println("Wrong entry, setting X value to 4.");
			}
			System.out.println("x = " + x);
		}
		else if(source == sizeY){
			try {
			      y = Integer.parseInt(sizeY.getText());
			} catch (NumberFormatException ee) {
			      y=4;
			      System.out.println("Wrong entry, setting Y value to 4.");
			}
			System.out.println("y = " + y);
		}
		else if(source == delta){
			try {
			      del = Double.parseDouble(delta.getText());
			} catch (NumberFormatException ee) {
			      del=0.1;
			      System.out.println("Wrong entry, setting delta value to 0.1.");
			}
			System.out.println("delta = " + del);
		}
	}
	
	void update_Menu() {
		repaint();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getDel() {
		return del;
	}

	public void setDel(double del) {
		this.del = del;
	}
	
}
