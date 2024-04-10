# QUESTION
Given an integer array `nums`, find the subarray with the largest sum, and return *its sum*
**Example 1:**
```
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.

```
**Example 2:**
```
Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
```
**Example 3:**
```
Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
```
**Constraints:**
- `1 <= nums.length <= 10^5`
- `10^4 <= nums[i] <= 10^4`
**Follow up:** If you have figured out the `O(n)` solution, try coding another solution using the **divide and conquer** approach, which is more subtle.

# APPROACH
1. My first intuition is dividing the array into 2, index i running from 0 to middle of the array, index j running from end to middle of the array
2. While iterating, we skip negative number, and if we encounter a positive number, we check if sum = A[i] +A[i+1] > 0. If yes, we breaks and mark current index as the startIndex. Same with the end index.
3. Problem of this approach is that the max sum is not always >0, comparing to 0 is incorrect.
4. Remember the question to find the max number in an array, we should compare the current one to the max one found in previous iteration, not with 0
5. To do this, we should initiate a variable to store the max, we call it "maxi". We can start maxi = A[0].
6. As we iterate, we add A[i]  to the sum and compare to maxi. We store the larger value in maxi = max(sum,maxi)
7. Two scenarios can happen when adding A[i]. A[i] < 0 or A[i]> 0. If A[i] <0, it makes sum smaller to the point that sum<0, let say at index j. Here we reset sum = 0. By doing this, we got the max value of all subarrays within (i,j) range. if A[0] <0, sum = A[0] will be reset =0 at the end of the process. By reseting regative sum --> 0, we make sure that sub array in consideration will always be started with a positive integer, never a negative one. 
8. If A[i] >0, it makes the sum larger. This larger value then is stored in maxi. 
9. The most interesting point in this question lies in RESET sum = 0. By doing this, we imaginary divide the array into subparts with at least 1 elements ( either negative or not). A subpart ends when sum of its element <0. At this point, sum is reset to 0 or a new subpart starts with the next element as the first one. 
10. Instead of considering all subarrays starting with any elements of the array, we focus only on **subarrays starting with positive element**. Because the rule here is if the maxArray starts with a negative number, it can always cut out that negative one and start wil the next positive one. Once cut out the first negative number, sum of remaining elements are of course greater than previous sum. 

'''java
public class Solution{
    public int maxSubArray(int[] nums) {
        int maxi = nums[0]; // maximum sum
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > maxi) {
                maxi = sum;
            }
            // If sum < 0: discard the sum calculated
            if (sum < 0) {
                sum = 0;
            }
        }

        // To consider the sum of the empty subarray
        // uncomment the following check:

        //if (maxi < 0) maxi = 0;

        return maxi;
    }}