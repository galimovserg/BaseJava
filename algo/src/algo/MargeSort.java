package algo;

public class MargeSort {

	public static int[] marge(int[] l, int[] r) {
		int [] margearray=new int[l.length+r.length];
		int lpos=0;
		int rpos=0;
		int c=0;
		for(;c<l.length+r.length;c++) {
			if(lpos>=l.length||rpos>=r.length) {
				break;
			}
			if(l[lpos]<r[rpos]) {
				margearray[c]=l[lpos];
				lpos++;
			}else {
				margearray[c]=r[rpos];
				rpos++;
			}
				
			
		}
		for(;lpos<l.length;lpos++) {
			
			margearray[c]=l[lpos];
			c++;
		}
		for(;rpos<r.length;rpos++) {
			
			margearray[c]=r[rpos];
			c++;
		}
		return margearray;
	}
	public static int[] margeSort(int[] arr) {
		if(arr.length<=1) {
			return arr;
		}
		int l[]=new int[arr.length-arr.length/2];
		int r[]=new int[arr.length/2];
		int lpos=0;
		int rpos=0;
		
		for(int c=0;c<arr.length;c++) {
			if(c%2==0) {
				l[lpos]=arr[c];
				lpos++;
			}else {
				r[rpos]=arr[c];
				rpos++;
			}
		}
		l=margeSort(l);
		r=margeSort(r);
		return marge(l,r);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		int[] l= {4,4};
		int[] r= {2,1};
		int[] m=marge(l,r);
		for(int i=0;i<m.length;i++) {
			System.out.print(m[i]+" ");
		}
		*/
		System.out.println();
		int[] arr= new int[10000000];
		for(int i=0;i<arr.length;i++)
			arr[i]=(int)(Math.random()*1000000)-500000;
		
		System.out.println("array was generated!");
		int[] arrsort=margeSort(arr);
		System.out.println("array was sorted!");
		boolean f=false;
		for(int i=0;i<arr.length-1;i++) {
			if(arrsort[i]<=arrsort[i+1]) {
				
			}else {
				f=true;
				break;
			}
		}
		if(f) {
			System.out.println("OMG!");
		}else {
			System.out.println("nice!");
		}
		
		/*for(int i=0;i<arrsort.length;i++) {
			System.out.print(arrsort[i]+" ");
		}*/
	}

}
