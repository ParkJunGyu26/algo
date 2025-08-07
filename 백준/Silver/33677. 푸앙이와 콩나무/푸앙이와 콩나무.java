import java.util.*;
import java.io.*;

public class Main {
    static final int MAX = 1_000_000;
    static int n;
    static ArrayList<int[]> dp; // 인덱스 : 길이, [0] : 일수, [1] : 물의 양

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        while (!q.isEmpty()) {
            int beenTree = q.poll();

            int[] info = dp.get(beenTree);
            int day = info[0];
            int water = info[1];

            int nBeenTree = beenTree + 1;
            int[] nInfo;
            int nDay = day + 1;
            int nWater = water + 1;

            if (0 < nBeenTree && nBeenTree <= MAX) {
                nInfo = dp.get(nBeenTree);
                if (nInfo[0] >= nDay && nInfo[1] > nWater) {
                    dp.set(nBeenTree, new int[] {nDay, nWater});
                    q.offer(nBeenTree);
                }
            }

            nBeenTree = beenTree * 3;
            nWater = water + 3;

            if (0 < nBeenTree && nBeenTree <= MAX) {
                nInfo = dp.get(nBeenTree);
                if (nInfo[0] >= nDay && nInfo[1] > nWater) {
                    dp.set(nBeenTree, new int[] {nDay, nWater});
                    q.offer(nBeenTree);
                }
            }

            nBeenTree = beenTree * beenTree;
            nWater = water + 5;

            if (0 < nBeenTree && nBeenTree <= MAX) {
                nInfo = dp.get(nBeenTree);
                if (nInfo[0] >= nDay && nInfo[1] > nWater) {
                    dp.set(nBeenTree, new int[] {nDay, nWater});
                    q.offer(nBeenTree);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new ArrayList<>();
        for (int i = 0; i <= MAX; i++) dp.add(new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE});

        dp.set(0, new int[] {0, 0});
        dp.set(1, new int[] {1, 1});

        bfs();

        System.out.println(dp.get(n)[0] + " " + dp.get(n)[1]);
    }
}