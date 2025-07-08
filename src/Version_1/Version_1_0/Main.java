package Version_1.Version_1_0;
import Base.Solution;

import java.util.*;

public class Main {
    static char[][] words_array;
    static long pathsTried = 0L, solutions=0L;
    static boolean found = false;

    public static void main(String[] args){
        words_array = Solution.getArray();
        for(int i=0; i<words_array.length; i++)
            backtrack(i, words_array[i], 0, new ArrayList<>());
    }
    public static void backtrack(int ind, char[] word, int bool_arr, List<char[]> words_used){
//        if(found) return;

        pathsTried++;
        if (pathsTried % 1_000_000_000 == 0) System.out.println("Tried " + pathsTried + " paths");

        if(words_used.size()==5){
            System.out.println("Solution: " + ++solutions);
            for(char[] word_in_list: words_used)
                System.out.println(word_in_list);
            System.out.println("Paths tried before getting solution: " + pathsTried);
            System.out.println("Solution is valid?: " + Solution.isValid(words_used));
            found=true;
            return;
        }
        int checker = bool_arr;
        for(char c: word){
            if ((checker & (1 << (c - 'a'))) != 0) return;
            else checker |= 1 << (c - 'a');
        }
        bool_arr=checker;
        words_used.add(word);
        for(int i=ind+1; i<words_array.length; i++)
            backtrack(i, words_array[i], bool_arr, words_used);
        words_used.removeLast();
    }
}
