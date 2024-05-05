import sys
from collections import deque
input = sys.stdin.readline

def bfs(x, y):
    q = deque()
    q.append((x, y))
    graph[y][x] = 0
    count = 1

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if -1 < nx < m and -1 < ny < n:
                if graph[ny][nx] == 1:
                    graph[ny][nx] = 0
                    q.append((nx, ny))
                    count += 1
    
    return count

n, m, c = map(int, input().split())
graph = [[0] * m for _ in range(n)]
for _ in range(c):
    a, b = map(int, input().split())
    graph[a-1][b-1] = 1

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]

cnt = []
# 세로
for i in range(n):
    # print(" i : ", i)
    # 세로
    for j in range(m):
        # print(" j : ", j)
        if graph[i][j] == 1:
            cnt.append(bfs(j, i))
    # print(cnt)

print(max(cnt))