// Given an ArrayList arr of N positive integers. The task is to find the maximum for every adjacent pairs in the ArrayList.

// Example 1:

// Input:
// 6
// 1 2 2 3 4 5

// Output:
// 2 2 3 4 5


SOLUTION: 
public static ArrayList<Integer> maxNeighbour(ArrayList<Integer>arr)
{
   ArrayList<Integer>result = new ArrayList<>();
    for(int i = 0;i<arr.size()-1;i++){
        if(i+1<arr.size()){
        int x = Math.max(arr.get(i),arr.get(i+1));
        result.add(x);
        }else{
        int x = arr.get(i);
        result.add(x);
        }
    }
    
    return result;
}
