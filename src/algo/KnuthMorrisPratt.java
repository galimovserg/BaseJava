package algo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class KnuthMorrisPratt {

	static int[] prefix(char[] sx) {
		int p[]=new int[sx.length];
		p[0]=0;
		int j=0;
		int i=1;
		while(i<sx.length) {
			if(sx[i]==sx[j]) {
				p[i]=j+1;
				i++;
				j++;
			}else {
				if(j== 0){
					p[i]=0;
					i++;
				}else {
					j=p[j-1];
				}
			}
		}
		return p;
	}
	/**
	 * Поиск всех вхождений pattern в text
	 * алгоритмом Кнута-Морриса-Пратта
	 * @param text
	 * @param pattern
	 * @return
	 */
	static ArrayList<Integer> find(char[] text,char[] pattern) {
		int p[]=prefix(pattern);
		ArrayList<Integer> answers = new ArrayList<Integer>() ;
		int l=0;
		int k=0;
		
		while(l<text.length) {
			if(pattern[k]==text[l]) {
				k++;
				l++;
				if(k==pattern.length ) {
					answers.add(l-pattern.length);
					k=p[k-1];
				}
			}else {
				if(k==0) {
					l++;
					
				}else {
					k=p[k-1];
				}
			}
		}
		return answers;
	}
	static char[] open(String fname) {
		File f = new File(fname);
		char[] CB = new char[(int) f.length()];
		Reader reader;
		try {
			reader = new InputStreamReader(new FileInputStream(f), "cp1251");
			try {
				reader.read(CB);
				//System.out.println(CB);
				return CB;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

		   
	}
	public static void FindFromFileAndPrint(String pattern,String path) {
		char[] text=open(path);
		ArrayList<Integer> answers=find(text,pattern.toCharArray());
		for(int m : answers) {
			int pos=m;
			String t="";
			while(text[pos]!='.'&&text[pos]!='?'&&text[pos]!='!'&&text[pos]!='\n'&&pos>0) {
				t=text[pos]+t;
				pos--;
				
			}
			int low=pos;
			pos=m+1;
			while(text[pos]!='.'&&text[pos]!='?'&&text[pos]!='!'&&text[pos]!='\n'&&pos<text.length-1) {
				t=t+text[pos];
				pos++;
			}
			int high=pos;
			System.out.println("----------------------------------------------------------------");
			System.out.println("Образец присутствует в строке со смещением "+m);
			System.out.println(t);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindFromFileAndPrint("что-нибудь","file1.txt");
		
	}

}
