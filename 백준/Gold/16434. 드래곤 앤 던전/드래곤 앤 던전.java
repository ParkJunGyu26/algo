import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static long attack;
    static Room[] room;

    static class Room {
        int status, attack, life;

        Room(int t, int a, int h) {
            status = t;
            attack = a;
            life = h;
        }
    }

    static boolean check(long target) {
        long nowHp = target;
        long nowAttack = attack;
        for (int i = 1; i <= n; i++) {
            if (room[i].status == 1) {
                // 마지막 턴에서 적에게 공격 안 맞고, 죽임
                if (room[i].life % nowAttack == 0) nowHp -= (room[i].attack * (room[i].life / nowAttack - 1));
                // 마지막 턴에서 적에게 공격 맞고 죽일 수 있음
                else nowHp -= (room[i].attack * (room[i].life / nowAttack));
                
            } else {
                nowAttack += room[i].attack;
                nowHp = Math.min(nowHp + room[i].life, target);
            }

            if (nowHp <= 0) return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        attack = Long.parseLong(st.nextToken());
        room = new Room[n+1];

        for (int i = 1; i <= n; i++) {
            int t, a, h;
            st = new StringTokenizer(br.readLine());

            t = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            room[i] = new Room(t, a, h);
        }

        long answer = 0;
        long left = 1;
        long right = 123_456L * 1_000_000_000_000L;
        while (left <= right) {
            long mid = (left + right) / 2;

            if (check(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}