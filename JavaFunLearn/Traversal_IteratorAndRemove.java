package LearnGFG;

import java.util.ArrayList;
import java.util.Iterator;

public class Traversal_IteratorAndRemove {
//1 - USING get() - only for List DS
// 2- for each loop
// 3- iterator()
	public static void main(String[] args) {
		ArrayList<Integer>al = new ArrayList<Integer>();
		al.add(10);
		al.add(20);
		al.add(5);
		al.add(30);
		Iterator<Integer> it = al.iterator();
		while(it.hasNext()) {
			int x = (Integer)it.next(); // it.next() return an object, so its reference type. We need to convert it into primitive type
			if(x<10) {
				it.remove();
			}else {System.out.println(x);} // make sure that you only call it.next() once when using Iterator
			// using System.out.println(it.next()) here wont work!!!
		}
// 4- .forEach() 
		System.out.println();
		al.forEach(x -> System.out.println(x));
	}
}
// IF you are trying to remove while traversing using method 1 and 2, you will get concurrent modification exception
//Method 3 can apply for all DS and it wont cause the concurrent modification exception