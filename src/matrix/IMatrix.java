package matrix;

public interface IMatrix {
	IMatrixElement at(int i, int j);
	void setElement(int i,int j,IMatrixElement element);
	
	public interface IMatrixElement{
		
	}
}
