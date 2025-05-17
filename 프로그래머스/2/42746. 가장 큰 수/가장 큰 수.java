import java.util.*;
import java.io.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(String.valueOf(numbers[i]));
        }
        
        list.sort((l1, l2) -> (l2 + l1).compareTo(l1 + l2));
        
        if (list.get(0).equals("0")) return "0";
            
        for (String l : list) answer.append(l);
        
        return answer.toString();
    }
}