import java.io.*;
import java.util.*;

public class Main {
    static int n, answer = 0;
    static int[] scoreOfDay = new int[1001];
    static ArrayList<Homework> list;

    static class Homework {
        int day, score;
        Homework(int day, int score) {
            this.day = day;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        int MAX_DAY = 0;
        for (int i = 0; i < n; i++) {
            int d, w;
            StringTokenizer st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            
            list.add(new Homework(d, w));
            MAX_DAY = Math.max(list.get(i).day, MAX_DAY);
        }
        list.sort((h1, h2) -> {
            int result = h1.day - h2.day;
            if (result != 0) return result;
            result = h1.score - h2.score;
            return result;
        });

        for (int i = 0; i < n; i++) {
            if (scoreOfDay[list.get(i).day] == 0) {
                scoreOfDay[list.get(i).day] = list.get(i).score;
            } else {
                int score = 101;
                int day = 0;
                for (int j = 1; j <= list.get(i).day; j++) {
                    if (score > scoreOfDay[j]) {
                        score = scoreOfDay[j];
                        day = j;
                    }
                }               

                scoreOfDay[day] = list.get(i).score;
            }
        }

        for (int i = 1; i <= MAX_DAY; i++) answer += scoreOfDay[i];

        System.out.println(answer);
    }
}