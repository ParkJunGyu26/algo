import sys
from collections import deque
input = sys.stdin.readline

def bfs(x, y):
    cnt = 1
    q = deque()
    q.append((x, y))

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if -1 < nx < m and -1 < ny < n:
                if not visited[ny][nx] and graph[ny][nx]:
                    visited[ny][nx] = True
                    q.append((nx, ny))
                    cnt += 1
    
    return cnt

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]
n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
visited = [[False] * m for _ in range(n)]
ans = []

for i in range(n):
    for j in range(m):
        if not visited[i][j] and graph[i][j]:
            visited[i][j] = True
            ans.append(bfs(j, i))

if ans:
    print(len(ans))
    print(max(ans))
else:
    print(0)
    print(0)