
public abstract class Obstacle {
	
	protected String type;
	public Obstacle(){
		type="Not defined";
	}
	public boolean collideSensor(double angleInit, double angleFin, Robot robot) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean isCollideRobot(Robot robot){
		return false;
	}
	public String getType(){
		return type;
	}
	

}
