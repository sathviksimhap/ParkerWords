package Version_1.Version_1_1;
import Base.Solution;
import java.util.*;

public class Main {
    static long solutions = 0L, paths=0L;
    static int size;
    static boolean found = false;
    static int[] answer = new int[5];

    public static void main(String[] args){
        char[][] words_array = Solution.getArray();
        size = words_array.length;
        int [] bitmask_array = getBitmaskArray(words_array);

        for(int i=0; i<size; i++)
            backtrack(i, 0, bitmask_array, new ArrayList<>());

        List<char[]> check_list = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            String s = new String(words_array[answer[i]]);
            System.out.println(s);
            check_list.add(words_array[answer[i]]);
        }
        System.out.println("Solution valid?: " + Solution.isValid(check_list));
    }
    public static void backtrack(int index, int bitmask, int[] bitmask_array, List<Integer> indices_used){
        if(found) return;

        if(indices_used.size()==5){
            System.out.println("Solution: " + ++solutions);
            found=true;
            for(int i=0; i<indices_used.size(); i++)
                answer[i]=indices_used.get(i);
            return;
        }
        paths++;
        if(paths%1_000_000_000==0)
            System.out.println("Tried " + paths + " paths");

        if((bitmask & bitmask_array[index]) != 0)
            return;

        indices_used.add(index);
        bitmask |= bitmask_array[index];

        for(int i=index+1; i<size; i++)
            backtrack(i, bitmask, bitmask_array, indices_used);

        indices_used.removeLast();

    }
    public static int[] getBitmaskArray(char[][] words_array){
        int[] res = new int[words_array.length];
        int k=0;
        for(char[] word: words_array){
            int bitmask=0;
            for(char c: word){
                bitmask |= 1<<(c - 'a');
            }
            res[k++] = bitmask;
        }
        return res;
    }
}
