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
		Wall wall1 = new Wall(0,0,0,800);
		Wall wall2 = new Wall(0,800,800,800);
		Wall wall3 = new Wall(800,800,800,0);
		Wall wall4 = new Wall(800,0,0,0);
		ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
		
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
