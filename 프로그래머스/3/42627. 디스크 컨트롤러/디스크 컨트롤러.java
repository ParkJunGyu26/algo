import java.util.*;

class Solution {
    static class Disk implements Comparable<Disk> {
        int rqTime, usedTime;
        Disk(int r, int u) {
            rqTime = r;
            usedTime = u;
        }
        @Override
        public int compareTo(Disk d) {
            return this.usedTime - d.usedTime; // 소요시간 오름차순
        }
    }
    
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]); // 요청 시각 오름차순
        PriorityQueue<Disk> pq = new PriorityQueue<>();
        
        int time = 0, idx = 0, total = 0, cnt = 0;
        int n = jobs.length;
        
        while (cnt < n) {
            // 현재 시간에 요청된 작업을 모두 큐에 넣음
            while (idx < n && jobs[idx][0] <= time) {
                pq.offer(new Disk(jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            if (pq.isEmpty()) { // 대기 큐가 비면, 다음 작업까지 점프
                time = jobs[idx][0];
            } else {
                Disk cur = pq.poll();
                time += cur.usedTime;
                total += (time - cur.rqTime);
                cnt++;
            }
        }
        return total / n;
    }
}
