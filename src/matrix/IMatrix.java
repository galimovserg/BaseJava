package matrix;

public interface IMatrix<f> {
	f at(int i, int j);
	void setElement(int i,int j,f element);
	
	IMatrix<f> matrixMultiplication(IMatrix<f> a,IMatrix<f> b);
	int getM();
	int getN();
	public interface IMatrixElement{
		
	}
	f add(f v1, f v2);
	f mull(f v1, f v2);
}
