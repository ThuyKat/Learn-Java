package LearnGFG;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class IteratorForwardAdd {
public static void main(String[] args) {
	List<Integer>list = new ArrayList<Integer>();
	list.add(10);
	list.add(20);
	list.add(30);
	ListIterator<Integer>It = list.listIterator();
	while(It.hasNext()){
		It.add(5); // add always put new number in the left hand side of the pointer
		// this add function wont work with hasPrevious and previous() --> runtime error
		It.next();
//		System.out.println(It.next());//this trigger the iteration either inside println or out of println
		//if I dont have It.next(), the program stops
	}
	System.out.println(list);
	
}
}
