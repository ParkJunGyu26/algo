import sys
input = sys.stdin.readline

n, k, p = map(int, input().split())
bread = list(map(int, input().split()))

ans = 0
for i in range(n):
    fail = 0
    for j in range(i*k, i*k+k):
        if bread[j] == 0:
            fail += 1
    if fail < p:
        ans += 1

print(ans)