import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] nums) {
        int answer = nums.length / 2;
        HashSet<Integer> hs = new HashSet<>();
        for (int num : nums) hs.add(num);
        
        if (answer >= hs.size()) return hs.size();
        return answer;
    }
}