import sys
from collections import deque
input = sys.stdin.readline

def bfs(x, y, target):
    cnt = 1
    q = deque()
    q.append((x, y))

    while q:
        x, y = q.popleft()
        visited[y][x] = True

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if -1 < nx < n and -1 < ny < m:
                if not visited[ny][nx] and graph[ny][nx] == target:
                    cnt += 1
                    visited[ny][nx] = True
                    q.append((nx, ny))
        
    return cnt


dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]
n, m = map(int, input().split())
graph = [list(input().rstrip()) for _ in range(m)]

ans = []
for _ in range(2):
    cnt1 = 0
    cnt2 = 0
    visited = [[False] * n for _ in range(m)]
    if _ == 0:
        for i in range(m):
            for j in range(n):
                if not visited[i][j] and graph[i][j] == 'W':
                    cnt1 += (bfs(j, i, 'W')**2)
        ans.append(cnt1)
    else:
        for i in range(m):
            for j in range(n):
                if not visited[i][j] and graph[i][j] == 'B':
                    cnt2 += (bfs(j, i, 'B')**2)
        ans.append(cnt2)

print(*ans)