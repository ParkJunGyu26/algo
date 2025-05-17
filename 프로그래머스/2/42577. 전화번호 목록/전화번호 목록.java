import java.util.*;
import java.io.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashMap<String, Integer> hs = new HashMap<>();
        Arrays.sort(phone_book);
        
        for (int i = 0; i < phone_book.length; i++) {
            StringBuilder st = new StringBuilder();
            for (int j = 0 ; j < phone_book[i].length(); j++) {
                st.append(phone_book[i].charAt(j));
                if (hs.containsKey(st.toString())) return false;
            }
            String change_st = st.toString();
            hs.put(change_st, 1);
        }
        return true;
    }
}