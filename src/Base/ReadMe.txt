Wanted to try and write my own of code to find the "Parker Words".

The first version is super basic with small memory optimizations.
It takes about 5 seconds to generate the first solution after exploring almost 34 billion paths.
Never ran it for longer than 1 minute, as it newer found a second solution.
Couldn't deal with duplicates, so I just gave up after getting one valid solution.

The second version has only one major change, i covert all words to bitmasks.
Since the list has anagrams this array contained multiple copies.
Solution can theoretically be mapped from the 2 arrays, but I didn't bother.
This one generated the first solution in around a second still after exploring 34 billion paths.
After running it for a minute it gave 3 valid solutions. Never ran it for longer.

The third version is properly optimized.
Now anagrams are just ignored.
Though with some minor changes to the hashmap and a new function to handle the creation of all combinations, all answers can be extracted.
But since Matt didn't really bother with them, I decided to just ignore them.
Found the first solution after just 20 million paths.
So I decided to run it till it was done.
It took 05:20:715 to run
And it explored 125,463,222,868 (125B) paths in total.
It found 538 valid solutions.
Don't know if they are the same ones Matt found, but the number matched and all of them are valid, so I'm assuming they are the same.

Added a wordle version too, it found 23 combinations in 03:38:908.
Again there is no anagram support.
To run the wordle version just use the getWordleArray function instead of the getArray function.