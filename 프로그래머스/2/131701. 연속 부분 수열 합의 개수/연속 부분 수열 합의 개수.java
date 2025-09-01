import java.io.*;
import java.util.*;

// 슬라이딩 윈도우 * N
class Solution {
    ArrayList<Integer> list;
    HashSet<Integer> hs;
    
    public int solution(int[] elements) {
        int answer = 0;
        int n = elements.length;
        
        hs = new HashSet<>();
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(elements[i]);
        for (int i = 0; i < n; i++) list.add(elements[i]);
        
        int m = list.size();
        for (int i = 1; i <= n; i++) { // 슬라이딩 윈도우 크기
            ArrayDeque<Integer> dq = new ArrayDeque<>();
            
            int totalSum = 0;
            for (int k = 0; k < i; k++) {
                dq.addLast(elements[k]);
                totalSum += elements[k];
            }
            
            hs.add(totalSum);
            for (int j = i; j < m; j++) {
                totalSum -= dq.removeFirst();
                int num = elements[j % n];
                
                dq.addLast(num);
                totalSum += num;
                hs.add(totalSum);
            }
        }
        
        return hs.size();
    }
}