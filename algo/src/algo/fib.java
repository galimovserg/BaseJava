package algo;

import java.math.BigInteger;
import java.util.Scanner;

public class fib{

	/**
	 * находит n-е число Фибоначчи
	 * @param args
	 */
	private BigInteger fib(int n) {
		
		BigInteger fib=BigInteger.valueOf(0);
		BigInteger fiblast=BigInteger.valueOf(1);
		for(int i=2;i<=n;i++) {
			BigInteger fibsv= fiblast;
			fiblast=fiblast.add(fib);
			fib=fibsv;
		}
		return fiblast;
	}
	private void run(int n) {
		System.out.println(fib(n));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long startTime=System.currentTimeMillis();
		new fib().run(100000);
		long finishTime=System.currentTimeMillis();
		System.out.println(finishTime-startTime+" ms");
		
		
	}

}
