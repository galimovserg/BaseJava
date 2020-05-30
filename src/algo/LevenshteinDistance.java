package algo;

public class LevenshteinDistance {
	static int getLevenshteinDistance(String str1, String str2){
		int M=str1.length();
		int N=str2.length();
		int[][] d=new int[M+1][N+1];
		d[0][0]=0;
		for(int i=1;i<=M;i++) {
			d[i][0]=i;
			for(int  j=1;j<=N;j++) {
				d[0][j]=j;
				if(str1.charAt(i-1)!=str2.charAt(j-1)) {
					d[i][j]=Math.min(d[i-1][j]+1, Math.min(d[i][j-1]+1,d[i-1][j-1]+1));
				}else {
					d[i][j]=d[i-1][j-1];
				}
			}
		}
		
		for(int i=0; i<d.length;i++) {
			for(int j=0;j<d[0].length;j++) {
				System.out.print(d[i][j]+" ");
			}
			System.out.println();
		}
		
		return d[M][N];
		
	}
	
	static int getLevenshteinDistanceOpt(String str1, String str2){
		int M=str1.length();
		int N=str2.length();
		int[] d=new int[N+1];
		d[0]=0;
		
		for(int i=0;i<=M;i++) {
			int d_1_1=d[0];
			d[0]=i;
			
			for(int  j=1;j<=N;j++) {
				
				if(i==0) {
					d_1_1=d[j];
					d[j]=j;
				}else
				if(str1.charAt(i-1)!=str2.charAt(j-1)) {
					int buf=d[j];
					d[j]=Math.min(d[j]+1, Math.min(d[j-1]+1,d_1_1+1));
					d_1_1=buf;
				}else {
					int buf=d[j];
					d[j]=d_1_1;
					d_1_1=buf;
				}
			}
		}
		
		
		return d[N];
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String str1="переодеться";
		final String str2="переоденется";
		System.out.println("distance between \""+str1+"\" and \""+str2+"\" is "+getLevenshteinDistance(str1,str2));
		System.out.print("distance between \""+str1+"\" and \""+str2+"\" is "+getLevenshteinDistanceOpt(str1,str2));
	}

}
