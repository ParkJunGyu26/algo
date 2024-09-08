import sys
import pprint
input = sys.stdin.readline

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
prefix_sum = [[0] * (m+1) for _ in range(n+1)]
for i in range(1, n+1):
    for j in range(1, m+1):
        prefix_sum[i][j] = graph[i-1][j-1] + prefix_sum[i-1][j] + prefix_sum[i][j-1] - prefix_sum[i-1][j-1]

k = int(input())
for _ in range(k):
    i, j, y, x = map(int, input().split())
    print(prefix_sum[y][x] - prefix_sum[y][j-1] - prefix_sum[i-1][x] + prefix_sum[i-1][j-1])