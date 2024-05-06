import sys
from collections import deque
input = sys.stdin.readline

def bfs(x, y):
    cnt = 1
    q = deque()
    q.append((x, y))

    while q:
        x, y = q.popleft()
        visited[y][x] = True

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if -1 < nx < m and -1 < ny < n:
                if not visited[ny][nx] and graph[ny][nx]:
                    visited[ny][nx] = True
                    q.append((nx, ny))
                    cnt += 1
        # print("-----")
    
    return cnt

dx = [1, -1, 0, 0]
dy = [0, 0, -1, 1]
t = int(input())

for _ in range(t):
    m, n, k = map(int, input().split())
    graph = [[0] * m for _ in range(n)]
    visited = [[False] * m for _ in range(n)]
    ans = []

    for i in range(k):
        a, b = map(int, input().split())
        graph[b][a] = 1
    
    for i in range(n):
        for j in range(m):
            if graph[i][j] == 1 and not visited[i][j]:
                ans.append(bfs(j, i))
    
    print(len(ans))