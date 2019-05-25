package algo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InverseCounter {
	static class Cortage {
		private int count;
		private int[] arr;
		Cortage(int  count,int[] arr){
			this.count=count;
			this.arr=arr;
		}
		public int getCount() {
			return this.count;
		}
		public int[] getArray() {
			return this.arr;
		}
	}
	public static Cortage marge_and_count(int[] A, int[] B) {
		int count=0;
		int[] margearr=new int[A.length+B.length];
		int apos=0;
		int bpos=0;
		int c=0;
		for(;c<margearr.length;c++) {
			if(apos>=A.length||bpos>=B.length) {
				break;
			}
			if(A[apos]<=B[bpos]) {
				margearr[c]=A[apos];
				apos++;
			}else {
				margearr[c]=B[bpos];
				bpos++;
				count+=A.length-apos;
			}
		}
		for(;bpos<B.length;bpos++) {
			
			margearr[c]=B[bpos];
			c++;
		}
		for(;apos<A.length;apos++) {
			
			margearr[c]=A[apos];
			c++;
		}
		return new Cortage(count,margearr);
	}
	/**
	 * Алгоритм находит количество инверсий в массиве
	 * методом "разделяй и властвуй"
	 * вернет отсортированный массив и число в виде Cortage
	 * @param arr
	 * @return
	 */
	public static Cortage sort_and_count(int[] arr) {
		if(arr.length<=1) {
			return new Cortage(0,arr);
		}
		int[] l=new int[arr.length/2];
		int[] r=new int[arr.length-arr.length/2];
		for(int i=0;i<l.length;i++) {
			l[i]=arr[i];
		}
		for(int i=0;i<r.length;i++) {
			r[i]=arr[l.length+i];
		}
		Cortage ls=sort_and_count(l);
		Cortage rs=sort_and_count(r);
		Cortage ms=marge_and_count(ls.getArray(),rs.getArray());
		return new Cortage(ls.getCount()+rs.getCount()+ms.getCount(),ms.getArray());
	}
	static List<Integer> a=Collections.emptyList();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr= {14,8,2,4,3,9,0,11};
		
		Cortage cr=sort_and_count(arr);
		System.out.println("count: "+cr.getCount());
		int[] arrsort=cr.getArray();
		for(int i=0;i<arrsort.length;i++) {
			System.out.print(arrsort[i]+" ");
		}
	}
}
