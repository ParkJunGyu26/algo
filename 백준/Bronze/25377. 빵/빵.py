import sys
input = sys.stdin.readline

n = int(input())
ans = 1e9

for i in range(n):
    a, b = map(int, input().split())
    if a <= b:
        ans = min(ans, b)

if ans == 1e9:
    print(-1)
else:
    print(ans)