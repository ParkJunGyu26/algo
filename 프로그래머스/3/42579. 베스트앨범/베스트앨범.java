import java.util.*;
import java.io.*;

class Solution {
    static int[] answer;
    static ArrayList<String> gList;
    static HashMap<String, ArrayList<Info>> hm;
    static HashMap<String, Long> total;
    
    static class Info {
        int count, index;
        
        Info(int c, int i) {
            count = c;
            index = i;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        hm = new HashMap<>();
        total = new HashMap<>();
        gList = new ArrayList<>();
        
        for (int i = 0; i < genres.length; i++) {
            ArrayList<Info> list = new ArrayList<>();
            String g = genres[i];
            
            if (hm.containsKey(g)) list = hm.get(g);
            list.add(new Info(plays[i], i));
            hm.put(g, list);
        }
        
        int musicCnt = 0;
        for (String g : hm.keySet()) {
            long cnt = 0;
            for (Info info : hm.get(g)) cnt += info.count;
            total.put(g, cnt);
            gList.add(g);
            
            ArrayList<Info> infos = hm.get(g);
            infos.sort((i1, i2) -> {
                if (i1.count == i2.count) {
                    return i1.index - i2.index;
                }
                return i2.count - i1.count;
            });
            
            int infoSize = infos.size();
            if (infoSize >= 2) infoSize = 2;
            musicCnt += infoSize;
        }
        
        gList.sort((g1, g2) -> Long.compare(total.get(g2), total.get(g1)));
        
        answer = new int[musicCnt];
        int Index = 0;
        for (String g : gList) {
            for (int i = 0; i < Math.min(hm.get(g).size(), 2); i++) {
                // System.out.println("index : " + Index++);
                answer[Index++] = hm.get(g).get(i).index;
            }
        }
        
        return answer;
    }
}