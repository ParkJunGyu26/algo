import sys
input = sys.stdin.readline

MOD = 1000000007

t = int(input())
for _ in range(t):
    n = int(input())
    if n == 1:
        print(1)  # N이 1일 때는 1을 출력
    else:
        n -= 2  # n-1로 계산 (1번 돌에서 시작하므로)
        ans = 1
        base = 2
        while n > 0:
            if n % 2 == 1:
                ans = (ans * base) % MOD
            base = (base * base) % MOD
            n //= 2
        print(ans)