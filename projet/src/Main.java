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

		Robot groombat = new Robot(10,0,200,200,370,0.5,1);
		DirtSensor sens = new DirtSensor(0.1,180,0.05,groombat);
		groombat.addSensor(sens);
		Bumper bump = new Bumper(90,90);
		groombat.addSensor(bump);
		ArrayList<Robot> robs = new ArrayList<Robot>();
		robs.add(groombat);
		
		Trashcan trash = new Trashcan(500,650,0.3);
		Wall wall1 = new Wall(0,0,0,800);
		Wall wall2 = new Wall(0,800,800,800);
		Wall wall3 = new Wall(800,800,800,0);
		Wall wall4 = new Wall(800,0,0,0);
		ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
		obs.add(trash);
		obs.add(wall1);
		obs.add(wall2);
		obs.add(wall3);
		obs.add(wall4);
		
		DirtSpot dirt = new DirtSpot(150,600,0.8);
		ArrayList<DirtSpot> dirts = new ArrayList<DirtSpot>();
		dirts.add(dirt);
		
		Simulator sim = new Simulator(obs,dirts,robs,0.01,800,800);
		
		JFrame ma_fenetre = new JFrame("Groombat");
	    Move m = new Move(sim);
	    
	    m.setPreferredSize(new Dimension((int)sim.getWidth(),(int)sim.getHeight() + offset));
	    ma_fenetre.setContentPane(m);
	    ma_fenetre.pack();
	    ma_fenetre.show();
	    
	    while (true){
	    	//m.moveRobot(0, 0);
	    	sim.updatePosAllRobot();
	    	// attend 0.01 sec
	    	try  { Thread.sleep(10); }
	    	catch (Exception e) {}
	    	// redessine (appelle entre autres paint())
	    	m.repaint();
	    }
	}
}
