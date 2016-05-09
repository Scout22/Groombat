
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * @author Yanis
 * Class permettant de cree un robot avec une interface graphique
 */
@SuppressWarnings("serial")
public class RobotFactory extends JFrame {

	private JPanel contentPane;
	private JPanel robotPane;
	private JTextField textField_WheelDist;
	private JTextField textField_RobotRadius;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_Capteur;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_IA;
	private Robot rob;
	private boolean init_done=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RobotFactory frame = new RobotFactory();
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
	public RobotFactory() {
		rob=new Robot(0,0,2,2,0,0.4,1,0.2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 670);
		setResizable(false);
		setTitle("Configurateur de Groombat");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		robotPane = new Panneau(rob);
		robotPane.setBounds(12, 12, 600, 600);
		contentPane.add(robotPane);


		JPanel panel = new JPanel();
		panel.setBounds(636, 13, 334, 557);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblEspacementDesRoues = new JLabel("Espacement des roues (en mm)");
		lblEspacementDesRoues.setBounds(12, 9, 183, 16);
		panel.add(lblEspacementDesRoues);


		comboBox_Capteur = new JComboBox();
		comboBox_Capteur.setBounds(28, 324, 274, 53);
		panel.add(comboBox_Capteur);
		comboBox_Capteur.addItem("Capteur de poussiere");
		comboBox_Capteur.addItem("Bumper");
		comboBox_Capteur.addItem("Telemetre laser");
		comboBox_Capteur.addItem("Capteur ultrason");

		JButton btnAddSensor = new JButton("Ajouter capteur");
		btnAddSensor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PopUpSensor(comboBox_Capteur.getSelectedIndex());
				robotPane.repaint();


			}
		});
		btnAddSensor.setBounds(28, 390, 274, 53);
		panel.add(btnAddSensor);

		textField_WheelDist = new JTextField();
		textField_WheelDist.setBounds(247, 6, 55, 22);
		textField_WheelDist.setText(Double.toString(1000*rob.getDistWheel()));
		panel.add(textField_WheelDist);
		textField_WheelDist.setColumns(10);

		textField_RobotRadius = new JTextField();
		textField_RobotRadius.setBounds(247, 65, 55, 22);
		textField_RobotRadius.setText(Double.toString(1000*rob.getRadius()));
		panel.add(textField_RobotRadius);
		textField_RobotRadius.setColumns(10);

		JButton btnValidateSettings = new JButton("Valider parametre");
		btnValidateSettings.setBounds(28, 116, 274, 53);
		panel.add(btnValidateSettings);
		btnValidateSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double radius=rob.getRadius();
				double dist_w=rob.getDistWheel();

				try {
					radius= Double.parseDouble(textField_RobotRadius.getText());
				} catch (NumberFormatException e) {
					radius=-1.0;
				}

				if(radius<2000 && radius>0){
					rob.setRadius(radius/1000);}
				else{
					JOptionPane.showMessageDialog(RobotFactory.this,"Le rayon du robot doit etre compris entre 0 et 2000mm");
				}

				try {
					dist_w= Double.parseDouble(textField_WheelDist.getText())/1000;
				} catch (NumberFormatException e) {
					dist_w=-1.0;
				}
				if(dist_w<rob.getRadius() && dist_w>0){
					rob.setDistWheel(dist_w);}
				else{
					JOptionPane.showMessageDialog(RobotFactory.this,"La distance entre les roues doit etre comprise dans le robot (entre 1 et "+rob.getRadius()*1000+"mm)");
				}



				robotPane.repaint();
			}
		});

		JLabel lblDiametreDuRobot = new JLabel("Rayon du robot en mm");
		lblDiametreDuRobot.setBounds(12, 68, 148, 16);
		panel.add(lblDiametreDuRobot);

		JButton btnValidateRobot = new JButton("Valider robot");
		btnValidateRobot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RobotFactory.super.dispose();
				init_done=true;
			}
		});
		btnValidateRobot.setBounds(28, 472, 274, 53);
		panel.add(btnValidateRobot);

		comboBox_IA = new JComboBox();
		comboBox_IA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(comboBox_IA.getSelectedIndex()+1){
				case 1:
					rob.setIa(new IaRobotRandom(rob));
					break;
				case 2:
					rob.setIa(new IaRobotTache(rob));
				break;
				}
			}
		});
		comboBox_IA.setBounds(28, 211, 274, 53);
		comboBox_IA.addItem("Random");
		comboBox_IA.addItem("Reste sur tâche");
		panel.add(comboBox_IA);

		JLabel lblModeIa = new JLabel("Mode IA");
		lblModeIa.setBounds(28, 182, 56, 16);
		panel.add(lblModeIa);

		JLabel lblTypeDeCapteur = new JLabel("Type de capteur \u00E0 ajouter");
		lblTypeDeCapteur.setBounds(28, 295, 274, 16);
		panel.add(lblTypeDeCapteur);
		robotPane.repaint();
		contentPane.validate();
		contentPane.repaint();
		this.setVisible(true);


	}

	/**
	 * Methode permettant de choisir la fenetre de dialog a lancer
	 * @param selectedIndex choix de la fenetre lancer
	 * 
	 */
	protected void PopUpSensor(int selectedIndex) {
		switch (selectedIndex){
		case 0:
			PopUpDirt();
			break;
		case 1:
			PopUpBumper();
			break;
		case 2:
			PopUpLaser();
			break;
		case 3:
			PopUpUltrasound();
			break;
		default:

		}
	}

	/**
	 * Methode d'aquisition des parametre pour un capteur laser
	 */
	private void PopUpLaser(){
		JOptionPane.showMessageDialog(RobotFactory.this,"Ce capteur n'est pas encore implementer");
	}

	/**
	 * Methode d'aquisition des parametre pour un capteur a ultrason
	 */
	private void PopUpUltrasound() {
		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);
		JTextField distField = new JTextField(5);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Angle init:"));
		myPanel.add(xField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Span:"));
		myPanel.add(yField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Distance en mm:"));
		myPanel.add(distField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, 
				"Veuillez sasir les paramtres du capteur", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			Double arc_b=1000.0;
			Double arc_s=1000.0;
			Double dist=-3000.0;
			try {
				arc_b= Double.parseDouble(xField.getText());
				arc_s=Double.parseDouble(yField.getText());
				dist=Double.parseDouble(distField.getText())/1000.0;
			} catch (NumberFormatException er) {
				JOptionPane.showMessageDialog(RobotFactory.this,"L'angle de depart et le span doivent etre compris entre 0 et 360deg");

			}


			if(arc_b<360 && arc_b>=0 && arc_s<360 && arc_s>0 && dist>0 ){
				rob.addSensor(new Ultrasound(arc_b,arc_s,dist));}
			else{
				JOptionPane.showMessageDialog(RobotFactory.this,"L'angle de depart et le span doivent etre compris entre 0 et 360deg");

			}

		}
	}

	/**
	 *  Methode d'aquisition des parametres pour un capteur a de poussiere
	 */
	private void PopUpDirt() {

		JTextField angleField = new JTextField(5);
		JTextField distanceField = new JTextField(5);
		JTextField sizeField = new JTextField(5);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Angle"));
		myPanel.add(angleField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Distance du centre en mm"));
		myPanel.add(distanceField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Diametre du capteur en mm"));
		myPanel.add(sizeField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, 
				"Veuillez parametre le capteur de poussiere", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			Double angle=1000.0;
			Double distance=10000.0;
			Double size=10000.0;
			try {
				angle= Double.parseDouble(angleField.getText());
				distance=Double.parseDouble(distanceField.getText())/1000;
				size=Double.parseDouble(sizeField.getText())/1000;
			} catch (NumberFormatException er) {
				size=1000.0;
			}


			if(angle<360 && angle>=0 && distance<rob.getRadius() && size<rob.getRadius() ){
				rob.addSensor(new DirtSensor(distance,angle,size,rob));}
			else{
				JOptionPane.showMessageDialog(RobotFactory.this,"Erreur lors de la creation du capteur, verifiez les valeurs");

			}

		}

	}

	/**
	 *  Methode d'aquisition des parametres pour un bumper
	 */
	private void PopUpBumper() {

		JTextField xField = new JTextField(5);
		JTextField yField = new JTextField(5);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Angle init:"));
		myPanel.add(xField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Span:"));
		myPanel.add(yField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, 
				"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			Double arc_b=1000.0;
			Double arc_s=1000.0;
			try {
				arc_b= Double.parseDouble(xField.getText());
				arc_s=Double.parseDouble(yField.getText());
			} catch (NumberFormatException er) {
				JOptionPane.showMessageDialog(RobotFactory.this,"L'angle de depart et le span doivent etre compris entre 0 et 360deg");

			}


			if(arc_b<360 && arc_b>=0 && arc_s<360 && arc_s>0 ){
				rob.addSensor(new Bumper(arc_b,arc_s));}
			else{
				JOptionPane.showMessageDialog(RobotFactory.this,"L'angle de depart et le span doivent etre compris entre 0 et 360deg");

			}

		}
	}

	/**
	 * Methode permettant de reccupere le robot cree
	 * @return Retourne le robot cree
	 */
	public Robot getRobot(){
		return rob;
	}

	/**
	 * @return Si la fenetre a finit de cree le robot
	 */
	public boolean isInit_done() {
		return init_done;
	}

}