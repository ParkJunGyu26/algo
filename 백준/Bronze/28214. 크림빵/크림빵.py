import sys
input = sys.stdin.readline

n, k, p = map(int, input().split())
bread = list(map(int, input().split()))

ans = 0
for i in range(n):
    pojang = 0
    for j in range(i*k, i*k+k):
        if bread[j] == 1:
            pojang += 1
    if pojang >= p:
        ans += 1

print(ans)