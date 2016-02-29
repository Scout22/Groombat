


public class Vector {
	public double x;
	public double y;
	public double z;
	
	public Vector(){
		this.x=0;
		this.y=0;
		this.z=0;
	}
	
	public Vector(double x1,double y1,double z1 ){
		this.x=x1;
		this.y=y1;
		this.z=z1;
	}
	
	public Vector additionner(Vector v2){
		Vector res=new Vector();
		res.x=v2.x+this.x;
		res.y=v2.y+this.y;
		res.z=v2.z+this.z;
		return res;
	}
	
	public double norme(){
		return Math.sqrt((this.x)*(this.x)+(this.y)*(this.y)+(this.z)*(this.z));
	}
	
	public double calculerProduitScalaire(Vector v2){
		return (this.x)*(v2.x)+(this.y)*(v2.y)+(this.z)*(v2.z);
	}
	
	public Vector tourner(double alpha){
		Vector res=new Vector();
		res.x=Math.cos(alpha)*this.x-Math.sin(alpha)*this.y;
		res.y=Math.sin(alpha)*this.x+Math.cos(alpha)*this.y;
		res.z=this.z;
		return res;
	}
	
	void afficher(){
		System.out.println(x+","+y+","+z);
	}
	
}