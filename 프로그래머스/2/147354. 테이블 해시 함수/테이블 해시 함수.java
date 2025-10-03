import java.util.*;
import java.io.*;

class Solution {
    int n, m;
    ArrayList<ArrayList<Integer>> list;
    int[] newArr;
    
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        n = data.length;
        m = data[0].length;
        newArr = new int[n];
        Arrays.fill(newArr, 0);
        
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
            for (int j = 0; j < m; j++) list.get(i).add(data[i][j]);
        }
        
        list.sort((i1, i2) -> {
            int result = Integer.compare(i1.get(col-1), i2.get(col-1));
            if (result != 0) return result;
            result = Integer.compare(i2.get(0), i1.get(0));
            return result;
        });
        
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) newArr[i] += (list.get(i).get(j) % (i+1));
        
        int target = newArr[row_begin-1];
        for (int i = row_begin; i < row_end; i++) target = (target ^ newArr[i]);
        // for (int i = 0; i < n; i++) System.out.print(newArr[i] + " ");
        // System.out.println();
        
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) System.out.print(list.get(i).get(j) + " ");
        //     System.out.println();
        // }
        
        return target;
    }
}