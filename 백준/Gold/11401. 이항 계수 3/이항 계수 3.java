import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static final long MOD = 1000000007;
    static long[] fact, inv;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        fact = new long[n+1];
        inv = new long[n+1];

        // 팩토리얼 계산
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i-1] * i) % MOD;
        }

        // 역수 계산 (페르마의 소정리)
        inv[n] = pow(fact[n], MOD-2, MOD);
        for (int i = n-1; i >= 0; i--) {
            inv[i] = (inv[i+1] * (i+1)) % MOD;
        }

        // 이항 계수 계산
        long answer = (fact[n] * inv[k] % MOD) * inv[n - k] % MOD;
        System.out.println(answer);
    }

    static long pow(long a, long b, long mod) {
        long ret = 1;
        while (b > 0) {
            if (b % 2 == 1) ret = (ret * a) % mod;
            a = (a * a) % mod;
            b /= 2;
        }
        return ret;
    }
}
