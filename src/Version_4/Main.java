package Version_4;

import Base.Solution;
import java.util.*;

public class Main {
    public static List<List<Integer>> solution = new ArrayList<>();
    public static long paths = 0L, solutions = 0L;
    public static void main(String[] args){
        char[][] words_array = Solution.getArray();
        int[] bitmask_set = getBitmaskSet(words_array);
        BitSet[] graph = getGraph(bitmask_set);
        solve(graph);
    }
    public static void solve(BitSet[] graph){
        for(int u=0; u<graph.length; u++){
            List<Integer> clique = new ArrayList<>();
            clique.add(u);
            BitSet cand = graph[u];
            findClique(clique, cand, graph);
        }
    }
    public static void findClique(List<Integer> clique, BitSet cand, BitSet[] graph){
        if(clique.size()==5){
            solution.add(clique);
            System.out.println("Solutions found: " + ++solutions);
            return;
        }
        paths++;
        if(paths % 10_000_000 == 0)
            System.out.println("Paths tried: " + paths);

        for(int v=cand.nextSetBit(0); v>=0; v=cand.nextSetBit(v+1)){
            if(canAdd(clique, v, graph)){
                clique.add(v);
                BitSet new_cand = (BitSet) cand.clone();
                new_cand.and(graph[v]);
                findClique(clique, new_cand, graph);
                clique.removeLast();
            }
        }
    }
    public static boolean canAdd(List<Integer> clique, int v, BitSet[] graph){
        for(int u: clique)
            if(!(graph[u].get(v))) return false;
        return true;
    }
    //pre processing functions
    public static int[] getBitmaskSet(char[][] words_array){
        HashSet<Integer> set = new HashSet<>();
        for(char[] word: words_array){
            int bitmask=0;
            for(char c: word) bitmask |= 1 << c - 'a';
            set.add(bitmask);
        }
        int[] arr = new int[set.size()];
        int k=0;
        for(int i: set) arr[k++] = i;

        return arr;
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
        base alg:
        func search(clique, cands):
            for n in cands
            if can add n to clique:
                new_cand = cand intersection neighbors of n
                search(clique+n, new_cand)
         */