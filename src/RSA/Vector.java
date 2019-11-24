package RSA;

public class Vector{
	private long x;
	private long y;
	Vector(long x, long y){
		this.y=y;
		this.x=x;
	}
	long getX() {
		return this.x;
	}
	long getY() {
		return this.y;
	}
	void consolePrint(){
		System.out.println("x= "+this.x+" y= "+this.y);
	}
}
