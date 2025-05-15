import java.util.*;
import java.io.*;

public class Main {

    static int t, k, num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            PriorityQueue<Long> pq = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                long num = Long.parseLong(st.nextToken());
                pq.add(num);
            }

            long answer = 0;
            while(pq.size() > 1) {
                long number1 = pq.poll();
                long number2 = pq.poll();
                answer += (number1 + number2);
                pq.add(number1+number2);
            }

            bw.write(String.valueOf(answer+"\n"));
            bw.flush();
        }
        bw.close();
        br.close();
    }
}