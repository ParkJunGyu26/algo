import java.io.*;
import java.util.*;

public class Main {
    static int n, k, answer;
    static ArrayList<String> list;

    private static void dfs(int index, boolean[] visited, int total) {
        if (total == k) {

            int result = 0;
            // O(N)
            for (String str : list) {
                int cnt = 0;
                // O(15)
                for (int j = 0; j < str.length(); j++) {
                    if (visited[str.charAt(j) - 'a']) cnt++;
                }
                if (cnt == str.length()) result++;
            }

            answer = Math.max(answer, result);

            return;
        }

        for (int i = index; i < 26; i++) {
            String chr = String.valueOf((char) (i + 'a'));
            if (chr.equals("a") || chr.equals("n") || chr.equals("t") || chr.equals("i") || chr.equals("c")) continue;
            visited[i] = true;
            dfs(i+1, visited, total+1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                String chr = String.valueOf((char) (j + 'a'));
                if (chr.equals("a") || chr.equals("n") || chr.equals("t") || chr.equals("i") || chr.equals("c")) continue;
                if (tmp.contains(chr)) sb.append(chr);
            }
            String target = sb.toString();
            list.add(target);
        }
        
        if (k < 5) {
            System.out.println(0);
            return;
        }

        k -= 5;
        boolean[] visited = new boolean[26];
        dfs(0, visited, 0);
        System.out.println(answer);
    }
}

/*

[INPUT]
3 5
antaic
antaic
antaci

[OUTPUT]
3

[INPUT]
4 5
antacatica
antatnitica
antakcctica
antatica

[OUTPUT]
3

 */