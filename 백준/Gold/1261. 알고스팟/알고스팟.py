import sys
from collections import deque
input = sys.stdin.readline

def bfs(x, y):
    q = deque([(x, y)])

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if -1 < nx < n and -1 < ny < m:
                if graph[ny][nx] == '0':
                    if res[ny][nx] > res[y][x]:
                        res[ny][nx] = res[y][x]
                        q.append((nx ,ny))
                else:
                    if res[ny][nx] > res[y][x] + 1:
                        res[ny][nx] = res[y][x]+1
                        q.append((nx, ny))
    
    return

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

n, m = map(int, input().split())
graph = [list(input().rstrip()) for _ in range(m)]
res = [[int(1e9)] * n for _ in range(m)]

res[0][0] = 0
bfs(0, 0)
print(res[m-1][n-1])