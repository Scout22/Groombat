import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;


public abstract class Main {

	static int offset = 0;
	

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		Robot groombat = new Robot(0,0,200,200,0,0.5,1);
		DirtSensor sens = new DirtSensor(0.1,180,0.05,groombat);
		groombat.addSensor(sens);
		Bumper bump = new Bumper(90,90);
		groombat.addSensor(bump);
		Trashcan trash = new Trashcan(500,650,0.3);
		//public Robot(double speedLeft, double speedRight, double x, double y, double theta, int mode, ArrayList<Sensor> sensors){
		Simulator sim = new Simulator();
		
		JFrame ma_fenetre = new JFrame("Groombat");
	    Move m = new Move(sim);
	    
	    m.setPreferredSize(new Dimension(sim.getWidth(),sim.getHeight() + offset));
	    ma_fenetre.setContentPane(m);
	    ma_fenetre.pack();
	    ma_fenetre.show();
	    
	    while (true){
	    	m.moveRobot(0, 0);
	    	// attend 0.01 sec
	    	try  { Thread.sleep(10); }
	    	catch (Exception e) {}
	    	// redessine (appelle entre autres paint())
	    	m.repaint();
	    }
	}
}
