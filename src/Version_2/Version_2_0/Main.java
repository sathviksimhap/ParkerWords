package Version_2.Version_2_0;

import Base.Solution;
import java.util.*;

public class Main {

    public static List<List<Integer>> solutions = new ArrayList<>();
    public static long paths = 0L;

    public static void main(String[] args){
        long start = System.currentTimeMillis();

        char[][] words_array = Solution.getArray();
        HashMap<Integer, char[]> bitmask_to_word = getBitmaskMap(words_array);

        int[] bitmask_set = new int[bitmask_to_word.size()];
        int k=0;
        for(int i: bitmask_to_word.keySet())
            bitmask_set[k++] = i;

        BitSet[] graph = getGraph(bitmask_set);
        solve(graph);

        List<List<char[]>> answers = getAnswers(bitmask_set, bitmask_to_word);
        for(List<char[]>answer: answers) {
            if (!Solution.isValid(answer)) {
                System.out.println("Invalid solution submitted: " + answer);
                return;
            }
        }
        long end = System.currentTimeMillis();
        String time_taken = timeTaken(start, end);

        int sols = 0;
        for (List<char[]> sol : answers) {
            System.out.print("Solution " + ++sols + " :");
            for (char[] word : sol)
                System.out.print(" " + new String(word));
            System.out.println();
        }
        System.out.println("Found " + answers.size() + " valid solutions in " + time_taken + ". After exploring " + String.format("%,d", paths) + " paths.");
    }
    public static void solve(BitSet[] graph){
        for(int u=0; u<graph.length; u++){
            List<Integer> clique = new ArrayList<>();
            clique.add(u);

            BitSet cand = (BitSet) graph[u].clone();

            findClique(clique, cand, graph);
        }
    }
    public static void findClique(List<Integer> clique, BitSet cand, BitSet[] graph){
        if(clique.size()==5){
            solutions.add(new ArrayList<>(clique));
            return;
        }
        paths++;

        for(int v=cand.nextSetBit(0); v>=0; v=cand.nextSetBit(v+1)){
            List<Integer> new_clique = new ArrayList<>(clique);
            new_clique.add(v);

            BitSet new_cand = (BitSet) cand.clone();
            new_cand.and(graph[v]);

            findClique(new_clique, new_cand, graph);
        }
    }
    //Pre_processing Functions
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
    public static BitSet[] getGraph(int[] bitmask_set){
        int l = bitmask_set.length;
        BitSet[] bitset = new BitSet[l];
        for(int i=0; i<l; i++) bitset[i] = new BitSet(l);

        for(int i=0; i<l; i++)
            for(int j=i+1; j<l; j++)
                if((bitmask_set[i] & bitmask_set[j]) == 0)
                    bitset[i].set(j);

        return bitset;
    }
    //Post-processing Functions
    public static List<List<char[]>> getAnswers(int[] bitmask_set, HashMap<Integer, char[]> bitmask_to_word) {
        List<List<char[]>> answers = new ArrayList<>();
        for (List<Integer> solution : solutions) {
            List<char[]> answer = new ArrayList<>();
            for (int i : solution)
                answer.add(bitmask_to_word.get(bitmask_set[i]));
            answers.add(answer);
        }
        return answers;
    }
    public static String timeTaken(long start, long end){
        long total_millis = end - start;
        long minutes = total_millis / 60000;
        long seconds = (total_millis % 60000) / 1000;
        long millis = total_millis % 1000;
        return String.format("%02d:%02d:%03d", minutes, seconds, millis);
    }
}
