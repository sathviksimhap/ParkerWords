package Version_3;

import Base.Solution;
import com.sun.security.jgss.GSSUtil;

import java.util.*;

public class Main {
    static HashSet<List<Integer>> answer_bitmasks = new HashSet<>();
    static long paths=0L, paths_pruned=0L, solutions=0L;

    public static void main(String[] args){
        char[][] words_array = Solution.getArray();
        HashMap<Integer, List<char[]>> bitmask_to_word = getBitmaskMap(words_array);
        int[] bitmask_set = new int[bitmask_to_word.size()];
        int k=0;
        for(int i: bitmask_to_word.keySet())
            bitmask_set[k++] = i;

        //test code
//        String[] wordList = {
//                "abcde", "fghij", "klmno", "pqrst", "uvwxy",  // first 5 rows
//                "flame", "grape", "honey", "ivory", "jolly",
//                "knife", "lemon", "mango", "noble", "ocean",
//                "piano", "queen", "raven", "stone", "tiger"
//        };
//
//        char[][] mini_words = new char[20][5];
//        for (int i = 0; i < 20; i++)
//            mini_words[i] = wordList[i].toCharArray();
//
//        HashMap<Integer, List<char[]>> mini_map = getBitmaskMap(mini_words);
//        int[] mini_set = new int[mini_map.size()];
//        k=0;
//        for(int i: mini_map.keySet())
//            mini_set[k++] = i;
//
//        int temp=mini_set[19];
//        mini_set[19] = mini_set[18];
//        mini_set[18] = temp;
//
//        System.out.println(Arrays.toString(mini_set));
//
//        for(int i=0; i<mini_set.length; i++)
//            backtrack(i, 0, mini_set, new HashSet<>());
//
//        System.out.println(solutions);
        //test code

        for(int i=0; i<bitmask_set.length; i++)
            backtrack(i, 0, bitmask_set, new ArrayList<>());
    }
    public static void backtrack(int index, int bitmask, int[] bitmask_set, List<Integer> used){

        if(used.size()==5){
            List<Integer> ans = new ArrayList<>(used);
            if(answer_bitmasks.contains(ans))return;
            answer_bitmasks.add(ans);
            System.out.println("Solution bitmasks for solution no:  " + ++solutions);
            System.out.println(ans);
            return;
        }

        paths++;
        if(paths % 1_000_000_000 == 0)
            System.out.println("Tried " + paths + " paths");

        int word = bitmask_set[index];
        if((word & bitmask) != 0) return;
        used.add(word);
        bitmask |= word;

//        System.out.println("path: " + paths + " successful" + " index: " + index + " used.size(): " + used.size() + " used: " + used.toString());

        for(int i=index+1; i<bitmask_set.length; i++)
            backtrack(i, bitmask, bitmask_set, used);
        used.removeLast();
    }
    public static HashMap<Integer, List<char[]>> getBitmaskMap(char[][] words_array){
        HashMap<Integer, List<char[]>> ans = new HashMap<>();
        for(char[] word: words_array){
            int bitmask=0;
            for(char c: word) bitmask |= 1<<(c-'a');
            if(ans.containsKey(bitmask)) ans.get(bitmask).add(word);
            else{
                ans.put(bitmask, new ArrayList<>());
                ans.get(bitmask).add(word);
            }
        }
        return ans;
    }
}
