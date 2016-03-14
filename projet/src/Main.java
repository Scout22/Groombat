import java.awt.Dimension;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;


public abstract class Main {

	static int offset = 0;
	static double scaleFactor=200;
	

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {


		//Robot groombat = new Robot(-0.4,-0.5,2,1,Math.toRadians(0),0.4,1,0.2);
		//Robot groombat = new Robot(-0.4,-0.4,2,1,Math.toRadians(0),0.4,1,0.2);

		//Robot groombat = new Robot(0.4,0.4,2,1,Math.toRadians(0),0.4,1,0.2);
		//Robot groombat = new Robot(-0.4,-0.4,2,1,Math.toRadians(0),0.4,1,0.2);
		Robot groombat = new Robot(0.4,0.5,2,1,Math.toRadians(145),0.4,1,0.2);
		//Robot groombat = new Robot(-0.5,-0.4,2,1,Math.toRadians(0),0.4,1,0.2);
		//Robot groombat = new Robot(0.4,0.5,2,1,Math.toRadians(0),0.4,1,0.2);
		//Robot groombat = new Robot(0,0,2,1,Math.toRadians(0),0.4,1,0.2);

		//Robot groombat = new Robot(0,0,2,1,0,0.4,1,0.2);
		

		
		DirtSensor sens = new DirtSensor(0.1,-2*Math.PI/3,0.05,groombat);
		groombat.addSensor(sens);
		
		for(int i=-1;i<360;i+=10){
			//int i=90;
		Bumper bump = new Bumper(i,11);
		groombat.addSensor(bump);}
		
		ArrayList<Robot> robs = new ArrayList<Robot>();
		robs.add(groombat);
		
		Trashcan trash = new Trashcan(0.5,2.5,0.2);
		Trashcan trash2 = new Trashcan(2,2,0.2);
		Trashcan trash3 = new Trashcan(3,1,0.2);
		Trashcan trash4 = new Trashcan(1,1,0.2);
		Wall wall1 = new Wall(0,0,0,4);
		Wall wall2 = new Wall(0,4,4,4);
		Wall wall3 = new Wall(4,4,4,0);
		Wall wall4 = new Wall(4,0,0,0);
		Wall wall5 = new Wall(2,2,2,4);
		Wall wall6 = new Wall(2,2,4,4);
		ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
		obs.add(trash);
		obs.add(trash2);
		obs.add(trash3);
		obs.add(trash4);
		obs.add(wall1);
		obs.add(wall2);
		obs.add(wall3);
		obs.add(wall4);
		obs.add(wall5);
		//obs.add(wall6);
		
		DirtSpot dirt = new DirtSpot(1,1,0.4);
		ArrayList<DirtSpot> dirts = new ArrayList<DirtSpot>();
		//dirts.add(dirt);
		
		Simulator sim = new Simulator(obs,dirts,robs,0.01,4,4);
		
		JFrame ma_fenetre = new JFrame("Groombat");
	    Move m = new Move(sim,scaleFactor);
	    
	    m.setPreferredSize(new Dimension((int)(scaleFactor*sim.getWidth()),(int)(scaleFactor*sim.getHeight()) + offset));
	    ma_fenetre.setContentPane(m);
	    ma_fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    ma_fenetre.pack();
	    ma_fenetre.show();
	    
	    while (true){
	    	//m.moveRobot(0, 0);
	    	for(Robot rob:sim.getRobots()){
	    		rob.getIa().start();
	    	}
	    	sim.timeStep();
	    	// attend 0.01 sec
	    	try  { Thread.sleep(10); }
	    	catch (Exception e) {}
	    	// redessine (appelle entre autres paint())
	    	//System.out.println(groombat.getPosture().toString());
	    	m.repaint();
	    }
	}
}
