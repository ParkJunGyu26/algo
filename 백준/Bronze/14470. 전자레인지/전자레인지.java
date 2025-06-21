import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int D = sc.nextInt();
        int E = sc.nextInt();

        long totalTime = 0; // 총 소요 시간 (초)

        if (A < 0) {
            totalTime += Math.abs(A) * C;

            totalTime += D;

            totalTime += B * E;
        }
        else {
            totalTime += (B - A) * E;
        }

        System.out.println(totalTime);

        sc.close();
    }
}