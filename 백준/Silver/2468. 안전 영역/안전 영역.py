import sys
from collections import deque
input = sys.stdin.readline

def bfs(height, x, y):
    cnt = 0
    q = deque()
    q.append((x, y))
    # print("x : ", x)
    # print("y : ", y)

    while q:
        x, y = q.popleft()

        for i in range(5):
            nx = x + dx[i]
            ny = y + dy[i]
            if -1 < nx < n and -1 < ny < n:
                if not visited[ny][nx] and graph[ny][nx] > height:
                    q.append((nx, ny))
                    visited[ny][nx] = True
                    cnt = 1
    
    return cnt



dx = [1, -1, 0, 0, 0]
dy = [0, 0, -1, 1, 0]
n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]

max_height = graph[0][0]
for i in range(n):
    for j in range(n):
        if max_height < graph[i][j]:
            max_height = graph[i][j]

ans = []
for h in range(1, max_height+1):
    cnt = 0
    visited = [[False] * n for _ in range(n)]
    # print("height : ", h)
    for i in range(n):
        for j in range(n):
            if not visited[i][j] and graph[i][j] > h:
                cnt += bfs(h, j, i)
    ans.append(cnt)
    # print("~~~~")

if max(ans) == 0:
    print(1)
else:
    print(max(ans))