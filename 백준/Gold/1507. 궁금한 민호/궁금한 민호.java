import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] graph, newGraph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 주어진 최단 거리 2차원 배열 (원본)
        newGraph = new int[n][n];

        // 초기 그래프(엣지)를 역추적하기 위한 2차원 배열
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
                    // 6 = 6 + 0 이러는 경우가 있기 때문에 스킵 (즉, 바로 이동할 수 있는 경우)
                    if (graph[i][k] == 0 || graph[k][j] == 0) continue;

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

        // 초기의 그래프를 통해 다시, 최단 거리 2차원 배열으로 플로이드 워셜로 만듦
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

        // 초기 최단 거리 그래프와 초기 그래프를 통한 최단 거리 결과가 다른 경우는 불가능한 경우로 취급
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] != newGraph[i][j]) {
                    check = false;
                }
            }
        }

        System.out.println((check ? answer : -1));
    }
}