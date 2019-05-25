package algo;

public class Point {
	private float X;
	private float Y;
	private static float Eps=(float) 0.000000001;
	Point(float X, float Y){
		this.X=X;
		this.Y=Y;
	}
	
	public void setX(float X){
		this.X=X;
	}
	void setY(float Y){
		this.Y=Y;
	}
	public static void setEps(float eps) {
		Eps=eps;
	}
	public float getX() {
		return this.X;
	}
	public float getY() {
		return this.Y;
	}
	public void setXY(float X, float Y) {
		this.X=Y;
		this.Y=Y;
	}
	public float getRQ(Point p) {
		return (float) (Math.pow(this.getX()-p.getX(), 2)+Math.pow(this.getY()-p.getY(), 2));
	}
	public static Point[] getMin(Point[] arr) {
		if(arr.length>=2) {
			Point[] p=new Point[2];
			p[0]=arr[0];
			p[1]=arr[1];
			float d=p[0].getRQ(p[1]);
			for(int i=0;i<arr.length;i++) {
				for(int j=i+1;j<arr.length;j++) {
					float dloc=arr[i].getRQ(arr[j]);
					if(dloc<d) {
						p[0]=arr[i];
						p[1]=arr[j];
						d=dloc;
					}
				}
			}
		
			return p;
		}else
		return null;
	}
	public static Point[] getMinBin(Point[] arr) {
		
		return null;
	}
	
	public boolean equals(Point p) {
		if(Math.abs(this.X-p.getX())<Eps&&Math.abs(this.Y-p.getY())<Eps) {
			return true;
		}else {
			return false;
		}
	}
	public String toString() {
		return "("+this.X+", "+this.Y+")";
	}
	public static void main(String args[]) {
		Point parr[]= new Point[100000];
		for(int i=0;i<parr.length;i++) {
			parr[i]=new Point((float)(Math.random()*100.-50),(float)(Math.random()*100.-50));
			//System.out.println(parr[i].toString());
		}
		System.out.println("Array of Points is was generated!");
		Point[] pmin=Point.getMin(parr);
		System.out.println("Пары ближайших точек");
		if(pmin!=null) {
			System.out.println(pmin[0].toString());
			System.out.println(pmin[1].toString());
		}
	}
}
