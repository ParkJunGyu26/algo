import java.util.*;
import java.io.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        StringBuilder st = new StringBuilder();
        
        HashMap<String, Integer> par = new HashMap<>();
        
        for (String p : participant) {
            if (!par.containsKey(p)) par.put(p, 1);
            else {
                int plus = par.get(p) + 1;
                par.put(p, plus);
            }
        }
        
        for (String c : completion) {
            int minus = par.get(c)-1;
            par.put(c, minus);
        }
        
        for (String p : participant) {
            int num = par.get(p);
            if (num > 0) {
                st.append(p);
                break;
            }
        }
        
        return st.toString();
    }
}