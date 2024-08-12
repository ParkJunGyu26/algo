import sys
input = sys.stdin.readline

row, column, query = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(row)]
prefix_sum = [[0] * (column+1) for _ in range(row+1)]

for i in range(1, row+1):
    for j in range(1, column+1):
        prefix_sum[i][j] = graph[i-1][j-1] + prefix_sum[i-1][j] + prefix_sum[i][j-1] - prefix_sum[i-1][j-1]

for i in range(query):
    r1, c1, r2, c2 = map(int, input().split())

    all_cnt = (r2-r1+1)*(c2-c1+1)
    all_sum = prefix_sum[r2][c2] - prefix_sum[r2][c1-1] - prefix_sum[r1-1][c2] + prefix_sum[r1-1][c1-1]

    print(all_sum//all_cnt)