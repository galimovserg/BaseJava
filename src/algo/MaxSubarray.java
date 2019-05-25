package algo;

public class MaxSubarray {
	static class Cortage{
		public int low,high;
		public double sum;
		Cortage(int low, int high, double sum){
			this.low=low;
			this.high=high;
			this.sum=sum;
		}
		public String toString(){
			return "("+low+", "+high+", "+sum+")";
		}
	}
	public static Cortage Find_Max_Crossing_Subarray(int low,int mid, int high,double[] arr){
		int left_max=mid;
		double left_sum=arr[mid];
		double sum=arr[mid];
		
		for(int i=mid-1;i>=low;i--){
			sum+=arr[i];
			if(sum>left_sum){
				left_sum=sum;
				left_max=i;
			}
		}
		int right_max=mid+1;
		double right_sum=arr[mid+1];
		sum=arr[mid+1];
		for(int i=mid+2;i<=high;i++){
			sum+=arr[i];
			if(sum>right_sum){
				right_sum=sum;
				right_max=i;
			}
		}
		return new Cortage(left_max,right_max,left_sum+right_sum);
	}
	/**
	 * Находит максимальный подмассив в массиве arr, методом "разделяй и властвуй"
	 * @param low
	 * @param high
	 * @param arr
	 * @return
	 */
	public static Cortage Find_Maximum_Subarray(int low,int high,double arr[]){
		if(low==high){
			return new Cortage(low,high,arr[low]);
		}
		int mid=(int) Math.floor((low+high)/2.);
		Cortage left=Find_Maximum_Subarray(low,mid,arr);
		Cortage right=Find_Maximum_Subarray(mid+1,high,arr);
		Cortage cross=Find_Max_Crossing_Subarray(low,mid,high,arr);
		if(left.sum>=right.sum&&left.sum>=cross.sum){
			return left;
		}else
			if(right.sum>=left.sum&&right.sum>cross.sum){
				return right;
			}else{
				return cross;
			}
		
	}
	/**
	 * Находит максимальный подмассив за линейное время
	 * @param arr
	 * @return
	 */
	public static Cortage Find_Maximum_SubarrayL(double arr[]) {
		double anssum=arr[0];
		int anslow=0;
		int anshigh=0;
		double sum=0;
		int low=0;
		int high=0;
		
		for(int i=0;i<arr.length;i++) {
			sum+=arr[i];
			high=i;
			if(sum>anssum) {
				anssum=sum;
				anslow=low;
				anshigh=high;
			}
			if(sum<0) {
				sum=0;
				low=i+1;
				high=i+1;
			}
		}
		return new Cortage(anslow,anshigh,anssum);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double A[]={-100,-200,12,-30,32,-5,10,-20};
		Cortage res=Find_Maximum_Subarray(0,A.length-1,A);
		System.out.println(res.toString());
		Cortage res2=Find_Maximum_SubarrayL(A);
		System.out.println(res2.toString());
	}

}
