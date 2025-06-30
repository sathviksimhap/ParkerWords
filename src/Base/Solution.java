package Base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static boolean isValid(List<char[]> words_used){
        int check=0;
        for(char[] word: words_used){
            for(char c: word){
                if((check & 1 << (c - 'a')) != 0) return false;
                else check |= 1 << (c - 'a');
            }
        }
        return true;
    }
    public static char[][] getArray(){
        List<char[]> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\sride\\IdeaProjects\\Anagram\\src\\Base\\words_alpha.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.length()==5){
                    boolean[] unique_word = new boolean[26];
                    boolean skip=false;
                    for(int i=0; i<5; i++){
                        if(unique_word[(int) line.charAt(i) - 'a']) skip=true;
                        else unique_word[(int) line.charAt(i) - 'a'] = true;
                    }
                    if(!skip) list.add(line.toCharArray());
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list.toArray(new char[0][]);
    }
}
