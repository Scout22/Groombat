import java.util.ArrayList;


public class Robot {

	double speedRight;
	double speedLeft;
	Posture posture;
	double radius;
	ArrayList<Sensor> sensors;
	
	
	public double getSpeedRight() {
		return speedRight;
	}

	public void setSpeedRight(double speedRight) {
		this.speedRight = speedRight;
	}

	public double getSpeedLeft() {
		return speedLeft;
	}

	public void setSpeedLeft(double speedLeft) {
		this.speedLeft = speedLeft;
	}

	public Posture getPosture() {
		return posture;
	}

	public void setPosture(Posture posture) {
		this.posture = posture;
	}

	public ArrayList<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(ArrayList<Sensor> sensors) {
		this.sensors = sensors;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
