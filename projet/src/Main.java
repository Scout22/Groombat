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

	public static void main(String[] args) { // on lance le main avec l'argument --text pour avoir l'interface textuelle

		/*
		//Robot groombat = new Robot(-0.4,-0.5,2,1,Math.toRadians(0),0.4,1,0.2);
		//Robot groombat = new Robot(0.4,0.4,2,1,Math.toRadians(270),0.4,1,0.2);
		//Robot groombat = new Robot(-0.4,-0.4,2,1,Math.toRadians(0),0.4,1,0.2);
		//Robot groombat = new Robot(0.4,0.5,2,1,Math.toRadians(145),0.4,2,0.2);
		Robot groombat = new Robot(0.1,0.1,2,1,Math.toRadians(89),0.4,2,0.2);
		//Robot groombat = new Robot(-0.5,-0.4,2,1,Math.toRadians(0),0.4,1,0.2);
		//Robot groombat = new Robot(0.4,0.5,2,1,Math.toRadians(0),0.4,1,0.2);
		//Robot groombat = new Robot(0,0,2,1,Math.toRadians(0),0.4,1,0.2);
		ArrayList<Robot> robs = new ArrayList<Robot>();
		robs.add(groombat);


		DirtSensor sens = new DirtSensor(0.1,-2*Math.PI/3,0.05,groombat);
		groombat.addSensor(sens);

		for(int i=-11;i<360;i+=10){
			Bumper bump = new Bumper(i,11);
			groombat.addSensor(bump);}

		Trashcan trash = new Trashcan(0.5,2.5,0.2);
		Trashcan trash2 = new Trashcan(2,2,0.2);
		Trashcan trash3 = new Trashcan(3,1,0.2);
		//Trashcan trash4 = new Trashcan(1,1,0.2);
		Wall wall1 = new Wall(0,0,0,4);
		Wall wall2 = new Wall(0,4,4,4);
		Wall wall3 = new Wall(4,4,4,0);
		Wall wall4 = new Wall(4,0,0,0);
		Wall wall5 = new Wall(2,2,2,4);
		//Wall wall6 = new Wall(2,2,4,4);

		ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
		obs.add(trash);
		obs.add(trash2);
		obs.add(trash3);
		//obs.add(trash4);
		obs.add(wall1);
		obs.add(wall2);
		obs.add(wall3);
		obs.add(wall4);
		obs.add(wall5);
		//obs.add(wall6);

		DirtSpot dirt = new DirtSpot(1,1,0.4);
		ArrayList<DirtSpot> dirts = new ArrayList<DirtSpot>();
		dirts.add(dirt);
		 
		Simulator sim = new Simulator(obs,dirts,robs,0.01,4,4);
		*/
		
		
		//menu
		Simulator sim = config_menu();
		
		UI g;
		if(args.length >= 1 && args[0]=="--text"){
			g=new TextUI(sim);
		}
		else{
			g=new GraphicalUI(sim);
		}
		
		//simulation
		while (true){
			run_simulation(sim, g);
		}
	}

	private static Simulator config_menu() {
		ArrayList<Robot> robs = new ArrayList<Robot>();
		ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
		ArrayList<DirtSpot> dirts = new ArrayList<DirtSpot>();
		MenuUI menu = new MenuUI();
		while(!menu.init_done){
			menu.update_Menu();
		}
		// quand ça fini, menu comporte la taille en X, en Y et le delta T qu on utilise plus bas avec des gets
		// il ne reste plus qu a mettre le createur de robot et le createur de map ici
		// si ils remplissent robs, obs et dirts, tout marche automatiquement normalement
		
		Simulator sim = new Simulator(obs,dirts,robs,menu.getDel(),menu.getX(),menu.getY());
		return sim;
	}

	static void run_simulation(Simulator sim, UI g){

		//Actualise la commande 
		for(Robot rob:sim.getRobots()){
			rob.getIa().start();
		}

		//Fait avancer la simulation d'un pas de temps
		sim.timeStep();

		// attend 0.01 sec
		try  { Thread.sleep((int)(1000*sim.getDeltaT())); }
		catch (Exception e) {}

		//Actualise l'affichange
		g.updateDisplay();
	}
}
