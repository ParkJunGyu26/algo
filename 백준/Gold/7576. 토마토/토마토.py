# 1인 곳에서 동시에 출발!!

import sys
from collections import deque
input = sys.stdin.readline

def bfs():

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if -1 < nx < n and -1 < ny < m:
                if not v[ny][nx]:
                    if g[ny][nx] == 0:
                        q.append((nx, ny))
                        v[ny][nx] = True
                        res[ny][nx] = res[y][x] + 1
                        g[ny][nx] = 1
                    elif g[ny][nx] == 1:
                        q.append((nx, ny))
                        v[ny][nx] = True

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
n, m = map(int, input().split())
g = [list(map(int, input().split())) for _ in range(m)]
v = [[False] * n for _ in range(m)]
res = [[0] * n for _ in range(m)]

q = deque()
cnt = 0

for i in range(m):
    for j in range(n):
        if g[i][j] == 1:
            v[i][j] = True
            q.append((j, i))
            cnt += 1
        elif g[i][j] == -1:
            cnt += 1

bfs()
# print("cnt2 : ", cnt)

ans = 0
for i in range(m):
    for j in range(n):
        if res[i][j] == 0:
            cnt -= 1
        else:
            ans = max(ans, res[i][j])

if cnt == 0:
    print(ans)
else:
    print(-1)