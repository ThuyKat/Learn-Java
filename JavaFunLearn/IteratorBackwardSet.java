package LearnGFG;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class IteratorBackwardSet {
	public static void main(String[] args) {
		List<Integer>list = new ArrayList<Integer>();
		list.add(10);
		list.add(20);
		list.add(30);
		ListIterator<Integer>It = list.listIterator(list.size()); // size = 3
		while(It.hasPrevious()){
			int x = It.previous();
			It.set(x*2);
			
		}
		System.out.println(list);
		
	}
}
