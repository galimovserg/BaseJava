package algo;

public class RSA {
	
	private long p;
	private long q;
	private long N;
	private long fN;
	private long e;
	private long d;
	
	private static final long minFirstValue=40;
	private static final long maxFirstValue=80;
	private static final long minSecondValue=70;
	private static final long maxSecondValue=120;
	
	//Euclidean algorithm
	static long GCD(long a, long b) {
		while(b!=0) {
			long t=b;
			b=a%b;
			a=t;
		}
		return a;
		/*
		if(a%b==0) {
			return b;
		}else {
			return GCD(b, a%b);
		}*/
	}
	static class Vector{
		private long x;
		private long y;
		Vector(long x, long y){
			this.y=y;
			this.x=x;
		}
		long getX() {
			return this.x;
		}
		long getY() {
			return this.y;
		}
		void consolePrint(){
			System.out.println("x= "+this.x+" y= "+this.y);
		}
	}
	/**
	 * Класс содержит поля открытого ключа,
	 * и метод шифрования строки
	 * @author Sergey
	 *
	 */
	public static class PublicKey{
		private long n;
		private long e;
		PublicKey(long n, long e){
			this.n=n;
			this.e=e;
		}
		String encode(String message) {
			String encmessage="";
			for(int i=0;i<message.length();i++) {
				long charmsg=(long) message.charAt(i);
				long charenc=fastpow(charmsg,e,n);
				long basel=(long)256*256*256*256;
				encmessage+=(char)(charenc%(basel));
			}
			return encmessage;
		}
	}
	public static class DecryptionKey{
		private long n;
		private long d;
		DecryptionKey(long n, long d){
			this.n=n;
			this.d=d;
		}
		String decode(String encmessage) {
			String message="";
			for(int i=0;i<encmessage.length();i++) {
				long charencmsg=(long) encmessage.charAt(i);
				long chardec=fastpow(charencmsg,d,n);
				long basel=(long)256*256*256*256;
				message+=(char)(chardec%(basel));
			}
			return message;
		}
	}
	//Extended Euclidean Algoritm
	public static Vector GCDExtended(long a, long b) {
		if(a%b==0) {
			return new Vector(0,1);
		}
		long divAB=a/b;
		Vector v=GCDExtended(b,a%b);
		return new Vector(v.getY(),v.getX()-v.getY()*divAB);
	}
	/**
	 * Производит быстрое возведение в степень по модулю
	 * @param a
	 * @param n
	 * @param base
	 * @return (a^n) mod base
	 */
	public static long fastpow(long a, long n, long base) {
		if(n==0) 
			return 1;
		
		if(n%2==0) {
			
			long result=fastpow(a,n/2,base);
			return (result*result)%base;
		}else
		{
			return (a*fastpow(a,n-1,base))%base;
		}
		
	}
	/**
	 * Производит генерацию случайного числа
	 * в заданном диапазоне
	 * @param min
	 * @param max
	 * @return случайное число от min до max
	 */
	public static long generate(long min,long max) {
		return (long)(Math.random()*(max-min))+min;
	}
	//количество проверок
	private static int k=100;
	/**
	 * Производит тест числа на простоту по методу Ферма
	 * @param t
	 * @return false, если число составное, и false, если простое
	 */
	public static boolean FermMethod(long t) {
		for(int i=0;i<k;i++) {
			long a=generate(2,t-1);
			if(fastpow(a,t-1,t)%t!=1) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Генерирует случайное простое число в заданном диапазоне
	 * @param min
	 * @param max
	 * @return простое число
	 */
	public static long genNewPrimeNumber(long min, long max) {
		long Zvalue=generate(min,max);
		while(!FermMethod(Zvalue)) {
			Zvalue=generate(min,max);
		}
		return Zvalue;
	}
	
	RSA(){
		p=genNewPrimeNumber(minFirstValue,maxFirstValue);
		//System.out.println("p = "+p);
		q=genNewPrimeNumber(minSecondValue,maxSecondValue);
		//System.out.println("q = "+q);
		N=p*q;
		//System.out.println("N = "+N);
		//Euler function
		//Используя мультипликативность функции Эйлера f(mn)=f(m)*f(n)
		//и зная, что f(p)=p-1, для простых p, получаим
		fN=(p-1)*(q-1);
		//System.out.println("f(n) = "+fN);
		//public exponent
		e=genPublicExp(fN);
		//System.out.println("public exponent e = "+e);
		//из уравнения e*d mod f(n)=1
		//следует, что d=y, где y
		//x*f(n)+y*e=1
		d=genSecretExp(fN,e);
		//System.out.println("private exponent d = "+d);
	}

	private long genPublicExp(long fN) {
		
		long ee=genNewPrimeNumber(2,fN);
		
		return ee;
	}
	private long genSecretExp(long f,long e) {
		
		long C=GCD(f,e);
		Vector v=GCDExtended(f,e);
		
		
		if(v.getY()<0) {
			return v.getY()+fN;
		}
		return v.getY();
	}
	public PublicKey getPublicKey() {
		return new PublicKey(N,e);
	}
	public DecryptionKey getDecryptionKey() {
		return new DecryptionKey(N,d);
	}
	void print() {
		System.out.println("p = "+p);
		System.out.println("q = "+q);
		System.out.println("N = "+N);
		System.out.println("f(n) = "+fN);
		System.out.println("public exponent e = "+e);
		System.out.println("private exponent d = "+d);
	}
	public static void main(String[] args) {
		
		RSA myrsa=new RSA();
		myrsa.print();
		
		PublicKey mypublickey=myrsa.getPublicKey();
		
		DecryptionKey mydecodkey=myrsa.getDecryptionKey();
		
		String message="Привет";
		System.out.println("Сообщение: "+message);
		String encmessage=mypublickey.encode(message);
		System.out.println("Зашифрованное сообщение: "+encmessage);
		String decmessage=mydecodkey.decode(encmessage);
		System.out.println("Расшифрованное сообщение: "+decmessage);
		
		
	}

}
