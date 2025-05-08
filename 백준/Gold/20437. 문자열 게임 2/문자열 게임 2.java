import java.util.*;
import java.io.*;

public class Main {

    static int t, k;
    static String w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int first = 10000;
            int second = 0;
            ArrayList<Queue<Integer>> res = new ArrayList<>();
            for (int j = 0; j < 26; j++) res.add(new LinkedList<>());

            w = br.readLine();
            k = Integer.parseInt(br.readLine());

            // 3번 조건
            for (int j = 0; j < w.length(); j++) {
                int index = w.charAt(j)-'a';
                res.get(index).add(j);

                if (res.get(index).size() == k) {
                    int alphabetIndex = res.get(index).poll();
                    
                    if (first > j - alphabetIndex) first = j - alphabetIndex + 1;
                }
            }

            res = new ArrayList<>();
            for (int j = 0; j < 26; j++) res.add(new LinkedList<>());

            // 4번 조건
            for (int j = 0; j < w.length(); j++) {
                int index = w.charAt(j)-'a';
                res.get(index).add(j);

                if (res.get(index).size() == k) {
                    int alphabetIndex = res.get(index).poll();
                    
                    if (second < j - alphabetIndex +1) second = j - alphabetIndex + 1;
                }
            }

            if (first == 10000 || second == 0) bw.write("-1\n");
            else bw.write(first + " " + second + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}