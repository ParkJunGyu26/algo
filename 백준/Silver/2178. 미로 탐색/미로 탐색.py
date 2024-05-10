import sys, pprint
from collections import deque
input = sys.stdin.readline

def bfs(x, y):
    q = deque()
    q.append((x, y))
    ans[y][x] = 1
    visited[y][x] = True

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if -1 < nx < m and -1 < ny < n:
                if not visited[ny][nx] and graph[ny][nx] == '1':
                    q.append((nx, ny))
                    visited[ny][nx] = True
                    ans[ny][nx] = ans[y][x] + 1


dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]
n, m = map(int, input().split())
graph = [list(input().rstrip()) for _ in range(n)]
visited = [[False] * m for _ in range(n)]
ans = [[0] * m for _ in range(n)]

bfs(0, 0)
print(ans[n-1][m-1])