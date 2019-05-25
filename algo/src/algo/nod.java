package algo;

import java.util.Scanner;

public class nod {
	public static long EuclidGCD(long a, long b) {
		if(a==0) return b;
		if(b==0) return a;
		if(a>=b) return EuclidGCD(a%b,b);
		if(b>=a) return EuclidGCD(a,b%a);
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println(EuclidGCD(sc.nextLong(),sc.nextLong()));
	}

}
