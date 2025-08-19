import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] graph, newGraph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        newGraph = new int[n][n];
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) newGraph[i][j] = graph[i][j] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        boolean check = true;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][j] == 0 || graph[i][k] == 0 || graph[k][j] == 0) continue;

                    if (graph[i][j] == graph[i][k] + graph[k][j]) {
                        graph[i][j] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                answer += graph[i][j];
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;

                    if (graph[i][k] == 0 || graph[k][j] == 0) continue;

                    if (graph[i][j] == 0) graph[i][j] = graph[i][k] + graph[k][j];
                    else graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] != newGraph[i][j]) {
                    check = false;
                }
            }
        }

        if (check) System.out.println(answer);
        else System.out.println(-1);
    }
}