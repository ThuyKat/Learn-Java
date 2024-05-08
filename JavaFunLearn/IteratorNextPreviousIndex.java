package LearnGFG;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class IteratorNextPreviousIndex {
	public static void main(String[] args) {

		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(20);
		list.add(30);
		ListIterator<Integer> It = list.listIterator(2);
		
		System.out.println(It.previousIndex());
		System.out.println(It.nextIndex());

	}
}
