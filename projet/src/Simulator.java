import java.util.ArrayList;

/**
 * @author Yanis
 * Class gerant la simulation, et s'occupant de la faire evoluer
 */
public class Simulator {
	private ArrayList <Robot> robots;
	private Map terrain;
	private double width=4;
	private double height=4;
	private double deltaT=0.01;
	private double time=0;


	/**
	 * Instencie une simulation vide avec aucun robot, aucun obstacle et aucune tache. 
	 * La dimension du terrain est de 4x4 metres et le pas de temps est de 10ms
	 */
	public Simulator() {
		terrain=new Map();
		robots=new ArrayList <Robot>();
	}
	
	/**
	 * Instencie un robot avec des paramtere definis
	 * @param obs Listes des obstacles
	 * @param dirt Listes des taches
	 * @param rob Listes des robots
	 * @param deltaT Pas de temps de la simulation en s
	 * @param width Largeur du terrain en m
	 * @param height Hauteur du terrain en m
	 */
	public Simulator(ArrayList <Obstacle> obs,ArrayList <DirtSpot> dirt,ArrayList <Robot> rob,double deltaT,double width,double height) {
		terrain=new Map(obs,dirt);
		robots=new ArrayList <Robot>();
		robots=rob;
		this.deltaT=deltaT;
		this.width=width;
		this.height=height;
		addFence();
	}

	/**
	 * Intencie un simulateur avec des parametres predefinis.
	 * Mode=demo : crée une demo avec un robot, des obstacles et des tache
	 * @param mode mode=demo : Crée un simulateur avec un robot et des taches prefabriquer
	 */
	public Simulator(String mode){
		if(mode=="demo"){
			Robot groombat = new Robot(-0.4,-0.5,2,1,Math.toRadians(0),0.4,2,0.2);
			robots = new ArrayList<Robot>();
			robots.add(groombat);


			DirtSensor sens = new DirtSensor(0.1,-2*Math.PI/3,0.05,groombat);
			groombat.addSensor(sens);

			for(int i=-11;i<360;i+=10){
				Bumper bump = new Bumper(i,11);
				groombat.addSensor(bump);}
			Trashcan trash = new Trashcan(0.5,2.5,0.2);
			Trashcan trash2 = new Trashcan(2,2,0.2);
			Trashcan trash3 = new Trashcan(3,1,0.2);

			Wall wall1 = new Wall(0,0,0,4);
			Wall wall2 = new Wall(0,4,4,4);
			Wall wall3 = new Wall(4,4,4,0);
			Wall wall4 = new Wall(4,0,0,0);
			Wall wall5 = new Wall(2,2,2,4);

			terrain=new Map();
			terrain.addObstacle(trash);
			terrain.addObstacle(trash2);
			terrain.addObstacle(trash3);

			terrain.addObstacle(wall1);
			terrain.addObstacle(wall2);
			terrain.addObstacle(wall3);
			terrain.addObstacle(wall4);
			terrain.addObstacle(wall5);


			DirtSpot dirt = new DirtSpot(1,1,0.4);
			terrain.addDirtSpot(dirt);

			width=4;
			height=4;
		}
	}
	
	/**
	 * Reccupere la largeur du terrain
	 * @return Largeur du terrain en metre
	 */
	public double getWidth(){
		return width;
	}
	
	/**
	 * Reccupere la hauteur du terrain
	 * @return hauteur du terrain en metre
	 */
	public double getHeight(){
		return height;
	}
	
	/**
	 * Ajoute une tache a la simulation
	 * @param ds taches a ajouter
	 */
	public void addDirtSpot(DirtSpot ds){
		terrain.addDirtSpot(ds);
	}
	
	/**
	 * Ajoute un obstacle a la simulation
	 * @param ob obstacle a ajouter
	 */
	public void addObstacle(Obstacle ob){
		terrain.addObstacle(ob);		
	}

	/**
	 * Met a jours la position de tout les robots
	 */
	public void updatePosAllRobot(){

		for(Robot rob:robots){
			Robot tempRobot=rob.clone();
			updatePosRobot(tempRobot);
			if(isValidPos(tempRobot)){
				updatePosRobot(rob);
			}
		}
	}
	
	/**
	 * Met a jours l'etat de tout les capteurs
	 */
	private void updateAllSensor() {
		for(Robot rob:robots){
			for(Sensor sens:rob.getSensors()){
				sens.updateState(terrain,rob);
			}
		}

	}

	/**
	 * Met a jour la position d'un robot d'un pas de temps 
	 * @param rob robot dont on cherche a mettre a jours la position
	 */
	private void updatePosRobot(Robot rob){
		Posture p =rob.getPosture();
		p.move(deltaT*rob.getSpeedLeft(), deltaT*rob.getSpeedRight(), rob.getDistWheel());
	}
	
	/**
	 * Verifie qu'une position est valide pour un robot
	 * @param rob robot dont on cherche a verifier la validiter de la position
	 * @return Boolean a vrai si la position est valide, et false sinon
	 */
	public boolean isValidPos(Robot rob){
		for(Obstacle ob:terrain.getObstacles()){
			if(ob.isCollideRobot(rob)){
				return false;
			}
		}
		return true;
	}

	/**
	 * Met a jour d'un pas de temps le terrain
	 */
	private void updateTerrain(){
		for(Robot rob:robots){
			terrain.cleanDirtSpot(rob,deltaT*5*rob.getRadius());
		}}

	/**
	 * Ajoute un robot a la simulation
	 * @param rob robot a ajouter a la simulation
	 */
	public void addRobot(Robot rob){
		robots.add(rob);
	}

	/**
	 * Reccupere la liste des obstacles presents dans la simulation
	 * @return listes des obstacles
	 */
	public ArrayList<Obstacle> getObstacles() {
		return terrain.getObstacles();
	}
	
	/**
	 * Reccupere la liste des taches presentent dans la simulation
	 * @return listes des taches
	 */
	public ArrayList<DirtSpot> getDirtSpots() {
		return terrain.getDirtSpots();
	}
	
	/**
	 * Permet de reccuperer les robots presents dans la simulation
	 * @return ArrayList des Robots present dans la simulation
	 */
	public ArrayList<Robot> getRobots() {
		return robots;
	}
	
	/**
	 * Fait avancer la simulation d'un pas de temps
	 */
	public void timeStep() {
		updatePosAllRobot();
		updateAllSensor();
		updateTerrain();
		time=time+deltaT;

	}
	
	/**
	 * Permet de reccuperer le pas de temps de la simulation
	 * @return pas de temps de la simulation
	 */
	public double getDeltaT() {
		return deltaT;
	}
	
	/**
	 * Permet de remplacer le terrain de la simulation
	 * @param mapy Terrain a ajouter
	 */
	public void setMap(Map mapy) {
		terrain=mapy;

	}
	
	/**
	 * Ajouter une barriere rectagulaire autour du terrain
	 */
	public void addFence(){
		terrain.addObstacle(new Wall(0,0,0,height));
		terrain.addObstacle(new Wall(0,height,width,height));
		terrain.addObstacle(new Wall(width,height,width,0));
		terrain.addObstacle(new Wall(width,0,0,0));
	}
	
	/**
	 * Reccupere le terrain utiliser par la simulation
	 * @return Terrain utiliser par la simulation
	 */
	public Map getMap() {
		return terrain;
	}
	
	/**
	 * Retire le dernier robot ajouté à la simulation
	 */
	public void removeLastRobot() {
		if(robots.size()>0){
		robots.remove(robots.size()-1);}
		
	}

	/**
	 * @return temps ecoulé en seconde depuis le debut
	 */
	public double getTime() {
		return time;
	}

}
