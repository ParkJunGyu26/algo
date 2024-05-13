import sys
from collections import deque
input = sys.stdin.readline

def bfs(x, y):
    q = deque([(x, y)])

    while q:
        x, y = q.popleft()

        for i in range(8):
            nx = x + dx[i]
            ny = y + dy[i]
            if -1 < nx < w and -1 < ny < h:
                if not visited[ny][nx] and graph[ny][nx]:
                    visited[ny][nx] = True
                    q.append((nx, ny))
    
    return 1


dx = [0, 1, 1, 1, 0, -1, -1, -1]
dy = [1, 1, 0, -1, -1, -1, 0, 1]
while True:
    w, h = map(int, input().split())
    if w == 0 and h == 0:
        break

    graph = [list(map(int, input().split())) for _ in range(h)]
    visited = [[False] * w for _ in range(h)]
    island = 0
    for i in range(h):
        for j in range(w):
            if not visited[i][j] and graph[i][j]:
                visited[i][j] = True
                island += bfs(j, i)
    print(island)