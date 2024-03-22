package be6_day4;

import java.util.Arrays;

public class fourseason {
	public static void main(String[] args) {
		int [] T = {-3,-14,-5,7,8,42,8,3};
		System.out.println("Season that has highest amplitude is " + maxAmpSeason(T));
	}
	
// function to find the season with highest amplitude of temperature
	public static String maxAmpSeason(int[] T ) {
		
		int maxAmp = 0; 
		String[] season = {"Winter", "Spring","Summer","Autumn"};
		
		int n = T.length/4;
		// Group of T's indexes representing each season are:
			// 0 --> n-1, n --> 2n-1, 2n--> 3n-1, 3n--> 4n-1
		// In each group, we find the season's max, min and calculate amplitude = max - min 		
		int[] eachSeasonTemp = new int[n]; 
		int k = 0;
		int index = 0;
		for (int j = 0; j<4; j++) {	
			for (int i = j*n; i < (j+1)*n ; i++) {
				eachSeasonTemp[k] = T[i]; // array to contain the season's temperature
				k ++;}
			k = 0;
			// find min and max temperatures
			int min = eachSeasonTemp[0];
			int max = 0;
			
			for (int x : eachSeasonTemp) {
				if (x < min) { min = x;}
				else if (x> max) {max =x;}
			}
			// calculate the amplitude
			int amp = max -min;
			if (amp> maxAmp) {
				maxAmp = amp;
				index = j; 
			}				
		
	}
		
		return season[index];
		
	
}
}
