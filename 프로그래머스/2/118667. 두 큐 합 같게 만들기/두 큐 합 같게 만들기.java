import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int n = queue1.length;
        int m = queue2.length;
        int[] mergeQueue = new int[n+m];
        
        long total = 0L;
        for (int i = 0; i < n; i++) {
            mergeQueue[i] = queue1[i];
            total += queue1[i];
        }
        for (int i = 0; i < m; i++) {
            mergeQueue[n+i] = queue2[i];
            total += queue2[i];
        }
        
        int left = 0;
        int right = n-1;
        long nowSum = 0;
        for (int i = left; i <= right; i++) nowSum += mergeQueue[i];
        
        while (left <= right && right < (n+m-1)) {
            if (nowSum == total/2) return answer;
            
            answer++;
            if (nowSum < (total / 2)) {
                nowSum += mergeQueue[++right];
            } else if (nowSum > (total / 2)) {
                nowSum -= mergeQueue[left++];
            }
        }
        
        return -1;
    }
}