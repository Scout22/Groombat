import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.event.MouseListener;


/**
 * @author Yanis
 * Class permettant de positionner un robot au sein d'une simulation grace a une interface graphique
 */
@SuppressWarnings("serial")
public class EnvFactory extends JFrame implements MouseListener  {

	private JPanel contentPane;
	private JPanel mapPane;
	private JPanel robPreviewPane;
	private Simulator sim;
	private Robot rob;
	private boolean xyset=false;
	private double x;
	private double y;
	private boolean init_done=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnvFactory frame = new EnvFactory(new Simulator(),new Robot(0,0,9.5,2,Math.toRadians(0),0.5,2,0.2));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * L'instanciation de cette classe permet de lancer une fenêtre permettant de définir la position d'un robot 
	 * au seins d'une carte d'envirenement
	 * @param sim simulateur contenant la carte
	 * @param rob robot à positionner
	 */
	public EnvFactory(Simulator sim,Robot rob) {
		//Mise en place des elements
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 670);
		setResizable(false);
		setTitle("Configurateur de Map");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.rob=rob;
		this.sim=sim;
		
		//Affichage de la carte
		mapPane=new Panneau(sim);
		mapPane.setBounds(10, 10, 610, 610);
		((Panneau) mapPane).setScaleFactor(150);
		mapPane.addMouseListener(this);
		addMouseListener(this);
		contentPane.add(mapPane);

		//Apercu du robot
		robPreviewPane = new Panneau(rob);
		robPreviewPane.setBounds(630, 10 , 350, 350);
		((Panneau) robPreviewPane).autoScale();
		contentPane.add(robPreviewPane);

		//Mise en place des bouton
		JPanel panel = new JPanel();
		panel.setBounds(636, 13, 334, 557);
		contentPane.add(panel);
		panel.setLayout(null);
		JButton btnValiderCarte = new JButton("Valider position");
		btnValiderCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(xyset){
					init_done=true;
					EnvFactory.this.sim.removeLastRobot();
					EnvFactory.this.rob.setPosture(new Posture(x,y,EnvFactory.this.rob.getTheta()));
					EnvFactory.this.sim.addRobot(EnvFactory.this.rob);
				
				EnvFactory.this.dispose();}

			}
		});
		btnValiderCarte.setBounds(107, 445, 127, 43);
		panel.add(btnValiderCarte);

		//Affichage de la fenetre
		setVisible(true);
	}


	/**
	 * Vérifie si l'initialisation est terminer
	 * @return Vrai si l'initialisation est terminé
	 */
	public boolean isInitDone(){
		return init_done;
	}



	
	//Action lors de la pression de la souris
	@Override
	public void mousePressed(java.awt.event.MouseEvent arg0) {
		//On reccupere la position du clic
		x=arg0.getX()/(((Panneau) mapPane).getScaleFactor());
		y=arg0.getY()/(((Panneau) mapPane).getScaleFactor());
		
		//On positionne un robot a cette position
		Robot tempRob=rob.clone();
		tempRob.setPosture(new Posture(x,y,rob.getPosture().getTheta()));
		
		//On verifie que la position est valide
		if(sim.isValidPos(tempRob)){
			//On supprime eventuellement le robot correspondat à la precedente position
			if(xyset){
				sim.removeLastRobot();
			}
			xyset=true;
			//On ajoute le robot au simulateur
			sim.addRobot(tempRob);
		}
		//Sinon on affiche un message d'erreur
		else{
			JOptionPane.showMessageDialog(EnvFactory.this,"La robot ne peut pas chevaucher un obstacle ou un autre robot");
		}

		robPreviewPane.repaint();
		mapPane.repaint();
	}
	
	//Fonction issue de l'interface Mouse non utiliser
	@Override
	public void mouseClicked(java.awt.event.MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent arg0) {
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent arg0) {
	}
}