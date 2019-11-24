package matrix;


public class Matrix<p> implements IMatrix<p> {
	p matrix[][];
	int m, n;
	public Matrix(int m,int n){
		this.m=m;
		this.n=n;
		
	}
	static class MatrixFloat extends Matrix<Float>{

		public MatrixFloat(int m, int n) {
			super(m, n);
		}
		@Override
		public Float add(Float a,Float b) {
			
			return a+b;
		}
		@Override
		public Float mull(Float a,Float b) {
			
			return a*b;
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MatrixFloat A=new MatrixFloat(5,6);
		MatrixFloat B=new MatrixFloat(5,6);
		MatrixFloat C=(MatrixFloat) A.matrixMultiplication(A, B);
		
	}

	@Override
	public p at(int i, int j) {
		// TODO Auto-generated method stub
		if(i>=0&&j>=0&&i<m&&j<n) {
			return matrix[i][j];
		}
		return null;
	}
	@Override
	public void setElement(int i, int j, p element) {
		// TODO Auto-generated method stub
		if(i>=0&&j>=0&&i<m&&j<n) {
			matrix[i][j]=element;
		}
	}

	@Override
	public IMatrix<p> matrixMultiplication(IMatrix<p> a, IMatrix<p> b) {
		Matrix<p> m=new Matrix<p>(a.getM(),b.getN());
		for(int i=0;i<a.getM();i++) {
			for(int j=0;j<b.getN();j++) {
				for(int k=1;k<a.getN();k++) {
					m.setElement(i, j, add(m.at(i, j),mull(a.at(i, k),b.at(k, j))));
				}
			}
		}
		return null;
	}
	@Override
	public p mull(p v1, p v2) {
		return null;
	}
	@Override
	public p add(p v1, p v2) {
		return null;
	}

	@Override
	public int getM() {
		// TODO Auto-generated method stub
		return n;
	}

	@Override
	public int getN() {
		// TODO Auto-generated method stub
		return m;
	}
	
		
	

}
