import java.util.*;

class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        long num = x;  // int를 long으로 변환
        
        for (int i = 0; i < n; i++) {
            answer[i] = num;
            num += x;  // x만큼씩 증가
        }
        
        return answer;
    }
}