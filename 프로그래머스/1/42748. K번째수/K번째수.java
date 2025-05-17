import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int L = commands.length;
        int[] answer = new int[L];
        
        for (int i = 0; i < L; i++) {
            int start = commands[i][0]-1;
            int end = commands[i][1]-1;
            int query = commands[i][2]-1;
            
            int[] hubo = new int[end-start+1];
            int index = 0;
            for (int j = start; j <= end; j++) hubo[index++] = array[j];
            
            Arrays.sort(hubo);
            answer[i] = hubo[query];
        }
        return answer;
    }
}