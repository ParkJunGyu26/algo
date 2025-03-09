import java.util.*;
import java.io.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        boolean check = false;
        if (s.charAt(0) == '-') {
            check = true;
            s = s.substring(1);
        }
        
        answer = Integer.parseInt(s);
        if (check) {
            answer *= -1;
        }
        
        return answer;
    }
}