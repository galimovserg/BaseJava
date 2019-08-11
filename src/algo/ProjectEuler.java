package algo;

public class ProjectEuler {

	static void ProblemOne() {
		int sum=0;
		for(int i=0;i<1000;i++) {
			if(i%3==0||i%5==0) {
				sum+=i;
			}
		}
		System.out.println(sum);
	}
	static void ProblemTwo() {
		long sum=0;
		long firstfib=1;
		long lastfib=2;
		while(lastfib<=4000000){
			if(lastfib%2==0) {
				sum+=lastfib;
			}
			long nextfib=lastfib+firstfib;
			firstfib=lastfib;
			lastfib=nextfib;
		}
		System.out.println(sum);
	}
	
	static void ProblemThree() {
		
		long value=Long.valueOf("600851475143");
		long i=2;
		while(value>1) {
			if(value%i==0) {
				value=value/i;
			}else i++;
			
		}
		System.out.println(i);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProblemThree();
	}

}
