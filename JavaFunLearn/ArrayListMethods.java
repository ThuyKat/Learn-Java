package LearnGFG;

import java.util.ArrayList;

public class ArrayListMethods {
	//AMORTISED 0(1)
		//add(obj)
	//WORST CASE 0(1)
		//size(); isEmpty();get();set();
	//WORST CASE 0(n)
		//contain();indexOf();lastIndexOf();remove()- both version;add(index,obj);
public static void main(String[] args) {
	ArrayList<String>al = new ArrayList<String>();
	//ADD
	al.add("geeks");
	al.add("ide");
	al.add("courier"); // add always insert item at the end of the array
	System.out.println(al);
	al.add(1,"practice"); // the index is in range [0,size of array]
	// if greater than size, throw IndexOutOfBoundExeption
	System.out.println(al);
	
	//CONTAIN(object) --> true/false
	System.out.println(al.contains("ide"));
	
	// REMOVE(index) --> object : index is in range [0,size of array-1] if out of range throw IndexOutOfBoundException
	al.remove(2);
	System.out.println(al.contains("ide"));
	
	//REMOVE(object) --> true/false
	al.remove("courier");
	System.out.println(al.remove("gfg"));
	System.out.println(al.remove("courier")); // already removed! hence return false
	//GET(index)
	ArrayList<Integer>ali = new ArrayList<Integer>();
	ali.add(10);
	ali.add(20);
	ali.add(30);
	System.out.println(ali.get(0));
	//SET(index,object)
	ali.set(0, 40);
	System.out.println(ali.get(0));
	//INDEXOF(object)
	ali.add(20);
	System.out.println(ali.indexOf(20));
	//LASTINDEXOF(object)
	System.out.println(ali.lastIndexOf(20));
	//CLEAR()
	//ISEMPTY()
	System.out.println(al.isEmpty());
	al.clear();
	System.out.println(al.isEmpty());
	
	
}
}
