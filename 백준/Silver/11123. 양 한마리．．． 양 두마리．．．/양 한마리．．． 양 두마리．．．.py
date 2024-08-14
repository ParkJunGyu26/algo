import sys
from collections import deque
input = sys.stdin.readline

def bfs(x, y):
    q = deque([(x, y)])
    cnt = 1

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if -1 < nx < w and -1 < ny < h:
                if not visited[ny][nx] and graph[ny][nx] == '#':
                    visited[ny][nx] = 1
                    q.append((nx, ny))

    return cnt


dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

t = int(input())
for _ in range(t):
    h, w = map(int, input().split())
    graph = [list(input().rstrip()) for _ in range(h)]
    visited = [[0] * w for _ in range(h)]
    ans = 0
    for i in range(h):
        for j in range(w):
            if graph[i][j] == '#' and not visited[i][j]:
                visited[i][j] = 1
                ans += bfs(j, i)
    
    print(ans)