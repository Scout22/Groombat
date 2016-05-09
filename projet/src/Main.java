

/**
 * @author Yanis
 * Class principale permettant de lancer l'ensemble du programme
 */
public abstract class Main {

	static int offset = 0;
	static double scaleFactor=200;
	static Simulator sim;


	public static void main(String[] args) { // on lance le main avec l'argument --text pour avoir l'interface textuelle

		Simulator sim = config_menu();
		UI g;
		if(args.length >= 1 && args[0]=="--text"){
			g=new TextUI(sim);
		}
		else{
			g=new GraphicalUIP(sim);
		}

		//simulation
		while (true){
			run_simulation(sim, g);
		}
	}

	/**
	 * Configuration initiale de la simulation.
	 * Permet de creer une simulation customisee, avec un ou plusieurs robots places a loisir dans une carte egalement cree.
	 * Bloque la simulation tant que l'initialisation n'est pas terminee.
	 */
	private static Simulator config_menu() {
		MenuUI menu = new MenuUI();
		while(!menu.isInit_done()){
			menu.update_Menu();
			try  { Thread.sleep(10); }
			catch (Exception e) {}
		}
		try  { Thread.sleep(100); }
		catch (Exception e) {}
		// quand ça fini, menu comporte la taille en X, en Y et le delta T qu on utilise plus bas avec des gets
		// il ne reste plus qu a mettre le createur de robot et le createur de map ici
		// si ils remplissent robs, obs et dirts, tout marche automatiquement normalement

		sim =menu.getSimulator();
		if(!menu.isDemoMode()){
			MapFactory map_f =new MapFactory(sim);
			while(!map_f.isInitDone()){
				try  { Thread.sleep(10); }
				catch (Exception e) {}
			}
			for(int i=0;i<menu.getNbRobot();i++){
				RobotFactory rob_f=new RobotFactory();
				while(!rob_f.isInit_done()){
					try  { Thread.sleep(10); }
					catch (Exception e) {}
				}
				EnvFactory env=new EnvFactory(sim,rob_f.getRobot());
				while(!env.isInitDone()){
					try  { Thread.sleep(10); }
					catch (Exception e) {}
				}}}

		return sim;
	}


	/**
	 * Actualise le programme.
	 * Lance la simulation puis met a jour l'affichage graphique.
	 */
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
