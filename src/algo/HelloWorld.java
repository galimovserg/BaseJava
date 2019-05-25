package algo;

public class HelloWorld {
	private void run() {
		long sum=0;
		for(int i=0;i<1000000000;i++) {
			sum+=i;
		}
		System.out.println("Hello, world!");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime=System.currentTimeMillis();
		new HelloWorld().run();
		long finishTime=System.currentTimeMillis();
		System.out.println(finishTime-startTime+" ms");
	}

}
