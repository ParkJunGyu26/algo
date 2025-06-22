import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr;
    static long[] dp = new long[21];

    static boolean inRange(int num) {
        return (-1 < num && num < 21);
    }

    static void DP(int num, int nNum, ArrayList<Integer> newNumList, long[] newDp) {
        if (!inRange(nNum)) return;

        if (!newNumList.contains(nNum)) newNumList.add(nNum);
        newDp[nNum] += dp[num];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        ArrayList<Integer> numList = new ArrayList<>();
        numList.add(arr[0]);
        dp[arr[0]] = 1;

        for (int i = 1; i < n-1; i++) {
            long[] newDp = new long[21];
            ArrayList<Integer> newNumList = new ArrayList<>();
            for (int num : numList) {
                DP(num, num + arr[i], newNumList, newDp);
                DP(num, num - arr[i], newNumList, newDp);
            }

            numList.clear();
            for (int j = 0; j <= 20; j++) dp[j] = newDp[j];
            for (int nNum : newNumList) numList.add(nNum);
        }

        bw.write(dp[arr[n-1]] + "");
        bw.flush();
        bw.close();
        br.close();
    }
}