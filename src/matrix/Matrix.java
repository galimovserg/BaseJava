package matrix;


public class Matrix implements IMatrix {
	IMatrix.IMatrixElement matrix[][];
	int m, n;
	Matrix(int m,int n){
		this.m=m;
		this.n=n;
		
	}
	public IMatrix.IMatrixElement at(int i, int j) {
		if(i>=0&&j>=0&&i<m&&j<n) {
			return matrix[i][j];
		}
		return null;
	}
	@Override
	public void setElement(int i,int j, IMatrix.IMatrixElement element) {
		if(i>=0&&j>=0&&i<m&&j<n) {
			matrix[i][j]=element;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
		
	

}
