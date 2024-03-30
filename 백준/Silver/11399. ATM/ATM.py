# 그리디 알고리즘
import sys
input = sys.stdin.readline

n = int(input())
time = list(map(int, input().split()))
time.sort()

ans = [0] * (n+1)
for i in range(n):
    ans[i+1] = ans[i] + time[i]

print(sum(ans))