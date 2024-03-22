# Four Season Amplitude - How to solve
*The assumptions*
1. Array T of N integers is given. T contains temperatures of all seasons of a year.
2. A year last N days, every season lasts exact N/4 days.Order of seasons are: Winter, Spring, Summer and Autumn
3. All seasons within a year have different amplitudes = max temperature - min temperature
*The tasks*
**Write function to return the name of the season with the highest temperature amplitude.**
Solutions: 
1. Divide the array into 4 parts, each part has n = N/4 numbers. As a result, the index of members of each part will be: 
- Part 1: 0 -->n-1
- Part 2: n -->2n-1
- Part 3: 2n -->3n-1
- Part 4: 3n -->4n-1
In order to achieve this division, we will use a loop with running index *j* from 0 -->4. Inside this loop we will create another loop with index starting from i = n x j. The second loop will iterate through members of each part and break at the end of the part when i = n(j+1) -1. 
```java
public static String maxAmpSeason(int[] T ) {
		
		int maxAmp = 0; 
        int index = 0;
		String[] season = {"Winter", "Spring","Summer","Autumn"};
		int n = T.length/4; 		
		int[] eachSeasonTemp = new int[n]; 
		int k = 0;
		for (int j = 0; j<4; j++) {	
			for (int i = j*n; i < (j+1)*n ; i++) {
				eachSeasonTemp[k] = T[i]; // array to contain the season's temperature
				k ++;}
			k = 0;
```
2. In each part of T, now we can examine each member to find max and min temperatures and use them to calculate the amplitude 
To achive this, while looping throgh each member of the part, we store them in an array called *eachSeasonTemp*. After update this array with all member of the part, we loop through this array to find the min, the max and the amplitude.
```java
// find min and max temperatures
			int min = eachSeasonTemp[0];
			int max = 0;
			
			for (int x : eachSeasonTemp) {
				if (x < min) { min = x;}
				else if (x> max) {max =x;}
			}
			// calculate the amplitude
			int amp = max -min;
```
3. Now we have the amplitude of the part, the last task is to find the max amplitude of the year and print out the name of the correspondent season. 
To achieve this,we will compare calculated *amp* value with the value of variable *maxAmp* declared at the beginning of the program. If the value of amp > maxAmp, we update maxAmp = amp and update variable *index*-- index = j. This index will be used to locate the name of the season in the Arrays of String named *season*
```java
if (amp> maxAmp) {
				maxAmp = amp;
				index = j; 
			}					
	}
		return season[index];
```
