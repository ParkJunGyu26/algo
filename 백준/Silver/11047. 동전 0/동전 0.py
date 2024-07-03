import sys
input = sys.stdin.readline

N, K = map(int, input().split())
coin = [int(input()) for i in range(N)]

ans = 0
for i in coin[::-1]:
    if K // i:
        ans += K//i
        K = K % i

print(ans)