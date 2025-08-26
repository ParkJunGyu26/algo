import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static ArrayList<String[]> list;
    static HashMap<Integer, String> hm;

    public static void main(String[] args) throws IOException {
        hm = new HashMap<>(10);
        hm.put(0, "zero");
        hm.put(1, "one");
        hm.put(2, "two");
        hm.put(3, "three");
        hm.put(4, "four");
        hm.put(5, "five");
        hm.put(6, "six");
        hm.put(7, "seven");
        hm.put(8, "eight");
        hm.put(9, "nine");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        for (int i = m; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            if (i / 10 != 0) sb.append(hm.get(i / 10)).append(" ");
            sb.append(hm.get(i % 10));

            list.add(new String[] {sb.toString(), String.valueOf(i)});
        }

        list.sort((s1, s2) -> {
            int result = s1[0].compareTo(s2[0]);
            return result;
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)[1]).append(" ");
            if (i % 10 == 9) sb.append("\n");
        }
        System.out.println(sb);
    }
}