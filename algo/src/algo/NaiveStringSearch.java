package algo;

import java.util.ArrayList;

public class NaiveStringSearch {
	/**
	 * Производит поиск образца в строке прямым способом(в лоб)
	 * @param text
	 * @param pattern
	 * @return
	 */
	static ArrayList<Integer> find(char[] text,char[] pattern) {
		ArrayList<Integer> answers = new ArrayList<Integer>() ;
		int N=text.length;
		int M=pattern.length;
		int f=0;
		for(int i=0;i<N-M+1;i++) {
			f=0;
			for(int j=0;j<M;j++) {
				if(pattern[j]!=text[i+j]) {
					f=1;
					break;
				}
			}
			if(f==0) {
				answers.add(i);
			}
		}
		return answers;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String P="al";
		String T="algalgogoloalgoria";
		ArrayList<Integer> A=find(T.toCharArray(),P.toCharArray());
		for(int i=0;i<A.size();i++) {
			System.out.println("Образец найден со сдвигом "+A.get(i));
		}
	}

}
