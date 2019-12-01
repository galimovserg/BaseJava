package algo;

public class Matrix {

	private double M[][];
	public Matrix(double M[][]){
		this.M=M;
	}
	
	public int getM() {
		if(M==null)return 0;
		return M.length;
	}
	public int getN() {
		if(M==null)return 0;
		if(M.length==0)return 0;
		if(M[0]==null) return 0;
		return M[0].length;
	}
	public double getV(int i, int j) {
		if(M==null)return 0;
		if(M.length==0)return 0;
		if(M[i]==null) return 0;
		return M[i][j];
	}
	public void DoTransposition() {
		double newM[][]=new double[getN()][getM()];
		for(int i=0;i<getN();i++) {
			for(int j=0;j<getM();j++) {
				newM[i][j]=M[j][i];
			}
		}
		M=newM;
	}
	
	public Matrix DoMultiplication(Matrix b) {
		int m=getM();
		int n=getN();
		int k=b.getN();
		
		double newm[][]=new double[m][k];
		for(int i=0;i<m;i++) {
			for(int j=0;j<k;j++) {
				newm[i][j]=0;
				for(int c=0;c<n;c++) {
					newm[i][j]+=getV(i,c)*b.getV(c, j);
				}
			}
		}
		
		return new Matrix(newm);
		
	}
	public Matrix getTriangularMatrix() {
		double[][] locm=new double[getM()][getN()];
		for(int i=0;i<getM();i++) {
			for(int j=0;j<getN();j++) {
				locm[i][j]=M[i][j];
			}
		}
		for(int j=0;j<getM()-1;j++){
			for(int i=j+1;i<getM();i++){
				if(locm[j][j]!=0){
					double k=locm[i][j]/locm[j][j];
					for(int t=j;t<getN();t++){	
						locm[i][t]=locm[i][t]-locm[j][t]*k;
					}
				}
			}
			
		}
		return new Matrix(locm);
	}
	
	public double det() {
		Matrix triangular=getTriangularMatrix();
		double dt=1;
		for(int i=0;i<triangular.getM();i++) {
			dt*=triangular.getV(i,i);
		}
		
		return dt;
	}
	private Matrix delete(int icd, int jcd) {
		double newm[][]=new double[getM()-1][getN()-1];
		
		for(int i=0;i<getM();i++) {
			
			if(i!=icd)
			for(int j=0;j<getN();j++) {
				if(j!=jcd) {
					int icount=i;
					int jcount=j;
					if(icount>icd)icount--;
					if(jcount>jcd)jcount--;
					newm[icount][jcount]=getV(i, j);
				}
			}
		}
		return new Matrix(newm);
	}
	private Matrix getMinorMatrix(Matrix m) {
		// TODO Auto-generated method stub
		if(m.getM()==1) {
			return m;
		}else {
			double newm[][]=new double[m.getM()][m.getN()];
			for(int i=0; i<m.getM();i++) {
				for(int j=0;j<m.getN();j++) {
					Matrix dm=m.delete(i, j);
					newm[i][j]=dm.det();
				}
			}
			return new Matrix(newm);
		}
		
	}
	public Matrix inv() {
		Matrix minormatrix=getMinorMatrix(this);
		double invm[][]=new double[getM()][getN()];
		double dt=det();
		for(int i=0;i<getM();i++) {
			for(int j=0;j<getN();j++) {
				
				invm[i][j]=minormatrix.getV(i, j)/dt;
				if((i+j)%2==1) invm[i][j]=-invm[i][j];
			}
		}
		Matrix im=new Matrix(invm);
		im.DoTransposition();
		return im;	
	}
	

	public String toString() {
		String st="";
		if(M==null) {
			return "null";
		}
		st+="{";
		for(int i=0;i<getM();i++) {
			st+="{";
			for(int j=0;j<getN()-1;j++) {
				st+=""+M[i][j]+", ";
			}
			st+=M[i][getN()-1];
			
			st+="}";
			if(i<getM()-1) st+=","+'\n';
		}
		
		st+="}";
		return st;
		
	}
	
	public static Matrix[] StringToVector(String msg, Matrix key) {
		Matrix matrixmsg[]=new Matrix[msg.length()/key.getM()];
		for(int i=0;i<matrixmsg.length;i++) {
			double blockmsg[][]=new double[key.getM()][1];
			for(int j=0;j<key.getM();j++) {
				blockmsg[j][0]=msg.charAt(j+i*key.getM());
			}
			matrixmsg[i]=new Matrix(blockmsg);
		}
		
		return matrixmsg;
	}
	
	public static String  VectorToString(Matrix[] decmsg) {
		String res="";
		for(int i=0;i<decmsg.length;i++) {
			for(int j=0;j<decmsg[i].getM();j++) {
				res+=(char)Math.round(decmsg[i].getV(j, 0));
			}
		}
		
		return res;
	}
	public static Matrix[] encode(Matrix msg[], Matrix key) {
		Matrix encodemsg[]=new Matrix[msg.length];
		for(int i=0;i<encodemsg.length;i++) {
			encodemsg[i]=key.DoMultiplication(msg[i]);
		}
		return encodemsg;
	}
	public Matrix[] decode(Matrix encmsg[], Matrix invkey) {
		Matrix encodemsg[]=new Matrix[encmsg.length];
		for(int i=0;i<encodemsg.length;i++) {
			encodemsg[i]=invkey.DoMultiplication(encmsg[i]);
		}
		return encodemsg;
	}
	public static void main(String[] args) {
		
		double keydata[][]=new double[][] {
			{1,2,3},
			{4,3,5},
			{6,7,8}};
		
		Matrix key=new Matrix(keydata);
		System.out.println("Ключ шифрования: ");
		System.out.println(key);
		
		String message="Хорошо";
		System.out.println("Сообщение для зашифровки: ");
		System.out.println(message);
		Matrix msg[]=StringToVector(message,key);
		System.out.println("Сообщение в векторном виде: ");
		for(Matrix msgblock:msg) {
			System.out.println(msgblock.toString());
		}
		
		
		Matrix encmsg[]=encode(msg,key);
		System.out.println("Результат шифрования: ");
		for(Matrix encmsgblock:encmsg) {
			System.out.println(encmsgblock.toString());
		}
		
		System.out.println("Результат расшифровки в векторном виде: ");
		Matrix invkey=key.inv();
		Matrix decmsg[]=encode(encmsg,invkey);
		for(Matrix decmsgblock:decmsg) {
			System.out.println(decmsgblock.toString());
		}
		
		System.out.println("Результат расшифровки: ");
		System.out.println(VectorToString(decmsg));
	}

}
