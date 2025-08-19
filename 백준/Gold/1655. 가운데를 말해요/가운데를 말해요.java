import java.io.*;
import java.util.*;

// 가중치 음수라면 벨만-포드!!
// 가중치 양수라면, 다익스트라 & 플로이드-워셜 & MST(최소신장트리)

public class Main {
    static int n;
    static PriorityQueue<Integer> maxHeap, minHeap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());

        maxHeap = new PriorityQueue<>((i1, i2) -> Integer.compare(i2, i1));
        minHeap = new PriorityQueue<>((i1, i2) -> Integer.compare(i1, i2));

        int num = Integer.parseInt(br.readLine());
        minHeap.offer(num);
        sb.append(num).append("\n");

        for (int i = 1; i < n; i++) {
            num = Integer.parseInt(br.readLine());

            if (minHeap.peek() < num) {
                minHeap.offer(num);
            } else {
                maxHeap.offer(num);
            }

            if (minHeap.size() > maxHeap.size()) {
                if (minHeap.size() > maxHeap.size() + 1 || maxHeap.peek() < minHeap.peek()) maxHeap.offer(minHeap.poll());
            }

            if (maxHeap.size() > minHeap.size()) {
                if (maxHeap.size() > minHeap.size() + 1 || minHeap.peek() < maxHeap.peek()) minHeap.offer(maxHeap.poll());
            }

            sb.append(maxHeap.peek()).append("\n");
        }

        System.out.println(sb);
    }
}

/*

3
1
3
5
-> 1 1 3

5
1
5
2
4
3
-> 1 1 2 2 3

 */