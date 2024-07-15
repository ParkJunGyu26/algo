import sys
input = sys.stdin.readline

N = int(input())

ans = 0
for i in range(N):
    p = list(map(int, input().split()))
    s_p = sorted(p[2:len(p)])
    total = max(p[:2]) + s_p[-1] + s_p[-2]
    ans = max(ans, total)

print(ans)