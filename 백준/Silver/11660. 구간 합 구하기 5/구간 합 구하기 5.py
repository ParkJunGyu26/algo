import sys
input = sys.stdin.readline

n, m = map(int, input().split())
g = [list(map(int, input().split())) for _ in range(n)]
prefix = [[0] * (n+1) for _ in range(n+1)]

for i in range(1, n+1):
    for j in range(1, n+1):
        prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + g[i-1][j-1]

for i in range(m):
    a, b, c, d = map(int, input().split())
    print(prefix[c][d] + prefix[a-1][b-1] - prefix[a-1][d] - prefix[c][b-1])
