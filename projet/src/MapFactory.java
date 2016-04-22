import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.events.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.event.MouseListener;
import javax.swing.JTextArea;

public class MapFactory extends JFrame implements MouseListener  {

	private JPanel contentPane;
	JTextArea textArea;
	private JPanel mapPane;
	private JCheckBox chckbxActiverModeEditier;
	private JRadioButton rdbtnAjouterObstacle;
	private JRadioButton rdbtnAjouterTache;
	private String mode="dis";
	private Map mapy;
	private boolean init_done=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapFactory frame = new MapFactory(new Simulator());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MapFactory(Simulator sim) {
		mapy=sim.getMap();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 670);
		setResizable(false);
		setTitle("Configurateur de Map");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		mapy=sim.getMap();
		
		//met en place les element graphique
		//mapPane = new GraphicalUI(sim);
		mapPane=new Panneau(mapy);
		//mapPane.setBackground(Color.BLUE);
		mapPane.setBounds(10, 10, 610, 610);
		((Panneau) mapPane).setScaleFactor(150);
		mapPane.addMouseListener(this);
		addMouseListener(this);
		contentPane.add(mapPane);


		JPanel panel = new JPanel();
		panel.setBounds(636, 13, 334, 557);
		contentPane.add(panel);
		panel.setLayout(null);

		rdbtnAjouterObstacle = new JRadioButton("Ajouter obstacle");
		rdbtnAjouterObstacle.setBounds(87, 85, 109, 23);
		rdbtnAjouterObstacle.setEnabled(false);
		panel.add(rdbtnAjouterObstacle);

		rdbtnAjouterTache = new JRadioButton("Ajouter tache");
		rdbtnAjouterTache.setBounds(87, 111, 109, 23);
		rdbtnAjouterTache.setEnabled(false);
		panel.add(rdbtnAjouterTache);

		//Definit le comportement des boutons
		rdbtnAjouterObstacle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAjouterObstacle.isSelected()){
					rdbtnAjouterTache.setSelected(false);
					mode="obs";
				}
			}
		});
		rdbtnAjouterTache.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAjouterTache.isSelected()){
					rdbtnAjouterObstacle.setSelected(false);
					mode="dirt";
				}
			}
		});


		chckbxActiverModeEditier = new JCheckBox("Activer mode editier");
		chckbxActiverModeEditier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxActiverModeEditier.isSelected()){
					rdbtnAjouterObstacle.setEnabled(true);
					rdbtnAjouterTache.setEnabled(true);
					rdbtnAjouterObstacle.doClick();
					mode="obs";
				}
				else {
					rdbtnAjouterObstacle.setEnabled(false);
					rdbtnAjouterTache.setEnabled(false);
					rdbtnAjouterObstacle.setSelected(false);
					mode="dis";
				}
			}
		});

		chckbxActiverModeEditier.setBounds(87, 49, 97, 23);
		panel.add(chckbxActiverModeEditier);

		JButton btnValiderCarte = new JButton("Valider Carte");
		btnValiderCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				init_done=true;
				MapFactory.this.dispose();
			
			}
		});
		btnValiderCarte.setBounds(107, 445, 127, 43);
		panel.add(btnValiderCarte);

		setVisible(true);
	}
	

	public boolean isInitDone(){
		return init_done;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent arg0) {
		
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent arg0) {
		if(arg0.getSource().equals(mapPane)){
			if(mode!="dis"){
				double x=arg0.getX()/(((Panneau) mapPane).getScaleFactor());
				double y=arg0.getY()/(((Panneau) mapPane).getScaleFactor());
				if(mode=="obs"){
					try{
						double size = Double.parseDouble(JOptionPane.showInputDialog(this,"Taille en mm de l'obstacle?", null))/1000;
						if(size<=0){
							throw new ArithmeticException("Negative value");
						}
						mapy.addObstacle(new Trashcan(x,y,size));}
					catch(Exception e){
						JOptionPane.showMessageDialog(MapFactory.this,"La taille de l'obstacle doit etre un nombre positif");
					}}
				else if(mode=="dirt"){
					try{
						double size = Double.parseDouble(JOptionPane.showInputDialog(this,"Taille en mm de la tache?", null))/1000;
						if(size<=0){
							throw new ArithmeticException("Negative value");
						}
						mapy.addDirtSpot(new DirtSpot(x,y,size));}
					catch(Exception e){
						JOptionPane.showMessageDialog(MapFactory.this,"La taille de l'obstacle doit etre un nombre positif");
					}
				}}

		}
		mapPane.repaint();
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}