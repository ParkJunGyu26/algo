import sys, pprint
from collections import deque
input = sys.stdin.readline

def bfs(x, y):
    global ans
    tmp = 0
    q = deque()
    q.append((x, y))

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if -1 < nx < w and -1 < ny < l:
                if not visited[ny][nx] and graph[ny][nx] == 'L':
                    visited[ny][nx] = True
                    q.append((nx, ny))
                    dist[ny][nx] = dist[y][x] + 1
                    tmp = dist[ny][nx]
    
    ans = max(ans, tmp)

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]
l, w = map(int, input().split())
graph = [list(input().rstrip()) for _ in range(l)]

ans = 0
cnt = 0
for i in range(l):
    for j in range(w):
        if graph[i][j] == 'L':
            cnt += 1
            visited = [[False] * w for _ in range(l)]
            dist = [[0] * w for _ in range(l)]
            visited[i][j] = True
            bfs(j, i)
            # pprint.pp(dist)
            # print(cnt)
            # print("-----")

print(ans)