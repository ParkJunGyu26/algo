import sys
import pprint
input = sys.stdin.readline

n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
prefix_sum = [[0] * (m+1) for _ in range(n+1)]

for i in range(1, n+1):
    for j in range(1, m+1):
        prefix_sum[i][j] = prefix_sum[i][j-1] + prefix_sum[i-1][j] + arr[i-1][j-1] - prefix_sum[i-1][j-1]

ans = -int(1e9)
for i1 in range(1, n+1):
    for j1 in range(1, m+1):
        for i2 in range(i1, n+1):
            for j2 in range(j1, m+1):
                value = prefix_sum[i2][j2] - prefix_sum[i1-1][j2] - prefix_sum[i2][j1-1] + prefix_sum[i1-1][j1-1]
                if value > ans:
                    ans = value
            
print(ans)