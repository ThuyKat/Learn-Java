1. Price = 2*a + 7*b + 30*c with a, b,c is number of daily, weekly, monthly tickets respectively
2. Two options that works with all arrays: option 1: take all daily tickets P1 = A.length *2; option 2: take 1 monthly ticket P2=25
3. The task in focus now is finding the consecutive day streak
4. Price of 3 single travel is almost equal to weekly price, so if consecutive day streak is >3, better taking weekly ticket
5. How can we slide the array to find the streak?
6. Idea: A[0] to A[i], count all A[i] that A[i] < A[0]+6
- if count>3 then we have a weekly ticket
- if count<3 then we have daily tickets
7. total of weekly and daily tickets, if this total > min(P1,P2) then we take min(P1,P2) only