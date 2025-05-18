import java.util.*;
import java.io.*;

// 매개변수탐색 최소값
class Solution {

    static boolean check(long target, int n, int[] times) {
        long cnt = 0;
        for (int i = 0; i < times.length; i++) {
            cnt += (target / times[i]);
            
            if (cnt >= n) return true;
        }
        
        return false;
    }
    
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long answer = 0;
        long left = 0;
        long right = times[times.length-1]*(long)n;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            
            if (check(mid, n, times)) {
                answer = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return answer;
    }
}