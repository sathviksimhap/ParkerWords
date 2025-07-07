package Version_4;

import Base.Solution;
import java.util.*;

public class CountEdges {
    public static void main(String[] args){
        long edges=0L;
        char[][] words_array=Solution.getArray();
        HashMap<Integer, char[]> bitmask_to_word = getBitmaskMap(words_array);
        int[] bitmask_set = new int[bitmask_to_word.size()];
        int k=0;
        for(int i: bitmask_to_word.keySet())
            bitmask_set[k++] = i;

        for(int i=0; i<bitmask_set.length; i++) {
            for (int j = 0; j < bitmask_set.length; j++) {
                if((bitmask_set[i] & bitmask_set[j]) == 0) edges++;
            }
        }
        System.out.println("Number of edges: " + edges);
    }
    public static HashMap<Integer, char[]> getBitmaskMap(char[][] words_array){
        HashMap<Integer, char[]> ans = new HashMap<>();
        for(char[] word: words_array){
            int bitmask=0;
            for(char c: word) bitmask |= 1<<(c-'a');
            if(!ans.containsKey(bitmask))
                ans.put(bitmask, word);
        }
        return ans;
    }
}
