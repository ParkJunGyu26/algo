import sys

input = sys.stdin.readline

dp = [0, 1, 2, 4] + [0] * 7

t = int(input())
for _ in range(t):
    num = int(input())
    if num <= 3:
        print(dp[num])
    else:
        for i in range(4, num+1):
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
        print(dp[num])