def people(n):
    if dp[n]:
        return "SK"
    return "CY"

import sys

input = sys.stdin.readline

n = int(input())

# 1이 상근, 0이 창영
dp = [-1, 1, 0, 1, 1]

if n < 5:
    print(people(n))
else:
    for i in range(5, n+1):
        if dp[i-1] == 0 or dp[i-3] == 0 or dp[i-4] == 0:
            dp.append(1)
        else:
            dp.append(0)
    print(people(n))