package LearnGFG;

import java.util.ArrayList;

public class ArrayListVSArray {
public static void main(String[] args) {
	ArrayList<Integer>al = new ArrayList<Integer>();
	// be sure of how ArrayList internally work
	//ensure capacity method: as if you are preparing for a party and your client tell you estimated capacity of 100 guest
	al.ensureCapacity(100);
	//Amortized time complexity: if we want to add the 101th guest, it will be a costly operation. Eventhough averange time complexity is 
	// (O(1) * n + O(n)) /(n+1) = O(1)
	// Its better to specify the size of the operation to avoid this costly operation
}
}
