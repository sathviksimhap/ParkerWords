package Version_4;

import Base.Solution;
import java.util.*;

public class Main {

    public static List<List<Integer>> solution = new ArrayList<>();
    public static long paths = 0L, solutions = 0L;

    public static void main(String[] args){

        char[][] words_array = Solution.getArray();
        HashMap<Integer, char[]> bitmask_to_word = getBitmaskMap(words_array);

        int[] bitmask_set = new int[bitmask_to_word.size()];
        int k=0;
        for(int i: bitmask_to_word.keySet())
            bitmask_set[k++] = i;

        BitSet[] graph = getGraph(bitmask_set);
        solve(graph);

        //temp output
        for(List<Integer> sol: solution){
            for(int s: sol)
                System.out.print(Arrays.toString(bitmask_to_word.get(bitmask_set[s])) + " ");
            System.out.println();
        }
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

//        if(clique.size() == 2) System.out.println(2);
        if(clique.size()==5){
            solution.add(new ArrayList<>(clique));
            System.out.println("Solutions found: " + ++solutions);
            return;
        }
        paths++;
        if(paths % 10_000_000 == 0)
            System.out.println("Paths tried: " + paths);

        for(int v=cand.nextSetBit(0); v>=0; v=cand.nextSetBit(v+1)){
            List<Integer> new_clique = new ArrayList<>(clique);
            new_clique.add(v);

            BitSet new_cand = (BitSet) cand.clone();
            new_cand.and(graph[v]);

            findClique(new_clique, new_cand, graph);
        }
    }
    public static boolean canAdd(List<Integer> clique, int v, BitSet[] graph){
        for(int u: clique)
            if(!graph[u].get(v)) return false;
        return true;
    }
    //pre processing functions
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

        for(int i=0; i<l; i++){
            for(int j=i+1; j<l; j++){
                if((bitmask_set[i] & bitmask_set[j]) == 0)
                    bitset[i].set(j);
            }
        }
        return bitset;
    }
}
/*

[3058, 3064, 3316, 4275, 5337]

        base alg:
        func search(clique, cands):
            for n in cands
            if can add n to clique:
                new_cand = cand intersection neighbors of n
                search(clique+n, new_cand)
         */