package be6_day7;

public class ticket {
public static void main(String[] args) {
	int [] A = {1,2,4,5,7,29,30}; // A is sorted in asc order, elements are distinct
	
	System.out.println(solution(A));
}

public static int solution(int [] A) {
	
	// 1 day : $2
	//7 day:$7
	//30day :$25
	
	int dailyTicket =0; // number of daily ticket
	int weeklyTicket =0; // number of weekly ticket
	// price that works with  all type arrays: price 1 = A.length *2 and price 2 = $25
	// the minPrice needs to be smaller than this
	int minPrice = Math.min(A.length*2,25);
	// initiate an array to count number of weekly traveling days
	int [] streaks = new int[4];
	for(int i =0; i<A.length; i++) {
		int index = (int)Math.floor(A[i]/8); 
		streaks[index]++;
	}
	// if within a week, no of traveling day >3 --> it's cheaper to take weekly ticket
	// if within a week, no of traveling day > 0 and <3 --> we can take daily ticket
	for (int j =0;j<streaks.length;j++) {
		if(streaks[j]>3) {
			weeklyTicket++;
		}else if(streaks[j]>0) {dailyTicket = dailyTicket+ streaks[j];}
	}
	
	int sum = weeklyTicket *7+dailyTicket*2;
	minPrice = Math.min(minPrice, sum);
	return minPrice;
}

}
