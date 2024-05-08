package LearnGFG;

import java.util.ArrayList;
import java.util.Iterator;

public class exercise_getSmallerInteger {
public static void main(String[] args) {
	ArrayList<Integer>al = new ArrayList<>();
	al.add(8);
	al.add(100);
	al.add(20);
	al.add(40);
	al.add(3);
	al.add(7);
	int K = 10;
	System.out.println(solutionIterator(al,K));
	
}

private static String solutionIterator(ArrayList<Integer> al, int k) {
	//iterate through each element
	//if >10, remove || if <10, add to the new list
	Iterator<Integer> it = al.iterator();
	while(it.hasNext()) {
		int x = (Integer)it.next();
		if(x>k) {
			it.remove();
		}
	}
	return al.toString();
	
	
}

}
