Wanted to try and write my own of code to find the "Parker Words".
I saw the video a long time ago. I knew of the existence of recursive algorithms and wanted to use them.
But I was very intimidated by the scale and gave up. After a few 100 problems on Leetcode I decided to give it a try.
First, even though I knew it wouldn't be optimal I implemented a DP solution.
Then after it worked, I decided to use graphs.
So as I was learnt about cliques and the Bron–Kerbosch Algorithm.


Version 1.0: Unoptimized DP and operations

The first version is super basic with small memory optimizations.
It takes about 5 seconds to generate the first solution after exploring almost 34 billion paths.
Never ran it for longer than 1 minute, as it newer found a second solution.
Couldn't deal with duplicates, so I just gave up after getting one valid solution.


Version 1.1: Unoptimized DP bt optimized operations

The second version has only one major change, i covert all words to bitmasks.
Since the list has anagrams this array contained multiple copies.
Solution can theoretically be mapped from the 2 arrays, but I didn't bother.
This one generated the first solution in around a second still after exploring 34 billion paths.
After running it for a minute it gave 3 valid solutions. Never ran it for longer.


Version 1.2: Optimized DP and operations

The third version is properly optimized.
Now anagrams are just ignored.
Though with some minor changes to the hashmap and a new function to handle the creation of all combinations, all answers can be extracted.
But since Matt didn't really bother with them, I decided to just ignore them.
Found the first solution after just 20 million paths.
So I decided to run it till it was done.
It took 05:20:715 to run, and it explored 125,463,222,868 (125B) paths in total.
It found 538 valid solutions.
Don't know if they are the same ones Matt found, but the number matched and all of them are valid, so I'm assuming they are the same.

Added wordle support for version 1 too, it found 23 combinations in 03:38:908.
Again there is no anagram support.
To run the wordle version just use the getWordleArray function instead of the getArray function.


Version 2.0: Graphs using BitSet arrays
 
Realized it would be way faster to bitwise AND N^2 times compared to 2^N times.
So I used a pre-calculated adjacency matrix to store compatibility.
Then I'd just have to find all cliques of size 5.
This also doesn't deal with anagrams.
While the time complexity is still heavily pruned O(N^5).
The basic operation is accessing a BitSet array instead of bitwise Anding 2 integers
If the current path is invalid it just skips the for loop and returns immediately.
Compared to v1 where there would have been an extra function call, a few conditions and then return;
This gave saved huge amounts of time and function calls, (around 25% less calls due to this alone).
But the greatest time save is from the use of bitwise Anding to reduce the possible candidates to be considered hugely wih each layer recursion.
Which means one whole function call is replaced with an AND operation on just two bits.
These two optimizations resulted in a 1000x drop in the number of total function calls (paths).
It found 538 valid solutions in 00:21:471. After exploring 125,219,196 (125M) paths.


Concepts I learnt while trying to solve this problem as fast as possible:

Bitmasks
Bitwise operations
cliques