package Version_1.Version_1_2;

import Base.Solution;
import java.util.*;

public class Main {
    static HashSet<List<Integer>> answer_bitmasks = new HashSet<>();
    static long paths=0L;

    public static void main(String[] args){
        long start = System.currentTimeMillis();

        char[][] words_array = Solution.getArray();
        HashMap<Integer, char[]> bitmask_to_word = getBitmaskMap(words_array);
        int[] bitmask_set = new int[bitmask_to_word.size()];
        int k=0;
        for(int i: bitmask_to_word.keySet())
            bitmask_set[k++] = i;

        for(int i=0; i<bitmask_set.length; i++)
            backtrack(i, 0, bitmask_set, new ArrayList<>());

        List<List<char[]>> all_solutions = new ArrayList<>();
        for(List<Integer> solution: answer_bitmasks){
            List<char[]> answer = new ArrayList<>();
            for(int bitmask: solution)
                answer.add(bitmask_to_word.get(bitmask));
            all_solutions.add(answer);
        }

        for(List<char[]>answer: all_solutions){
            if(!Solution.isValid(answer)){
                System.out.println("Invalid solution submitted: " + answer);
                return;
            }
        }
        long end = System.currentTimeMillis();
        long total_millis = end - start;
        long minutes = total_millis / 60000;
        long seconds = (total_millis % 60000) / 1000;
        long millis = total_millis % 1000;

        System.out.println("All solutions verified");
        System.out.println("Paths explored : " + paths);
        System.out.printf("Total time: %02d:%02d:%03d\n", minutes, seconds, millis);
        System.out.println("NUmber of unique solutions: " + all_solutions.size());

        int solutions = 0;
        for (List<char[]> solution : all_solutions) {
            System.out.print("Solution #" + ++solutions + " :");
            for (char[] word : solution) {
                System.out.print(" " + new String(word));
            }
            System.out.println();
        }
    }
    public static void backtrack(int index, int bitmask, int[] bitmask_set, List<Integer> used){

        if(used.size()==5){
            List<Integer> ans = new ArrayList<>(used);
            if(answer_bitmasks.contains(ans))return;
            answer_bitmasks.add(ans);
            return;
        }
        paths++;

        int word = bitmask_set[index];
        if((word & bitmask) != 0) return;
        used.add(word);
        bitmask |= word;

        for(int i=index+1; i<bitmask_set.length; i++)
            backtrack(i, bitmask, bitmask_set, used);
        used.removeLast();
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
