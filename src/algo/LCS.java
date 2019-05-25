package algo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LCS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Predicate<Object> p=Predicate.isEqual(2).and(Predicate.isEqual(null));
		System.out.println(p.test(2));
	}

}
