package algo;

public class HeapSort {
	
	public int Parent(int i) {
		return (int)Math.floor(i/2.0);
	}
	public static int Left(int i) {
		return 2*i;
	}
	public static int Right(int i) {
		return 2*i+1;
	}
	/**
	 * ¬останавливает свойство невозрастани€ дл€ элемента с индексом pos
	 * @param arr
	 * @param heap_size
	 * @param pos
	 * @return
	 */
	public static int[] MaxHeapify(int arr[],int heap_size, int pos) {
		int l=Left(pos);
		int r=Right(pos);
		int lr=pos;
		if(l<=heap_size&&arr[l]>arr[pos]) {
			lr=l;
		}else {
			lr=pos;
		}
		if(r<=heap_size&&arr[r]>arr[lr]) {
			lr=r;
		}
		if(lr!=pos) {
			int buf=arr[pos];
			arr[pos]=arr[lr];
			arr[lr]=buf;
			arr=MaxHeapify(arr,heap_size,lr);
		}
		return arr;
	}
	/**
	 * —троит невозрастающюю кучу на основе массива arr
	 * @param arr
	 * @param heap_size
	 * @return
	 */
	public static int[] Build_Max_Heap(int arr[],int heap_size) {
		for(int i=(int)Math.floor(heap_size/2.);i>=1;i--) {
			arr=MaxHeapify(arr,heap_size,i);
		}
		return arr;
	}
	/**
	 * –еализует пирамидальную сортировку(или сортировка кучей)
	 * @param arr
	 * @return
	 */
	public static int[] sort(int arr[]) {
		int heap_size=arr.length-1;
		//дл€ начала построим невозрастающюю пирамиду
		arr=Build_Max_Heap(arr,heap_size);
		
		for(int i=arr.length-1;i>=2;i--) {
			//максимальный элемент лежит в корне пирамиды
			int buf=arr[1];
			arr[1]=arr[i];
			arr[i]=buf;
			//уменьшаем размер(таким образом максимальный элемент извлекаетс€ из пирамиды)
			heap_size--;
			//востанавливаем свойство невозрастани€ дл€ корн€, так как в корне другое число и условие может быть нарушено
			arr=MaxHeapify(arr,heap_size,1);
		}
		
		return arr;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr= new int[10000000];
		for(int i=0;i<arr.length;i++)
			arr[i]=(int)(Math.random()*1000000)-500000;
		System.out.println("array was generated!");
		int[] arrsort=sort(arr);
		System.out.println("array was sorted!");
		boolean f=false;
		for(int i=01;i<arr.length-1;i++) {
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
	}

}
