import java.util.*;

// 투 포인터
class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        answer[0] = 0;
        answer[1] = 1_000_001;
        
        int n = sequence.length;
        
        int left = 0;
        int right = 0;
        
        int totalSum = sequence[left];
        while (left <= right && right < n) {
            
            if (totalSum > k) {
                totalSum -= sequence[left++];
            } else {
                if (totalSum == k) {
                    if (answer[1] - answer[0] > right - left) {
                        answer[0] = left;
                        answer[1] = right;
                    }
                    
                }
                
                if (right == (n-1)) return answer;
                
                totalSum += sequence[++right];
            }
        }
        
        return answer;
    }
}