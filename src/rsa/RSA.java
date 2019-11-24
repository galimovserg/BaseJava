package rsa;

public class RSA {
	
	private long p;
	private long q;
	private long N;
	private long fN;
	private long e;
	private long d;
	
	private static final long minP=40;
	private static final long maxP=80;
	private static final long minQ=70;
	private static final long maxQ=120;
	
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
		public int[] decode(int[] m) {
			int result[]=new int[m.length];
			for(int i=0;i<m.length;i++) {
				result[i]=(int) fastpow(m[i],d,n);
			}
			return result;
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
		p=genNewPrimeNumber(minP,maxP);
		
		q=p;
		while(q==p) {
			q=genNewPrimeNumber(minQ,maxQ);
		}
		N=p*q;
		//Euler function
		//�?спользуя мультипликативность функции Эйлера f(mn)=f(m)*f(n)
		//и зная, что f(p)=p-1, для простых p, получаим
		fN=(p-1)*(q-1);
		//public exponent
		e=genPublicExp(fN);
		//из уравнения e*d mod f(n)=1
		//следует, что d=y, где y
		//x*f(n)+y*e=1
		d=genSecretExp(fN,e);
		
	}
	RSA(long pp, long qq, long ee){
		p=pp;
		q=qq;	
		N=p*q;
		//Euler function
		//�?спользуя мультипликативность функции Эйлера f(mn)=f(m)*f(n)
		//и зная, что f(p)=p-1, для простых p, получаим
		fN=(p-1)*(q-1);
		//public exponent
		e=ee;
		//из уравнения e*d mod f(n)=1
		//следует, что d=y, где y
		//x*f(n)+y*e=1
		d=genSecretExp(fN,e);
	}
	private long genPublicExp(long fN) {
		long ee=genNewPrimeNumber(2,fN);
		while(fN%ee==0) {
			ee=genNewPrimeNumber(2,fN);
		}
		
		
		return ee;
	}
	private long genSecretExp(long f,long e) {
		
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
	void printConsole() {
		System.out.println("p = "+p);
		System.out.println("q = "+q);
		System.out.println("N = "+N);
		System.out.println("f(n) = "+fN);
		System.out.println("public exponent e = "+e);
		System.out.println("private exponent d = "+d);
	}
	
	public static long f(long x,long n) {
		long res=(x*x-1)%n;
		
		return res;
	}
	/**
	 * ро метод Полларда
	 * @return
	 */
	public static long pollard(long n) {
		//long j=1;
		long x=generate(1,n-2);
		long y = 1; long i = 0; int stage = 2;
		   while (GCD(Math.abs(x - y),n) == 1)
		   {
		     if (i == stage){
		       y = x;
		       stage = stage*2; 
		     }
		     x = f(x,n);
		     i = i + 1;
		   }
		   return GCD(Math.abs(x-y),n);
		
	}
	private static final int maxraunds=20;
	/**
	 * Факторизация методом Полларда
	 * @param n
	 * @return
	 */
	public static long factorPollard(long n) {
		//pollard может не найти делитель с первого раза
		//поэтому будем повторять несколько раз
		//для разных значений начальных x
		for(int i=0;i<maxraunds;i++) {
			long d=pollard(n);
			if(d<n) {
				return d;
			}
		}
		return 1;
	}
	
	/**
	 * Проверяет правильность работы RSA
	 * @param text
	 * @return
	 */
	public static boolean testRSA(String text) {
		
		RSA myrsa=new RSA();
		
		PublicKey mypublickey=myrsa.getPublicKey();
		DecryptionKey mydecodkey=myrsa.getDecryptionKey();
		//шифрует а затем расшифровывает
		String enc=mydecodkey.decode(mypublickey.encode(text));
		//если входная строка соответсвует расшифрованной,
		//то все работает верно
		//иначе выводим содержимое RSA в консоль
		//и возвращаем false
		if(!text.equals(enc)) {
			myrsa.printConsole();
		}
		
		return text.equals(enc);
	}
	public static void main(String[] args) {
		
		RSA myrsa=new RSA();
		myrsa.printConsole();
		
		PublicKey mypublickey=myrsa.getPublicKey();
		
		DecryptionKey mydecodkey=myrsa.getDecryptionKey();
		
		String message="Привет";
		System.out.println("Сообщение: "+message);
		String encmessage=mypublickey.encode(message);
		System.out.println("Зашифрованное сообщение: "+encmessage);
		String decmessage=mydecodkey.decode(encmessage);
		System.out.println("Расшифрованное сообщение: "+decmessage);
		System.out.println(GCD(8,2));
	
		System.out.println("fallen RSA tests:");
		int i=0;
		while(testRSA("1")&&i<200) {
			i++;
		}
		
		System.out.println("fallen Pollard tests:");
		long p=genNewPrimeNumber(100,300);
		long q=genNewPrimeNumber(100,300);
		long ntest= p*q;
		long factor=factorPollard(ntest);
		i=0;
		while(i<100) {
			i++;
			if((ntest/factor==p ||ntest/factor==q)&&(p!=ntest)&&(q!=ntest)) {
				//System.out.println("p = "+p+" q = "+q+" factor = "+factor);
			}else {
				System.out.println("p = "+p+" q = "+q+" factor = "+factor);
			}
			p=genNewPrimeNumber(100,300);
			q=genNewPrimeNumber(100,300);
			ntest= p*q;
			factor=factorPollard(ntest);
		}
		
		
		System.out.println("Hacking...");
		
		long nn=150737;
		long pp=factorPollard(nn);
		long qq=nn/pp;
		long ee=113;
		
		RSA hack = new RSA(pp,qq,ee);
		DecryptionKey dechacker=hack.getDecryptionKey();
		int[] M={104318, 143945, 19327, 69783, 112451, 105094};
		int[] result = dechacker.decode(M);
		for(int c=0;c<result.length;c++) {
			System.out.print(result[c]+" ");
		}
	}

}
